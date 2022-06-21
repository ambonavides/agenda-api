package com.agenda.agenda_api.service;

import java.util.List;

import com.agenda.agenda_api.model.entity.Sistema;

public interface SistemaService {
	
	public abstract Sistema salvar(Sistema entity);
	
	public abstract Sistema alterar(Sistema entity);

	public abstract List<Sistema> listar();
	
	public abstract void remover(Sistema entity);

}
