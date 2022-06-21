package com.agenda.agenda_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agenda.agenda_api.model.entity.Cliente;

public interface ClienteService {
	
	public abstract Cliente salvar(Cliente cliente);
	
	public abstract Cliente alterar(Cliente cliente);

	public abstract List<Cliente> listar();
	
	public abstract Page<Cliente> buscarPorParametros(Cliente cliente, Pageable pageable);
	
	public abstract void remover(Cliente cliente);

	public abstract Optional<Cliente> buscarPorId(Long id);

	public abstract Optional<Cliente> buscarPorCodigo(int codigo);

	
}
