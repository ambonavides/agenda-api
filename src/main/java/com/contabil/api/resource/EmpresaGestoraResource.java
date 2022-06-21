package com.contabil.api.resource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contabil.api.dto.ClienteDTO;
import com.contabil.api.dto.ConfiguracaoClienteDTO;
import com.contabil.api.dto.ConfiguracaoDocumentoDTO;
import com.contabil.api.dto.EmpresaGestoraDTO;
import com.contabil.api.dto.SistemaDTO;
import com.contabil.api.dto.UsuarioDTO;
import com.contabil.exeption.RegraNegocioException;
import com.contabil.model.entity.Cliente;
import com.contabil.model.entity.ConfiguracaoCliente;
import com.contabil.model.entity.ConfiguracaoDocumento;
import com.contabil.model.entity.ERole;
import com.contabil.model.entity.EmpresaGestora;
import com.contabil.model.entity.Role;
import com.contabil.model.entity.Sistema;
import com.contabil.model.entity.Usuario;
import com.contabil.model.repository.RoleRepository;
import com.contabil.model.repository.UsuarioRepository;
import com.contabil.service.EmpresaGestoraService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/empresa")
@RequiredArgsConstructor
public class EmpresaGestoraResource {
	
	private final EmpresaGestoraService service;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PostMapping("/listar/{page}/{row}")
	public Page<EmpresaGestoraDTO> listar(@RequestBody EmpresaGestoraDTO dto, @PathVariable int page, @PathVariable int row) {
		EmpresaGestora empresaGestora = empresaDtoToEmpresaEntity(dto);
		
		Pageable pageable = PageRequest.of(page, row, Sort.Direction.DESC, "id");
		
		Page<EmpresaGestora> lista = service.buscarPorParametros(empresaGestora, pageable);
		
		List<EmpresaGestoraDTO> listaRetorno = new ArrayList<EmpresaGestoraDTO>();
		for(EmpresaGestora emp: lista) {
			listaRetorno.add(empresaEntityToEmpresaDto(emp));
		}
		
		Page<EmpresaGestoraDTO> empresas = new PageImpl<>(listaRetorno, pageable, lista.getTotalElements());
		return empresas;
	}
	
	@GetMapping("/listar-todos")
	public List<EmpresaGestoraDTO> listar() {
		List<EmpresaGestora> lista = service.listar();
		
		List<EmpresaGestoraDTO> listaRetorno = new ArrayList<EmpresaGestoraDTO>();
		for(EmpresaGestora empresaGestora: lista) {
			listaRetorno.add(empresaEntityToEmpresaDto(empresaGestora));
		}
		return listaRetorno;
	}

	@PostMapping("/salvar")
	public ResponseEntity salvar(@RequestBody EmpresaGestoraDTO dto) {
		try {
			EmpresaGestora empresaGestora = empresaDtoToEmpresaEntity(dto);
			EmpresaGestora empresaSalvo = service.salvar(empresaGestora);
			return new ResponseEntity(empresaSalvo, HttpStatus.CREATED);
		}catch (RegraNegocioException re) {
			return ResponseEntity.badRequest().body(re.getMessage());
		}
	}

	@PostMapping("/alterar")
	public ResponseEntity alterar(@RequestBody EmpresaGestoraDTO dto) {
		try {
			EmpresaGestora empresaGestora = empresaDtoToEmpresaEntity(dto);
			EmpresaGestora empresaSalvo = service.alterar(empresaGestora);
			return new ResponseEntity(empresaSalvo, HttpStatus.CREATED);
		}catch (RegraNegocioException re) {
			return ResponseEntity.badRequest().body(re.getMessage());
		}
	}
	
	@DeleteMapping("/remover/{id}")
	public ResponseEntity remover(@PathVariable("id") long id) {
		try {
			EmpresaGestora empresaGestora = EmpresaGestora.builder()
					.id(id)
					.build();
			this.service.remover(empresaGestora);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}catch (RegraNegocioException re) {
			return ResponseEntity.badRequest().body(re.getMessage());
		}
	}
	
	@GetMapping("/buscar-por-id/{id}")
	public EmpresaGestoraDTO buscarPorId(@PathVariable("id") long id) {
		Optional<EmpresaGestora> empresaGestoraOpt = service.buscarPorId(id);
		return this.empresaEntityToEmpresaDto(empresaGestoraOpt.get());
	}
	
