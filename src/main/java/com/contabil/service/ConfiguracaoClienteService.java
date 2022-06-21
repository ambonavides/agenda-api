package com.contabil.service;

import java.util.List;

import com.contabil.model.entity.ConfiguracaoCliente;

public interface ConfiguracaoClienteService {
	
	public abstract ConfiguracaoCliente salvar(ConfiguracaoCliente entity);
	
	public abstract ConfiguracaoCliente alterar(ConfiguracaoCliente entity);

	public abstract List<ConfiguracaoCliente> listar();
	
	public abstract List<ConfiguracaoCliente> buscar(int codigo);
	
	public abstract void remover(ConfiguracaoCliente entity);

}
