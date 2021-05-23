package com.example.spring.Service;

import com.example.spring.dto.KisiDto;
import com.example.spring.entity.Kisi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KisiService {
    KisiDto save(KisiDto kisiDto);
    void delete(Long id);
    KisiDto update(KisiDto kisiDto);
    List<KisiDto> getAll();
    Kisi findKisiById(long id);
    KisiDto findid(long id);
    Page<KisiDto> getAll(Pageable pageable);
}
