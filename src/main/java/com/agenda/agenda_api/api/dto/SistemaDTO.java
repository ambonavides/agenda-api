package com.agenda.agenda_api.api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SistemaDTO {
	
	private Long id;
	private String url;
	private String descricao;
	private boolean ativo;

}
