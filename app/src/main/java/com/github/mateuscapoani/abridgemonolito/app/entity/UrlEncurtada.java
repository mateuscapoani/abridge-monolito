package com.github.mateuscapoani.abridgemonolito.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "url_encurtada")
public class UrlEncurtada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url_original")
    private String urlOriginal;

    @Column(name = "url_curta")
    private String urlCurta;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
}
