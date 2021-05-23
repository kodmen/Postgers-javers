package com.example.spring.Service.Impl;


import com.example.spring.Service.KisiService;
import com.example.spring.dto.KisiDto;
import com.example.spring.entity.Adres;
import com.example.spring.entity.Kisi;

import com.example.spring.repo.AdresRepository;
import com.example.spring.repo.KisiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IKisiServiceImpl implements KisiService {

    private final KisiRepository kisiRepository;
    private final AdresRepository adresRepository;


    @Override
    @Transactional
    public KisiDto save(KisiDto kisiDto) {
        Assert.notNull(kisiDto.getAdi(),"alan adi zorunludur");

        Kisi kisi = new Kisi();
        kisi.setAdi(kisiDto.getAdi());
        kisi.setSoyadi(kisiDto.getSoyadi());
        final Kisi kisiDb = kisiRepository.save(kisi);

        List<Adres> liste = new ArrayList<>();

        kisiDto.getAdresler().forEach(item -> {
            Adres adres = new Adres();
            adres.setAdres(item);
            adres.setAdresTip(Adres.AdresTip.DIGER);
            adres.setAktif(true);
            adres.setKisi(kisiDb);
            liste.add(adres);
        });
        adresRepository.saveAll(liste);
        kisiDto.setId(kisiDb.getId());
        return kisiDto;
    }

    @Override
    public void delete(Long id) {
        Optional<Kisi> kisi = kisiRepository.findById(id);
        kisi.ifPresent(kisi1 -> {
            kisiRepository.delete(kisi1);
        });
    }

    @Override
    public KisiDto update(KisiDto dto) {
        Kisi kisi = new Kisi();

        kisi.setId(dto.getId());
        kisi.setAdi(dto.getAdi());
        kisi.setSoyadi(dto.getSoyadi());
        final Kisi kisiDb = kisiRepository.save(kisi);

        List<Adres> liste = new ArrayList<>();

        dto.getAdresler().forEach(item -> {
            Adres adres = new Adres();
            adres.setAdres(item);
            adres.setAdresTip(Adres.AdresTip.DIGER);
            adres.setAktif(true);
            adres.setKisi(kisiDb);
            liste.add(adres);
        });
        adresRepository.saveAll(liste);
        dto.setId(kisiDb.getId());

        return dto;
    }

    @Override
    public List<KisiDto> getAll() {

        List<Kisi> kisiler = kisiRepository.findAll();
        List<KisiDto> kisiDtos = new ArrayList<>();
        kisiler.forEach(it -> {
            KisiDto kisiDto = new KisiDto();
            kisiDto.setId(it.getId());
            kisiDto.setAdi(it.getAdi());
            kisiDto.setSoyadi(it.getSoyadi());
            kisiDto.setAdresler(it.getAdresleri().stream().map(Adres::getAdres)
                    .collect(Collectors.toList()));
            kisiDtos.add(kisiDto);
        });
        return kisiDtos;
    }

    @Override
    public Kisi findKisiById(long id) {
        Optional<Kisi> kisi = kisiRepository.findById(id);
        if(kisi.isPresent()){
            return kisi.get();
        }else{
            return null;
        }

    }

    @Override
    public KisiDto findid(long id) {
    Kisi kisi = kisiRepository.findById(id).get();
    KisiDto kisiDto = KisiDto.builder()
            .id(kisi.getId())
            .adi(kisi.getAdi())
            .soyadi(kisi.getSoyadi())
            .adresler(null)
            .build();

       return kisiDto;
    }


    @Override
    public Page<KisiDto> getAll(Pageable pageable) {
        return null;
    }
}
