package com.contabil.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empresa", schema = "contabil")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaGestora implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 939362778585367432L;

	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "cnpj", nullable = false)
	private String cnpj;
	
	@Column(name = "telefone")
	private String telefone;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usuario_cadastro")
	private Usuario usuarioCadastro;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_configuracao_documento")
	private ConfiguracaoDocumento configuracaoDocumento;
	
	@OneToMany(fetch=FetchType.EAGER, orphanRemoval=false)
	@JoinColumn(name = "id_cliente", insertable=false, updatable=false)
	private Set<Cliente> clientes = new HashSet<Cliente>(0);

}
