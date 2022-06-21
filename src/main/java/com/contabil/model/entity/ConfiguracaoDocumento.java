package com.contabil.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "configuracao_empresa", schema = "contabil")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracaoDocumento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1279366061098782575L;

	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao_servivo_declaracao_pre")
	private String descricaoServicoDeclaracaoPre;
	
	@Column(name = "descricao_servivo_declaracao_tom")
	private String descricaoServicoDeclaracaoTom;
	
	@Column(name = "descricao_arquivo_livro_pre")
	private String descricaoArquivoLivroPre;
	
	@Column(name = "descricao_arquivo_livro_tom")
	private String descricaoArquivoLivroTom;
	
	@Column(name = "descricao_arquivo_xml_pre")
	private String descricaoArquivoXmlPre;

	@Column(name = "descricao_arquivo_xml_tom")
	private String descricaoArquivoXmlTom;
	
	@Column(name = "descricao_arquivo_iss_pre")
	private String descricaoArquivoIssPre;
	
	@Column(name = "descricao_arquivo_iss_tom")
	private String descricaoArquivoIssTom;
	
}
