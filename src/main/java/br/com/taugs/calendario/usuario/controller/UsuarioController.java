package br.com.taugs.calendario.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.taugs.calendario.usuario.entity.Usuario;
import br.com.taugs.calendario.usuario.filter.UsuarioFilter;
import br.com.taugs.calendario.usuario.service.UsuarioService;

@CrossOrigin("*")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService service;
	
	@PostMapping(value = "/consultar")
	public ResponseEntity<List<Usuario>> consultar(@RequestBody UsuarioFilter filtro){ 
		List<Usuario> resultado = service.consultar(filtro);
		return new ResponseEntity<List<Usuario>> (resultado, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listar")
	public ResponseEntity<List<Usuario>> listar(){ 
		List<Usuario> mes = service.listar();
		return new ResponseEntity<List<Usuario>> (mes, HttpStatus.OK);
	}
	
	@PostMapping(value = "/salvar")
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){ 
		Usuario entity = service.salvar(usuario);
		return new ResponseEntity<Usuario>(entity, HttpStatus.OK);
	}
	
	@GetMapping(value = "/detalhar/{id}")
	public ResponseEntity<Usuario> detalhar(@PathVariable("id") Long id){ 
		Usuario usuario = service.detalhar(id);
		return new ResponseEntity<Usuario> (usuario, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/excluir/{id}")
	public ResponseEntity<String> deletar(@PathVariable("id") Long id){ 
		service.deletar(id);
		return new ResponseEntity<String> ("Deletado", HttpStatus.OK);
	}
	
	@PostMapping(value = "/buscarPorUserName")
	public ResponseEntity<Usuario> buscarPorUserName(@RequestBody UsuarioFilter filtro){ 
		Usuario entity = service.buscarPorUserName(filtro);
		return new ResponseEntity<Usuario>(entity, HttpStatus.OK);
	}
	

}
