package com.agenda.agenda_api.model.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agenda.agenda_api.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public abstract boolean existsByEmail(String email);

	public abstract Optional<Usuario> findByEmail(String nome);
	
	Optional<Usuario> findByLogin(String login);

	Boolean existsByLogin(String login);

	@Query(value="select * from usuario u where 1=1 "
			+ "and (:EMAIL is null or upper(u.email) like trim(upper(concat(:EMAIL)))) "
			+ "and (:LOGIN is null or upper(u.login) like trim(upper(concat(:LOGIN))))"
			
			,countQuery = "select count(*) from usuario u where 1=1 "
					+ "and (:EMAIL is null or upper(u.email) like trim(upper(concat(:EMAIL)))) "
					+ "and (:LOGIN is null or upper(u.login) like trim(upper(concat(:LOGIN))))"
			
			,nativeQuery= true)
	public abstract Page<Usuario> filtrar(@Param("EMAIL") String email,
											@Param("LOGIN") String login, Pageable pageable);
	
}
