package com.agenda.agenda_api.service;

import java.util.List;
import java.util.Optional;

import com.agenda.agenda_api.model.entity.ConfiguracaoDocumento;

public interface ConfiguracaoEmpresaService {
	
	public abstract ConfiguracaoDocumento salvar(ConfiguracaoDocumento entity);
	
	public abstract ConfiguracaoDocumento alterar(ConfiguracaoDocumento entity);

	public abstract List<ConfiguracaoDocumento> listar();
	
	public abstract void remover(ConfiguracaoDocumento entity);

	public abstract Optional<ConfiguracaoDocumento> buscar(Long id);

}