package com.agenda.agenda_api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.agenda_api.model.entity.ConfiguracaoCliente;

public interface ConfiguracaoClienteRepository extends JpaRepository<ConfiguracaoCliente, Long> {

	List<ConfiguracaoCliente> findAllByClienteCodigo(int codigo);
	

}
