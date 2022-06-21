package com.agenda.agenda_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agenda.agenda_api.model.entity.EmpresaGestora;

public interface EmpresaGestoraService {

	public abstract EmpresaGestora salvar(EmpresaGestora empresaGestora);
	
	public abstract EmpresaGestora alterar(EmpresaGestora empresaGestora);

	public abstract List<EmpresaGestora> listar();
	
	public abstract Page<EmpresaGestora> buscarPorParametros(EmpresaGestora empresaGestora, Pageable pageable);
	
	public abstract void remover(EmpresaGestora empresaGestora);
	
	public abstract Optional<EmpresaGestora> buscarPorId(Long id);

}
