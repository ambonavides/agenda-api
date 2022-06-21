package com.agenda.agenda_api.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.agenda.agenda_api.model.entity.Usuario;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmpresaGestoraDTO {
	
	private Long id;
	private String nome;
	private String descricao;
	private String cnpj;
	private String telefone;
	private UsuarioDTO usuarioDTO;
	private UsuarioDTO usuarioCadastroDTO;
	private ConfiguracaoDocumentoDTO configuracaoDocumentoDTO;
	private List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();


}
