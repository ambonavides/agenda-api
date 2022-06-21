package com.agenda.agenda_api.api.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.agenda_api.api.dto.UsuarioDTO;
import com.agenda.agenda_api.exeption.ErroAutenticacao;
import com.agenda.agenda_api.exeption.RegraNegocioException;
import com.agenda.agenda_api.model.entity.Usuario;
import com.agenda.agenda_api.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioResource {
		
		private final UsuarioService service;
		
		@PostMapping("/autenticar")
		public ResponseEntity autenticar(@RequestBody Usuario dto) {
			try {
				Usuario usuarioAutenticado = service.autenticar(dto.getLogin(), dto.getSenha());
				return ResponseEntity.ok(usuarioAutenticado);
			}catch(ErroAutenticacao ea) {
				return ResponseEntity.badRequest().body(ea.getMessage());
			}
		}
		
		@GetMapping("/listar-todos")
		public List<UsuarioDTO> listar() {
			List<Usuario> lista = service.listar();
			
			List<UsuarioDTO> listaRetorno = new ArrayList<UsuarioDTO>();
			for(Usuario u: lista) {
				listaRetorno.add(UsuarioDTO.builder()
						.id(u.getId())
						.email(u.getEmail())
						.senha("********")
						.ativo(u.getAtivo())
						.login(u.getLogin())
						.ativo(u.getAtivo())
						.build());
			}
			return listaRetorno;
		}
		
		@PostMapping("/listar/{page}/{row}")
		public Page<UsuarioDTO> listar(@RequestBody UsuarioDTO dto, @PathVariable int page, @PathVariable int row) {
			Usuario usuario = Usuario.builder()
					.email(dto.getEmail())
					.senha(dto.getSenha())
					.ativo(dto.getAtivo())
					.login(dto.getLogin())
					.build();
			
			Pageable pageable = PageRequest.of(page, row, Sort.Direction.ASC, "nome");
			
			Page<Usuario> lista = service.buscarPorParametros(usuario, pageable);
			
			List<UsuarioDTO> listaRetorno = new ArrayList<UsuarioDTO>();
			for(Usuario u: lista) {
				listaRetorno.add(UsuarioDTO.builder()
						.id(u.getId())
						.email(u.getEmail())
						.senha(u.getSenha())
						.ativo(u.getAtivo())
						.login(u.getLogin())
						.build());
			}
			
			Page<UsuarioDTO> usuarios = new PageImpl<>(listaRetorno, pageable, lista.getTotalElements());
			
			return usuarios;
		}
	
		@PostMapping("/salvar")
		public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
			try {
				Usuario usuario = Usuario.builder()
					.email(dto.getEmail())
					.senha(dto.getSenha())
					.ativo(dto.getAtivo())
					.login(dto.getLogin())
					.build();
				Usuario usuarioSalvo = service.salvar(usuario);
				return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
			}catch (RegraNegocioException re) {
				return ResponseEntity.badRequest().body(re.getMessage());
			}
		}
		
		@PostMapping("/alterar")
		public ResponseEntity alterar(@RequestBody UsuarioDTO dto) {
			try {
				Usuario usuario = Usuario.builder()
					.id(dto.getId())
					.email(dto.getEmail())
					.senha(dto.getSenha())
					.ativo(dto.getAtivo())
					.login(dto.getLogin())
					.build();
				Usuario usuarioSalvo = service.alterar(usuario);
				return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
			}catch (RegraNegocioException re) {
				return ResponseEntity.badRequest().body(re.getMessage());
			}
		}
		
		@DeleteMapping("/remover/{id}")
		public ResponseEntity remover(@PathVariable("id") long id) {
			try {
				Usuario usuario = Usuario.builder().id(id).build();
				this.service.remover(usuario);
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			}catch (RegraNegocioException re) {
				return ResponseEntity.badRequest().body(re.getMessage());
			}
		}
		
		
		
}