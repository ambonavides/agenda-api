package com.agenda.agenda_api.api.dto;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClienteDTO {
	
	private Long id;
	private int codigo;
	private String nomeFantasia;
	private String cnpj;
	private UsuarioDTO usuarioCadastroDTO;
	private Set<ConfiguracaoClienteDTO> configuracaoClientesDTO;

}