	private EmpresaGestora empresaDtoToEmpresaEntity(EmpresaGestoraDTO dto) {
		EmpresaGestora empresaGestora = EmpresaGestora.builder().build();
		Usuario usuario = Usuario.builder().build();
		Usuario usuarioCadastro = Usuario.builder().build();
		ConfiguracaoDocumento configuracaoDocumento = ConfiguracaoDocumento.builder().build();
		Sistema sistema = Sistema.builder().build();
		
		empresaGestora.setId(dto.getId());
		empresaGestora.setNome(dto.getNome());
		empresaGestora.setTelefone(dto.getTelefone());
		empresaGestora.setDescricao(dto.getDescricao());
		empresaGestora.setCnpj(dto.getCnpj());
		empresaGestora.setConfiguracaoDocumento(configuracaoDocumento);
		
		if(dto.getUsuarioDTO() != null) {
			usuario.setId(dto.getUsuarioDTO().getId());
			usuario.setLogin(dto.getUsuarioDTO().getLogin());
			if(!StringUtils.isEmpty(dto.getUsuarioDTO().getSenha())) {
				usuario.setSenha(encoder.encode(dto.getUsuarioDTO().getSenha()));
			}else {
				usuario.setSenha("");
			}
			usuario.setEmail(dto.getUsuarioDTO().getEmail());
			usuario.setAtivo(dto.getUsuarioDTO().getAtivo());
			usuario.setRoles(new HashSet<>());
			
			Set<Role> roles = new HashSet<>();
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		}
		
		if(!ObjectUtils.isEmpty(dto.getUsuarioCadastroDTO()) && 
				!ObjectUtils.isEmpty(dto.getUsuarioCadastroDTO().getId())) {
			Optional<Usuario> usuarioRetorno = this.usuarioRepository
					.findById(dto.getUsuarioCadastroDTO().getId().longValue());
			usuarioCadastro = usuarioRetorno.get();
		}
		
		if(dto.getConfiguracaoDocumentoDTO() != null) {
			configuracaoDocumento.setId(dto.getConfiguracaoDocumentoDTO().getId());
			configuracaoDocumento.setDescricaoServicoDeclaracaoPre(dto.getConfiguracaoDocumentoDTO().getDescricaoServicoDeclaracaoPre());
			configuracaoDocumento.setDescricaoServicoDeclaracaoTom(dto.getConfiguracaoDocumentoDTO().getDescricaoServicoDeclaracaoTom());
			configuracaoDocumento.setDescricaoArquivoLivroPre(dto.getConfiguracaoDocumentoDTO().getDescricaoArquivoLivroPre());
			configuracaoDocumento.setDescricaoArquivoLivroTom(dto.getConfiguracaoDocumentoDTO().getDescricaoArquivoLivroTom());
			configuracaoDocumento.setDescricaoArquivoIssPre(dto.getConfiguracaoDocumentoDTO().getDescricaoArquivoIssPre());
			configuracaoDocumento.setDescricaoArquivoIssTom(dto.getConfiguracaoDocumentoDTO().getDescricaoArquivoIssTom());
			configuracaoDocumento.setDescricaoArquivoXmlPre(dto.getConfiguracaoDocumentoDTO().getDescricaoArquivoXmlPre());
			configuracaoDocumento.setDescricaoArquivoXmlTom(dto.getConfiguracaoDocumentoDTO().getDescricaoArquivoXmlTom());
		}
		
		empresaGestora.setUsuario(usuario);
		empresaGestora.setUsuarioCadastro(usuarioCadastro);
		
		Cliente clienteParse = null;
		if(dto.getClientesDTO() != null) {
			for(ClienteDTO c: dto.getClientesDTO()) {
				clienteParse = new Cliente();
				clienteParse.setId(c.getId());
				clienteParse.setCnpj(c.getCnpj());
				clienteParse.setNomeFantasia(c.getNomeFantasia());

				ConfiguracaoCliente configuracaoCliente = null;
				for(ConfiguracaoClienteDTO conf: c.getConfiguracaoClientesDTO()) {
					configuracaoCliente = ConfiguracaoCliente.builder()
							.id(conf.getId())
							.flagDeclaracaoPre(conf.isFlagDeclaracaoPre())
							.flagDeclaracaoTom(conf.isFlagDeclaracaoTom())
							.flagLivroPre(conf.isFlagLivroPre())
							.flagLivroTom(conf.isFlagLivroTom())
							.flagXmlPre(conf.isFlagXmlPre())
							.flagXmlTom(conf.isFlagXmlTom())
							.flagIssPre(conf.isFlagIssPre())
							.flagIssTom(conf.isFlagIssTom())
							.login(conf.getLogin())
							.senha(conf.getSenha())
							.botAtivo(conf.isBotAtivo())
							.build();
					
					sistema.setId(conf.getSistemaDTO().getId());
					sistema.setUrl(conf.getSistemaDTO().getUrl());
					sistema.setDescricao(conf.getSistemaDTO().getDescricao());
					sistema.setAtivo(conf.getSistemaDTO().isAtivo());
				}
				clienteParse.getConfiguracoesClientes().add(configuracaoCliente);
				empresaGestora.getClientes().add(clienteParse);
			}
		}

		return empresaGestora;
	}
	
