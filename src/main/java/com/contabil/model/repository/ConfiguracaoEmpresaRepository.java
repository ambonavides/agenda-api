package com.contabil.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contabil.model.entity.ConfiguracaoDocumento;

public interface ConfiguracaoEmpresaRepository extends JpaRepository<ConfiguracaoDocumento, Long> {
	

}
