package com.agenda.agenda_api.model.entity;

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
@Table(name = "sistema", schema = "agenda")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sistema {
	
	@Id
	@Column(name ="id", columnDefinition = "bigint")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "url", nullable = false)
	private String url;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "ativo")
	private boolean ativo;

}
