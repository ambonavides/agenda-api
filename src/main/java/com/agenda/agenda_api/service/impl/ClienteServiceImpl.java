package com.agenda.agenda_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agenda.agenda_api.model.entity.Cliente;
import com.agenda.agenda_api.model.repository.ClienteRepository;
import com.agenda.agenda_api.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {	
	
	@Autowired
	private ClienteRepository repository;
	
	public ClienteServiceImpl(ClienteRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public List<Cliente> listar() {
		return repository.findAll();
	}
	
	@Override
	public Page<Cliente> buscarPorParametros(Cliente cliente, Pageable pageable) {
		return repository.filtrar(cliente.getNomeFantasia(), 
				cliente.getCodigo(),
				cliente.getCnpj(), 
				cliente.getUsuarioCadastro().getId(), 
				pageable);
	}
	
	@Override
	@Transactional
	public Cliente salvar(Cliente cliente) {
		return repository.save(cliente);
	}
	
	@Override
	@Transactional
	public Cliente alterar(Cliente cliente) {
		return repository.save(cliente);
	}

	@Transactional
	@Override
	public void remover(Cliente cliente) {
		repository.delete(cliente);
	}
	
	@Override
	public Optional<Cliente> buscarPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Cliente> buscarPorCodigo(int codigo) {
		return repository.findAllByCodigo(codigo);
	}

	
}
