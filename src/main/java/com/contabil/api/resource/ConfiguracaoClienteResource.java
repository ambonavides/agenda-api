package com.contabil.api.resource;

import java.util.ArrayList;
import java.util.List;

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

import com.contabil.api.dto.ConfiguracaoClienteDTO;
import com.contabil.exeption.RegraNegocioException;
import com.contabil.model.entity.ConfiguracaoCliente;
import com.contabil.service.ConfiguracaoClienteService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/configuracao-cliente")
@RequiredArgsConstructor
public class ConfiguracaoClienteResource {
	
	private final ConfiguracaoClienteService service;
	
	@GetMapping("/listar-todos")
	public List<ConfiguracaoClienteDTO> listar() {
		List<ConfiguracaoCliente> lista = service.listar();
		
		List<ConfiguracaoClienteDTO> listaRetorno = new ArrayList<ConfiguracaoClienteDTO>();
		for(ConfiguracaoCliente u: lista) {
			listaRetorno.add(ConfiguracaoClienteDTO.builder()
					.id(u.getId())
					.build());
		}
		return listaRetorno;
	}
	
	@GetMapping("{codigo}")
	public List<ConfiguracaoClienteDTO> buscar(@PathVariable("codigo") String codigo) {
		List<ConfiguracaoCliente> lista = service.buscar(Integer.parseInt(codigo));
		
		List<ConfiguracaoClienteDTO> listaRetorno = new ArrayList<>();
		for(ConfiguracaoCliente u: lista) {
			listaRetorno.add(ConfiguracaoClienteDTO.builder()
					.id(u.getId())
					.build());
		}
		return listaRetorno;
	}

	@PostMapping("/salvar")
	public ResponseEntity salvar(@RequestBody ConfiguracaoClienteDTO dto) {
		try {
			ConfiguracaoCliente cliente = ConfiguracaoCliente.builder()
					.id(dto.getId())
					.build();
			
			ConfiguracaoCliente clienteSalvo = service.salvar(cliente);
			return new ResponseEntity(clienteSalvo, HttpStatus.CREATED);
		}catch (RegraNegocioException re) {
			return ResponseEntity.badRequest().body(re.getMessage());
		}
	}
	
	@PostMapping("/alterar")
	public ResponseEntity alterar(@RequestBody ConfiguracaoClienteDTO dto) {
		try {
			ConfiguracaoCliente cliente = ConfiguracaoCliente.builder()
					.id(dto.getId())
					.build();
			
			ConfiguracaoCliente clienteSalvo = service.alterar(cliente);
			return new ResponseEntity(clienteSalvo, HttpStatus.CREATED);
		}catch (RegraNegocioException re) {
			return ResponseEntity.badRequest().body(re.getMessage());
		}
	}
	
	@DeleteMapping("/remover/{id}")
	public ResponseEntity remover(@PathVariable("id") String id) {
		try {
			ConfiguracaoCliente cliente = ConfiguracaoCliente.builder()
					.id(Long.valueOf(id))
					.build();
			this.service.remover(cliente);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}catch (RegraNegocioException re) {
			return ResponseEntity.badRequest().body(re.getMessage());
		}
	}

}
