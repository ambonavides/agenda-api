package com.contabil.api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UsuarioDTO {
	
	private Long id;
	private String login;
	private String senha;
	private String email;
	private Boolean ativo;
	
}