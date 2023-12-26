package com.agenda.agenda_api.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7958952006718831448L;

	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "codigo_cliente", nullable = false)
	private int codigo;
	
	@Column(name = "nome_fantasia", nullable = false)
	private String nomeFantasia;
	
	@Column(name = "cnpj", nullable = false)
	private String cnpj;
	
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "id_usuario_cadastro")
	private Usuario usuarioCadastro;
	
//	@OneToMany(mappedBy = "cliente", orphanRemoval = true, targetEntity = ConfiguracaoCliente.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private Set<ConfiguracaoCliente> configuracoesClientes = new HashSet<ConfiguracaoCliente>(0);
	
}