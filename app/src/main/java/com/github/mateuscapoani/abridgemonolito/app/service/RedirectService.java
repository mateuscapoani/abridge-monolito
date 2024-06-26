package com.github.mateuscapoani.abridgemonolito.app.service;

import com.github.mateuscapoani.abridgemonolito.app.entity.UrlEncurtada;
import com.github.mateuscapoani.abridgemonolito.app.exception.BadRequestException;
import com.github.mateuscapoani.abridgemonolito.app.repository.UrlEncurtadaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedirectService {

    private final UrlEncurtadaRepository repository;

    public ResponseEntity<Void> redirecionar(String urlCurta) {
        UrlEncurtada urlEncurtada = repository.findByUrlCurta(urlCurta)
                .orElseThrow(() -> new BadRequestException("Url não encontrada"));

        return ResponseEntity
                .status(302)
                .header("Location", urlEncurtada.getUrlOriginal())
                .build();
    }
}
