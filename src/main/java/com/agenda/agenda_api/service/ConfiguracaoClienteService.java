package com.agenda.agenda_api.service;

import java.util.List;

import com.agenda.agenda_api.model.entity.ConfiguracaoCliente;

public interface ConfiguracaoClienteService {
	
	public abstract ConfiguracaoCliente salvar(ConfiguracaoCliente entity);
	
	public abstract ConfiguracaoCliente alterar(ConfiguracaoCliente entity);

	public abstract List<ConfiguracaoCliente> listar();
	
	public abstract List<ConfiguracaoCliente> buscar(int codigo);
	
	public abstract void remover(ConfiguracaoCliente entity);

}
