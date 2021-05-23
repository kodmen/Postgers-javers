package com.example.spring.Service.Impl;

import com.example.spring.Service.KisiService;
import com.example.spring.dto.KisiDto;
import com.example.spring.entity.Adres;
import com.example.spring.entity.Kisi;
import com.example.spring.repo.AdresRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class IKisiServiceImplIntegrationTest {

    @Autowired
    private KisiService kisiService;

    @Autowired
    private AdresRepository adresRepository;

    @Test
    void testSave() {
        KisiDto kisiDto = new KisiDto();
        kisiDto.setAdi("test-name");
        kisiDto.setSoyadi("test-lastname");
        kisiDto.setAdresler(Arrays.asList("test-adres-1"));

        KisiDto result = kisiService.save(kisiDto);
        List<Adres> liste = adresRepository.findAll();

        assertTrue(result.getId() > 0L);
        assertEquals(liste.size(), 1);
    }

}
