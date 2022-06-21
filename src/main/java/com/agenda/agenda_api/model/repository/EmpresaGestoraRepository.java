package com.agenda.agenda_api.model.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agenda.agenda_api.model.entity.EmpresaGestora;

public interface EmpresaGestoraRepository extends JpaRepository<EmpresaGestora, Long> {
	
	@Query(value="SELECT * FROM agenda.empresa u WHERE "
			+ "(:COD_USUARIO_CADASTRO = 1 OR u.id_usuario_cadastro = :COD_USUARIO_CADASTRO ) "
			+ "AND (:CNPJ is null OR UPPER(u.cnpj) LIKE CONCAT('%',:CNPJ,'%')) "
			+ "AND (:NOME is null OR UPPER(u.nome) LIKE CONCAT('%',:NOME,'%')) "
			,countQuery = "SELECT COUNT(*) FROM agenda.empresa u WHERE "
			+ "(:COD_USUARIO_CADASTRO = 1 OR u.id_usuario_cadastro = :COD_USUARIO_CADASTRO ) "
			+ "AND (:CNPJ is null or UPPER(u.cnpj) LIKE CONCAT('%',:CNPJ,'%')) "
			+ "AND (:NOME is null OR UPPER(u.nome)  LIKE CONCAT('%',:NOME,'%')) "
			,nativeQuery= true)
	public abstract Page<EmpresaGestora> filtrar(@Param("CNPJ") String cnpj, 
												@Param("NOME") String nome, 
												@Param("COD_USUARIO_CADASTRO") long codUsuario,
												Pageable pageable);
	
	public abstract Optional<EmpresaGestora> findById(Long id);

}
