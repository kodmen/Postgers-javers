package com.example.spring.repo;

import com.example.spring.entity.Kisi;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@JaversSpringDataAuditable
public interface KisiRepository extends JpaRepository<Kisi,Long> {
}
