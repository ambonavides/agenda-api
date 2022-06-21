package com.contabil.service;

import java.util.List;

import com.contabil.model.entity.Sistema;

public interface SistemaService {
	
	public abstract Sistema salvar(Sistema entity);
	
	public abstract Sistema alterar(Sistema entity);

	public abstract List<Sistema> listar();
	
	public abstract void remover(Sistema entity);

}
