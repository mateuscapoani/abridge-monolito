package com.github.mateuscapoani.abridgemonolito.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CriarUrlResponseDto {

    private String urlCurta;
}