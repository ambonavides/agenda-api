package com.agenda.agenda_api.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "configuracao_cliente", schema = "agenda")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracaoCliente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7665181527106106808L;

	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JsonIgnore
	@JoinColumn(name = "id_sistema")
	private Sistema sistema;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	
	@Column(name = "fl_decla_pre")
	private boolean flagDeclaracaoPre;
	
	@Column(name = "fl_decla_tom")
	private boolean flagDeclaracaoTom;
	
	@Column(name = "fl_livro_pre")
	private boolean flagLivroPre;
	
	@Column(name = "fl_livro_tom")
	private boolean flagLivroTom;
	
	@Column(name = "fl_xml_pre")
	private boolean flagXmlPre;
	
	@Column(name = "fl_xml_tom")
	private boolean flagXmlTom;
	
	@Column(name = "fl_iss_pre")
	private boolean flagIssPre;
	
	@Column(name = "fl_iss_tom")
	private boolean flagIssTom;
	
	@Column(name = "login", nullable = false)
	private String login;
	
	@Column(name = "senha", nullable = false)
	private String senha;
	
	@Column(name = "ativacao_bot")
	private boolean botAtivo;
	

}
