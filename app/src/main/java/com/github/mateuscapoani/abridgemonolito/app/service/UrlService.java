package com.github.mateuscapoani.abridgemonolito.app.service;

import com.github.mateuscapoani.abridgemonolito.app.dto.CriarUrlRequestDto;
import com.github.mateuscapoani.abridgemonolito.app.dto.CriarUrlResponseDto;
import com.github.mateuscapoani.abridgemonolito.app.entity.UrlEncurtada;
import com.github.mateuscapoani.abridgemonolito.app.exception.BadRequestException;
import com.github.mateuscapoani.abridgemonolito.app.repository.UrlEncurtadaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlService {

    private final UrlEncurtadaRepository repository;

    @Value("${encurtador.host}")
    private String hostUrl;
    private static final String ALGORITIMO_CRIPTOGRAFIA = "SHA-256";
    private static final Integer ZERO = 0;
    private static final Integer TAMANHO_URL = 6;

    public CriarUrlResponseDto criar(CriarUrlRequestDto criarUrlRequestDto) {
        if (isNull(criarUrlRequestDto) || isBlank(criarUrlRequestDto.getUrlOriginal())) {
            throw new BadRequestException("Url inválida");
        }

        UrlEncurtada urlEncurtada = criarSeNaoExiste(criarUrlRequestDto.getUrlOriginal());

        return CriarUrlResponseDto.builder()
                .urlCurta(hostUrl + urlEncurtada.getUrlCurta())
                .build();
    }

    private UrlEncurtada criarSeNaoExiste(String urlOriginal) {
        return repository.findByUrlOriginal(urlOriginal).orElseGet(() -> {
            String urlCurta = gerarUrlCurta(urlOriginal);
            UrlEncurtada urlEncurtada = UrlEncurtada.builder()
                    .urlCurta(urlCurta)
                    .urlOriginal(urlOriginal)
                    .dataCriacao(LocalDateTime.now())
                    .build();
            return repository.save(urlEncurtada);
        });
    }

    private String gerarUrlCurta(String urlOriginal) {
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITIMO_CRIPTOGRAFIA);
            byte[] hashBytes = digest.digest(urlOriginal.getBytes());
            String encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes);
            return encoded.substring(ZERO, TAMANHO_URL);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
            return gerarUrlCurtaAleatoria();
        }
    }

    private String gerarUrlCurtaAleatoria() {
        StringBuilder sb = new StringBuilder();
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = ZERO; i < TAMANHO_URL; i++) {
            sb.append(caracteres.charAt((int) (Math.random() * caracteres.length())));
        }
        return sb.toString();
    }
}
