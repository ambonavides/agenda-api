package com.agenda.agenda_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agenda.agenda_api.exeption.ErroAutenticacao;
import com.agenda.agenda_api.exeption.RegraNegocioException;
import com.agenda.agenda_api.model.entity.Usuario;
import com.agenda.agenda_api.model.repository.UsuarioRepository;
import com.agenda.agenda_api.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository repository;
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public List<Usuario> listar() {
		return repository.findAll();
	}
	
	@Override
	public Page<Usuario> buscarPorParametros(Usuario usuario, Pageable pageable) {
		return repository.filtrar(usuario.getEmail(), usuario.getLogin(), pageable);
	}
	
	
	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = repository.findByEmail(email);
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao("UsuarioDTO não encontrado para o email informado.");
		}
		
		if(!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha inválida.");
		}
		
		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvar(Usuario usuario) {
		this.validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}
	
	@Override
	@Transactional
	public Usuario alterar(Usuario usuario) {
		return repository.save(usuario);
	}


	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com este email.");
		}
	}

	@Override
	public Optional<Usuario> obterPorId(Long id) {
		return repository.findById(id);
	}
	
	@Override
	public void remover(Usuario usuario) {
		repository.delete(usuario);
	}

}