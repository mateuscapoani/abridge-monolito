package com.github.mateuscapoani.abridgemonolito.app.controller;

import com.github.mateuscapoani.abridgemonolito.app.service.RedirectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("r")
@RequiredArgsConstructor
public class RedirectUrlController {

    private final RedirectService redirectService;

    @GetMapping("{urlCurta}")
    public ResponseEntity<Void> redirect(@PathVariable String urlCurta) {
        return redirectService.redirecionar(urlCurta);
    }
}
