package com.contabil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.contabil.model.entity.Usuario;

public interface UsuarioService {

	public abstract Usuario autenticar(String email, String senha);

	public abstract Usuario salvar(Usuario usuario);
	
	public abstract Usuario alterar(Usuario usuario);

	public abstract void validarEmail(String email);

	public abstract Optional<Usuario> obterPorId(Long id);

	public abstract List<Usuario> listar();
	
	public abstract Page<Usuario> buscarPorParametros(Usuario usuario, Pageable pageable);
	
	public abstract void remover(Usuario usuario);
}
