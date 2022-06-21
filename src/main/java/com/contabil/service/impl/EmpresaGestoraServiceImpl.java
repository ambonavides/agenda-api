package com.contabil.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contabil.exeption.ErroAutenticacao;
import com.contabil.model.entity.EmpresaGestora;
import com.contabil.model.repository.EmpresaGestoraRepository;
import com.contabil.service.EmpresaGestoraService;

@Service
public class EmpresaGestoraServiceImpl implements EmpresaGestoraService {
	
	@Autowired
	private EmpresaGestoraRepository repository;
	
	public EmpresaGestoraServiceImpl(EmpresaGestoraRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public List<EmpresaGestora> listar() {
		return repository.findAll();
	}
	
	@Override
	public Page<EmpresaGestora> buscarPorParametros(EmpresaGestora empresaGestora, Pageable pageable) {
		Page<EmpresaGestora> saida = repository.filtrar(empresaGestora.getCnpj(), empresaGestora.getNome(), 
														empresaGestora.getUsuario().getId(), pageable);
		return saida;
	}
	
	
	@Override
	@Transactional
	public EmpresaGestora salvar(EmpresaGestora empresaGestora) {
		return repository.save(empresaGestora);
	}
	
	@Override
	@Transactional
	public EmpresaGestora alterar(EmpresaGestora empresaGestora) {
		return repository.save(empresaGestora);
	}

	@Override
	public void remover(EmpresaGestora empresaGestora) {
		repository.delete(empresaGestora);
	}
	
	@Override
	public Optional<EmpresaGestora> buscarPorId(Long id) {
		return repository.findById(id);
	}

}
