package com.contabil.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contabil.model.entity.ConfiguracaoCliente;
import com.contabil.model.repository.ConfiguracaoClienteRepository;
import com.contabil.service.ConfiguracaoClienteService;

@Service
public class ConfiguracaoClienteServiceImpl implements ConfiguracaoClienteService {	
	
	@Autowired
	private ConfiguracaoClienteRepository repository;
	
	public ConfiguracaoClienteServiceImpl(ConfiguracaoClienteRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public List<ConfiguracaoCliente> listar() {
		return repository.findAll();
	}
	
	
	@Override
	@Transactional
	public ConfiguracaoCliente salvar(ConfiguracaoCliente entidade) {
		return repository.save(entidade);
	}
	
	@Override
	@Transactional
	public ConfiguracaoCliente alterar(ConfiguracaoCliente entidade) {
		return repository.save(entidade);
	}

	@Override
	public void remover(ConfiguracaoCliente entidade) {
		repository.delete(entidade);
	}

	@Override
	public List<ConfiguracaoCliente> buscar(int codigo) {
		return repository.findAllByClienteCodigo(codigo);
	}
	
}
