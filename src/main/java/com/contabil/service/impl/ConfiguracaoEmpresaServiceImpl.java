package com.contabil.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contabil.model.entity.ConfiguracaoDocumento;
import com.contabil.model.repository.ConfiguracaoEmpresaRepository;
import com.contabil.service.ConfiguracaoEmpresaService;

@Service
public class ConfiguracaoEmpresaServiceImpl implements ConfiguracaoEmpresaService {	
	
	@Autowired
	private ConfiguracaoEmpresaRepository repository;
	
	public ConfiguracaoEmpresaServiceImpl(ConfiguracaoEmpresaRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public List<ConfiguracaoDocumento> listar() {
		return repository.findAll();
	}
	
	
	@Override
	@Transactional
	public ConfiguracaoDocumento salvar(ConfiguracaoDocumento entidade) {
		return repository.save(entidade);
	}
	
	@Override
	@Transactional
	public ConfiguracaoDocumento alterar(ConfiguracaoDocumento entidade) {
		return repository.save(entidade);
	}

	@Override
	public void remover(ConfiguracaoDocumento entidade) {
		repository.delete(entidade);
	}

	@Override
	public Optional<ConfiguracaoDocumento> buscar(Long id) {
		return repository.findById(id);
	}
	
}
