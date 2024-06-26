package com.github.mateuscapoani.abridgemonolito.app.controller;

import com.github.mateuscapoani.abridgemonolito.app.dto.CriarUrlRequestDto;
import com.github.mateuscapoani.abridgemonolito.app.dto.CriarUrlResponseDto;
import com.github.mateuscapoani.abridgemonolito.app.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("url")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("criar")
    public CriarUrlResponseDto criar(@RequestBody CriarUrlRequestDto criarUrlRequest) {
        return urlService.criar(criarUrlRequest);
    }
}
