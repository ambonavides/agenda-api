package com.contabil.api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ConfiguracaoClienteDTO {
	
	private Long id;
	private SistemaDTO sistemaDTO;
	private boolean flagDeclaracaoPre;
	private boolean flagDeclaracaoTom;
	private boolean flagLivroPre;
	private boolean flagLivroTom;
	private boolean flagXmlPre;
	private boolean flagXmlTom;
	private boolean flagIssPre;
	private boolean flagIssTom;
	private String login;
	private String senha;
	private boolean botAtivo;
	
}
