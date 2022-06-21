package com.agenda.agenda_api.api.resource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.agenda_api.api.dto.ClienteDTO;
import com.agenda.agenda_api.api.dto.ConfiguracaoClienteDTO;
import com.agenda.agenda_api.api.dto.SistemaDTO;
import com.agenda.agenda_api.api.dto.UsuarioDTO;
import com.agenda.agenda_api.exeption.RegraNegocioException;
import com.agenda.agenda_api.model.entity.Cliente;
import com.agenda.agenda_api.model.entity.ConfiguracaoCliente;
import com.agenda.agenda_api.model.entity.Sistema;
import com.agenda.agenda_api.model.entity.Usuario;
import com.agenda.agenda_api.model.repository.UsuarioRepository;
import com.agenda.agenda_api.service.ClienteService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteResource {
	
	private final ClienteService service;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PostMapping("/listar/{page}/{row}")
	public Page<ClienteDTO> listar(@RequestBody ClienteDTO dto, @PathVariable int page, @PathVariable int row) {
		Cliente cliente = clienteDtoToClienteEntity(dto);
		
		Pageable pageable = PageRequest.of(page, row, Sort.Direction.ASC, "id");
		
		Page<Cliente> lista = service.buscarPorParametros(cliente, pageable);
		
		List<ClienteDTO> listaRetorno = new ArrayList<>();
		for(Cliente cli: lista) {
			listaRetorno.add(clienteEntityToClienteDto(cli));
		}
		
		Page<ClienteDTO> clientes = new PageImpl<>(listaRetorno, pageable, lista.getTotalElements());
		return clientes;
	}
	
	@GetMapping("/listar-todos")
	public List<ClienteDTO> listar() {
		List<Cliente> lista = service.listar();
		
		List<ClienteDTO> listaRetorno = new ArrayList<>();
		for(Cliente cli: lista) {
			listaRetorno.add(clienteEntityToClienteDto(cli));
		}
		return listaRetorno;
	}
	
	@GetMapping("/{id}")
	public ClienteDTO buscar(@PathVariable("id") String id) {
		Cliente cli = service.buscarPorId(Long.parseLong(id)).get();
		return clienteEntityToClienteDto(cli);
	}
	
	@GetMapping("/codigo/{codigo}")
	public ClienteDTO buscarPorCodigo(@PathVariable("codigo") Integer codigo) {
		Cliente cli = service.buscarPorCodigo(codigo).get();
		return clienteEntityToClienteDto(cli);
	}

	@PostMapping("/salvar")
	public ResponseEntity salvar(@RequestBody ClienteDTO dto) {
		try {
			Cliente cliente = clienteDtoToClienteEntity(dto);
			Cliente clienteSalvo = service.salvar(cliente);
			return new ResponseEntity(clienteSalvo, HttpStatus.CREATED);
		}catch (RegraNegocioException re) {
			return ResponseEntity.badRequest().body(re.getMessage());
		}
	}

	@PostMapping("/alterar")
	public ResponseEntity alterar(@RequestBody ClienteDTO dto) {
		try {
			Cliente cliente = clienteDtoToClienteEntity(dto);
			Cliente clienteSalvo = service.alterar(cliente);
			return new ResponseEntity(clienteSalvo, HttpStatus.CREATED);
		}catch (RegraNegocioException re) {
			return ResponseEntity.badRequest().body(re.getMessage());
		}
	}
	
	@DeleteMapping("/remover/{id}")
	public ResponseEntity remover(@PathVariable("id") String id) {
		try {
			Optional<Cliente> optionalCliente = this.service.buscarPorId(Long.valueOf(id));
			Cliente cliente = optionalCliente.get();
			this.service.remover(cliente);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}catch (RegraNegocioException re) {
			return ResponseEntity.badRequest().body(re.getMessage());
		}
	}
	
	@GetMapping("/buscar-por-id/{id}")
	public ClienteDTO buscarPorId(@PathVariable("id") long id) {
		Optional<Cliente> clienteRetorno = service.buscarPorId(id);
		return this.clienteEntityToClienteDto(clienteRetorno.get());
	}
	
	
	private Cliente clienteDtoToClienteEntity(ClienteDTO dto) {
		Cliente cliente = Cliente.builder().build();	
		cliente.setConfiguracoesClientes(new HashSet<ConfiguracaoCliente>(0));
		Usuario usuarioCadastro = Usuario.builder().build();
		
		cliente.setId(dto.getId());
		cliente.setCodigo(dto.getCodigo());
		cliente.setNomeFantasia(dto.getNomeFantasia());
		cliente.setCnpj(dto.getCnpj());
		
		if(!ObjectUtils.isEmpty(dto.getUsuarioCadastroDTO()) && 
				!ObjectUtils.isEmpty(dto.getUsuarioCadastroDTO().getId())) {
			Optional<Usuario> usuarioRetorno = this.usuarioRepository
					.findById(dto.getUsuarioCadastroDTO().getId().longValue());
			usuarioCadastro = usuarioRetorno.get();
		}
		cliente.setUsuarioCadastro(usuarioCadastro);

		ConfiguracaoCliente configuracaoCliente = null;
		Sistema sistema = null;
		for(ConfiguracaoClienteDTO conf: dto.getConfiguracaoClientesDTO()) {
			sistema = Sistema.builder()
					.id(conf.getSistemaDTO().getId())
					.url(conf.getSistemaDTO().getUrl())
					.descricao(conf.getSistemaDTO().getDescricao())
					.ativo(conf.getSistemaDTO().isAtivo())
					.build();
			
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
					.cliente(cliente)
					.sistema(sistema)
					.build();
			cliente.getConfiguracoesClientes().add(configuracaoCliente);
		}
		
		return cliente;
	}
	
	private ClienteDTO clienteEntityToClienteDto(Cliente entity) {
		ClienteDTO clienteDTO = ClienteDTO.builder().build();		
		clienteDTO.setConfiguracaoClientesDTO(new HashSet<ConfiguracaoClienteDTO>(0));
		UsuarioDTO usurioCadastroDTO = UsuarioDTO.builder().build();
		
		clienteDTO.setId(entity.getId());
		clienteDTO.setCodigo(entity.getCodigo());
		clienteDTO.setNomeFantasia(entity.getNomeFantasia());
		clienteDTO.setCnpj(entity.getCnpj());
		
		clienteDTO.setUsuarioCadastroDTO(usurioCadastroDTO);
		
		ConfiguracaoClienteDTO configuracaoClienteDTO = null;
		SistemaDTO sistemaDTO = null;
		for(ConfiguracaoCliente conf: entity.getConfiguracoesClientes()) {
			sistemaDTO = SistemaDTO.builder()
				.id(conf.getSistema().getId())
				.url(conf.getSistema().getUrl())
				.descricao(conf.getSistema().getDescricao())
				.ativo(conf.getSistema().isAtivo())
				.build();
			
			configuracaoClienteDTO = ConfiguracaoClienteDTO.builder()
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
					.sistemaDTO(sistemaDTO)
					.build();

			clienteDTO.getConfiguracaoClientesDTO().add(configuracaoClienteDTO);
		}
		return clienteDTO;
	}

}
