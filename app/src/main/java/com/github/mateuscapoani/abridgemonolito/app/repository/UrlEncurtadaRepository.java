package com.github.mateuscapoani.abridgemonolito.app.repository;

import com.github.mateuscapoani.abridgemonolito.app.entity.UrlEncurtada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlEncurtadaRepository extends JpaRepository<UrlEncurtada, Long> {

    Optional<UrlEncurtada> findByUrlCurta(String urlCurta);
    Optional<UrlEncurtada> findByUrlOriginal(String urlOriginal);
}
