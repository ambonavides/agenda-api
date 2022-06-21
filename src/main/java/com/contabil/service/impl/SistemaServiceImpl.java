package com.contabil.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contabil.model.entity.Sistema;
import com.contabil.model.repository.SistemaRepository;
import com.contabil.service.SistemaService;

@Service
public class SistemaServiceImpl implements SistemaService {	
	
	@Autowired
	private SistemaRepository repository;
	
	public SistemaServiceImpl(SistemaRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public List<Sistema> listar() {
		return repository.findAll();
	}
	
	
	@Override
	@Transactional
	public Sistema salvar(Sistema entidade) {
		return repository.save(entidade);
	}
	
	@Override
	@Transactional
	public Sistema alterar(Sistema entidade) {
		return repository.save(entidade);
	}

	@Override
	public void remover(Sistema entidade) {
		repository.delete(entidade);
	}
	
}
