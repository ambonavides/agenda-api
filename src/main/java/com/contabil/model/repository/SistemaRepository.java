package com.contabil.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contabil.model.entity.Sistema;

public interface SistemaRepository extends JpaRepository<Sistema, Long> {
	

}