	private EmpresaGestoraDTO empresaEntityToEmpresaDto(EmpresaGestora entity) {
		EmpresaGestoraDTO empresaGestoraDTO = EmpresaGestoraDTO.builder().build();
		UsuarioDTO usuarioDTO = UsuarioDTO.builder().build();
		UsuarioDTO usurioCadastroDTO = UsuarioDTO.builder().build();
		ConfiguracaoDocumentoDTO configuracaoDocumentoDTO = ConfiguracaoDocumentoDTO.builder().build();
		SistemaDTO sistemaDTO = SistemaDTO.builder().build();
		
		empresaGestoraDTO.setId(entity.getId());
		empresaGestoraDTO.setNome(entity.getNome());
		empresaGestoraDTO.setDescricao(entity.getDescricao());
		empresaGestoraDTO.setTelefone(entity.getTelefone());
		empresaGestoraDTO.setCnpj(entity.getCnpj());
		empresaGestoraDTO.setConfiguracaoDocumentoDTO(configuracaoDocumentoDTO);
		
		usuarioDTO.setId(entity.getUsuario().getId());
		usuarioDTO.setLogin(entity.getUsuario().getLogin());
		usuarioDTO.setSenha(entity.getUsuario().getSenha());
		usuarioDTO.setEmail(entity.getUsuario().getEmail());
		usuarioDTO.setAtivo(entity.getUsuario().getAtivo());
		
		configuracaoDocumentoDTO.setId(entity.getConfiguracaoDocumento().getId());
		configuracaoDocumentoDTO.setDescricaoServicoDeclaracaoPre(entity.getConfiguracaoDocumento().getDescricaoServicoDeclaracaoPre());
		configuracaoDocumentoDTO.setDescricaoServicoDeclaracaoTom(entity.getConfiguracaoDocumento().getDescricaoServicoDeclaracaoTom());
		configuracaoDocumentoDTO.setDescricaoArquivoLivroPre(entity.getConfiguracaoDocumento().getDescricaoArquivoLivroPre());
		configuracaoDocumentoDTO.setDescricaoArquivoLivroTom(entity.getConfiguracaoDocumento().getDescricaoArquivoLivroTom());
		configuracaoDocumentoDTO.setDescricaoArquivoIssPre(entity.getConfiguracaoDocumento().getDescricaoArquivoIssPre());
		configuracaoDocumentoDTO.setDescricaoArquivoIssTom(entity.getConfiguracaoDocumento().getDescricaoArquivoIssTom());
		configuracaoDocumentoDTO.setDescricaoArquivoXmlPre(entity.getConfiguracaoDocumento().getDescricaoArquivoXmlPre());
		configuracaoDocumentoDTO.setDescricaoArquivoXmlTom(entity.getConfiguracaoDocumento().getDescricaoArquivoXmlTom());
		
		empresaGestoraDTO.setUsuarioDTO(usuarioDTO);
		empresaGestoraDTO.setUsuarioCadastroDTO(usurioCadastroDTO);
		
		ClienteDTO clienteDTOParse = null;
		for(Cliente c: entity.getClientes()) {
			clienteDTOParse = ClienteDTO.builder().build();
			clienteDTOParse.setId(c.getId());
			clienteDTOParse.setCnpj(c.getCnpj());
			clienteDTOParse.setNomeFantasia(c.getNomeFantasia());

			ConfiguracaoClienteDTO configuracaoDTOCliente = null;
			for(ConfiguracaoCliente conf: c.getConfiguracoesClientes()) {
				configuracaoDTOCliente = ConfiguracaoClienteDTO.builder()
						.id(conf.getId())
						.flagDeclaracaoPre(conf.isFlagDeclaracaoPre())
						.flagDeclaracaoTom(conf.isFlagDeclaracaoTom())
						.flagLivroPre(conf.isFlagLivroPre())
						.flagLivroTom(conf.isFlagLivroTom())
						.flagXmlPre(conf.isFlagXmlPre())
						.flagXmlTom(conf.isFlagXmlTom())
						.flagIssPre(conf.isFlagIssPre())
						.flagIssTom(conf.isFlagIssTom())
						.login(conf.getLogin())
						.senha(conf.getSenha())
						.botAtivo(conf.isBotAtivo())
						.build();
				sistemaDTO.setId(conf.getSistema().getId());
				sistemaDTO.setUrl(conf.getSistema().getUrl());
				sistemaDTO.setDescricao(conf.getSistema().getDescricao());
				sistemaDTO.setAtivo(conf.getSistema().isAtivo());
			}
			clienteDTOParse.getConfiguracaoClientesDTO().add(configuracaoDTOCliente);
			empresaGestoraDTO.getClientesDTO().add(clienteDTOParse);
		}

		return empresaGestoraDTO;
	}
	

}
