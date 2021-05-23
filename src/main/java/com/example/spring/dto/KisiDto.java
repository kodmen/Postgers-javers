package com.example.spring.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
@Data
public class KisiDto {
    private Long id;
    private String adi;
    private String soyadi;
    private List<String> adresler;
}
