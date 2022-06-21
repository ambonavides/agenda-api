package com.agenda.agenda_api.api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ConfiguracaoDocumentoDTO {
	
	private Long id;
	private String descricaoServicoDeclaracaoPre;
	private String descricaoServicoDeclaracaoTom;
	private String descricaoArquivoLivroPre;
	private String descricaoArquivoLivroTom;
	private String descricaoArquivoXmlPre;
	private String descricaoArquivoXmlTom;
	private String descricaoArquivoIssPre;
	private String descricaoArquivoIssTom;
	
}
