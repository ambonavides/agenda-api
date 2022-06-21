START POSTGRES MANUAL
"C:\ambiente\ferramentas\PostgreSQL\13\bin\pg_ctl.exe" -D "C:\ambiente\ferramentas\PostgreSQL\13\data" restart


--SCRIPT PARA FUNCIONAR O LOGIN
INSERT INTO CONTABIL.SISTEMA (ID, URL, DESCRICAO, ATIVO)
VALUES(
	NEXTVAL('contabil.sistema_id_seq'), 
	'www.joaopessoa.pb.gov.br', 
	'PMJP', 
	true
);


INSERT INTO contabil.roles(name) VALUES('ROLE_USER');
INSERT INTO contabil.roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO contabil.roles(name) VALUES('ROLE_ADMIN');

insert into contabil.usuario(login, email, senha, ativo)
values('triad', 'triad@triad.com', '$2a$10$zdKdUO/kEWvLhtR824gZJ.l9D0fHGoHWIWiPi5UNT7rRTOuM/jeWm', true);
-- senha == 123456


insert into contabil.user_roles(user_id, role_id)
values(1, 1);

commit;

select * from contabil.roles;
select * from contabil.usuario;
select * from contabil.user_roles;

UTILIZAR DOCKER VIA DOCKER

```sh
docker pull postgres
```

```sh
docker run -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -d --name postgres-on-docker -p 5432:5432 postgres
```
