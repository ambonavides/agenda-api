package com.contabil.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contabil.model.entity.ConfiguracaoCliente;

public interface ConfiguracaoClienteRepository extends JpaRepository<ConfiguracaoCliente, Long> {

	List<ConfiguracaoCliente> findAllByClienteCodigo(int codigo);
	

}
