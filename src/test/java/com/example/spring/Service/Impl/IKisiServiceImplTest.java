package com.example.spring.Service.Impl;

import com.example.spring.Service.KisiService;
import com.example.spring.dto.KisiDto;
import com.example.spring.entity.Kisi;
import com.example.spring.repo.AdresRepository;
import com.example.spring.repo.KisiRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IKisiServiceImplTest {

    @InjectMocks
    private IKisiServiceImpl kisiService;

    @Mock
    private KisiRepository kisiRepository;

    @Mock
    private AdresRepository adresRepository;

    @Test
    void testSave() {
        KisiDto kisiDto = new KisiDto();
        kisiDto.setAdi("test-name");
        kisiDto.setSoyadi("test-lastname");
        kisiDto.setAdresler(Arrays.asList("test-adres-1"));
        Kisi kisiMock = Mockito.mock(Kisi.class);

        Mockito.when(kisiMock.getId()).thenReturn(1L);
        Mockito.when(kisiRepository.save(ArgumentMatchers.any(Kisi.class))).thenReturn(kisiMock);
        KisiDto result = kisiService.save(kisiDto);

        assertEquals(result.getAdi(), kisiDto.getAdi());
        assertEquals(result.getId(),1L);
    }


    /**
     * daha hızlı yazılım geliştirme
     * çok daha az hata içeren kod
     * testleri kodun çalıştırılıbilir örnek dökamanlarıın oluşturu
     * daha iyi tasarım sahip daha kaliteli kod
     * hataların daha kolay bulunması ve düzeltemi
     *
     * birim test
     * integrasyon testi
     *
     *
     */
}