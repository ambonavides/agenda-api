package com.contabil.model.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.contabil.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Query(value="SELECT * FROM contabil.cliente u WHERE "
			+ "(:COD_USUARIO_CADASTRO = 1 OR u.id_usuario_cadastro = :COD_USUARIO_CADASTRO ) "
			+ "AND (:NOME IS NULL OR UPPER(u.nome_fantasia) LIKE TRIM(UPPER(CONCAT('%',:NOME,'%')))) "
			+ "AND ((:CODIGO IS NULL OR :CODIGO = 0) OR u.codigo_cliente = :CODIGO) "
			+ "AND (:CNPJ IS NULL OR u.cnpj LIKE CONCAT('',:CNPJ,'')) "
			,countQuery = "SELECT COUNT(*) FROM contabil.cliente u WHERE "
			+ "(:COD_USUARIO_CADASTRO = 1 OR u.id_usuario_cadastro = :COD_USUARIO_CADASTRO ) "
			+ "AND (:NOME is null OR UPPER(u.nome_fantasia) LIKE TRIM(UPPER(CONCAT('%',:NOME,'%')))) "
			+ "AND ((:CODIGO IS NULL OR :CODIGO = 0) OR u.codigo_cliente = :CODIGO) "
			+ "AND (:CNPJ IS NULL OR u.cnpj LIKE CONCAT('',:CNPJ,'')) "
			,nativeQuery= true)
	public abstract Page<Cliente> filtrar(@Param("NOME") String nome, 
										@Param("CODIGO") int codigo, 
										@Param("CNPJ") String cnpj, 
										@Param("COD_USUARIO_CADASTRO") long codUsuario,
										Pageable pageable);

	public abstract Optional<Cliente> findAllByCodigo(int codigo);

}
