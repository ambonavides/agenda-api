package com.agenda.agenda_api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.agenda_api.model.entity.ConfiguracaoDocumento;

public interface ConfiguracaoEmpresaRepository extends JpaRepository<ConfiguracaoDocumento, Long> {
	

}
