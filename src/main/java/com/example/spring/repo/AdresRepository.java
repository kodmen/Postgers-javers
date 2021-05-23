package com.example.spring.repo;

import com.example.spring.entity.Adres;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;


@JaversSpringDataAuditable
public interface AdresRepository extends JpaRepository<Adres, Long> {
}
