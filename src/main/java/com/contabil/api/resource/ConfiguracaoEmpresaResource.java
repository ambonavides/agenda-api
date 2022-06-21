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

import com.contabil.api.dto.ConfiguracaoDocumentoDTO;
import com.contabil.exeption.RegraNegocioException;
import com.contabil.model.entity.ConfiguracaoDocumento;
import com.contabil.service.ConfiguracaoEmpresaService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/configuracao-empresa")
@RequiredArgsConstructor
public class ConfiguracaoEmpresaResource {
	
	private final ConfiguracaoEmpresaService service;
	
	@GetMapping("/listar-todos")
	public List<ConfiguracaoDocumentoDTO> listar() {
		List<ConfiguracaoDocumento> lista = service.listar();
		
		List<ConfiguracaoDocumentoDTO> listaRetorno = new ArrayList<>();
		for(ConfiguracaoDocumento u: lista) {
			listaRetorno.add(ConfiguracaoDocumentoDTO.builder()
					.id(u.getId())
					.descricaoServicoDeclaracaoPre(u.getDescricaoServicoDeclaracaoPre())
					.descricaoServicoDeclaracaoTom(u.getDescricaoServicoDeclaracaoTom())
					.descricaoArquivoXmlPre(u.getDescricaoArquivoXmlPre())
					.descricaoArquivoXmlTom(u.getDescricaoArquivoXmlTom())
					.descricaoArquivoLivroPre(u.getDescricaoArquivoLivroPre())
					.descricaoArquivoLivroTom(u.getDescricaoArquivoLivroTom())
					.descricaoArquivoIssPre(u.getDescricaoArquivoIssPre())
					.descricaoArquivoIssTom(u.getDescricaoArquivoIssTom())
					.build());
		}
		return listaRetorno;
	}
	
	@GetMapping("{id}")
	public ConfiguracaoDocumentoDTO buscar(@PathVariable("id") String id) {
		ConfiguracaoDocumento conf = service.buscar(Long.parseLong(id)).get();
		
		return ConfiguracaoDocumentoDTO.builder()
					.id(conf.getId())
					.descricaoServicoDeclaracaoPre(conf.getDescricaoServicoDeclaracaoPre())
					.descricaoServicoDeclaracaoTom(conf.getDescricaoServicoDeclaracaoTom())
					.descricaoArquivoXmlPre(conf.getDescricaoArquivoXmlPre())
					.descricaoArquivoXmlTom(conf.getDescricaoArquivoXmlTom())
					.descricaoArquivoLivroPre(conf.getDescricaoArquivoLivroPre())
					.descricaoArquivoLivroTom(conf.getDescricaoArquivoLivroTom())
					.descricaoArquivoIssPre(conf.getDescricaoArquivoIssPre())
					.descricaoArquivoIssTom(conf.getDescricaoArquivoIssTom())
					.build();
	}

	@PostMapping("/salvar")
	public ResponseEntity salvar(@RequestBody ConfiguracaoDocumentoDTO dto) {
		try {
			ConfiguracaoDocumento cliente = ConfiguracaoDocumento.builder()
					.id(dto.getId())
					.build();
			
			ConfiguracaoDocumento clienteSalvo = service.salvar(cliente);
			return new ResponseEntity(clienteSalvo, HttpStatus.CREATED);
		}catch (RegraNegocioException re) {
			return ResponseEntity.badRequest().body(re.getMessage());
		}
	}
	
	@PostMapping("/alterar")
	public ResponseEntity alterar(@RequestBody ConfiguracaoDocumentoDTO dto) {
		try {
			ConfiguracaoDocumento cliente = ConfiguracaoDocumento.builder()
					.id(dto.getId())
					.build();
			
			ConfiguracaoDocumento clienteSalvo = service.alterar(cliente);
			return new ResponseEntity(clienteSalvo, HttpStatus.CREATED);
		}catch (RegraNegocioException re) {
			return ResponseEntity.badRequest().body(re.getMessage());
		}
	}
	
	@DeleteMapping("/remover/{id}")
	public ResponseEntity remover(@PathVariable("id") String id) {
		try {
			ConfiguracaoDocumento cliente = ConfiguracaoDocumento.builder()
					.id(Long.valueOf(id))
					.build();
			this.service.remover(cliente);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}catch (RegraNegocioException re) {
			return ResponseEntity.badRequest().body(re.getMessage());
		}
	}

}
