package com.agenda.agenda_api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.agenda_api.model.entity.Sistema;

public interface SistemaRepository extends JpaRepository<Sistema, Long> {
	

}
