package com.contabil.api.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contabil.api.dto.EmpresaGestoraDTO;
import com.contabil.api.dto.SistemaDTO;
import com.contabil.model.entity.EmpresaGestora;
import com.contabil.model.entity.Sistema;
import com.contabil.service.EmpresaGestoraService;
import com.contabil.service.SistemaService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sistema")
@RequiredArgsConstructor
public class SistemaResource {
	
	private final SistemaService service;
	
	@GetMapping("/listar")
	public List<SistemaDTO> listar() {
		List<Sistema> lista = service.listar();
		
		List<SistemaDTO> listaRetorno = new ArrayList<SistemaDTO>();
		for(Sistema sistema: lista) {
			listaRetorno.add(sistemaEntityToSistemaDto(sistema));
		}
		return listaRetorno;
	}
	
	private SistemaDTO sistemaEntityToSistemaDto(Sistema entity) {
		SistemaDTO sistemaDTO = SistemaDTO.builder().build();
		sistemaDTO.setId(entity.getId());
		sistemaDTO.setUrl(entity.getUrl());
		sistemaDTO.setDescricao(entity.getDescricao());
		sistemaDTO.setAtivo(entity.isAtivo());
		return sistemaDTO;
	}	

}
