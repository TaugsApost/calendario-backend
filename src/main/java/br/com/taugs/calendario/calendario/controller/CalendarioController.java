package br.com.taugs.calendario.calendario.controller;

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

import br.com.taugs.calendario.calendario.entity.Calendario;
import br.com.taugs.calendario.calendario.service.CalendarioService;
import br.com.taugs.calendario.usuario.entity.Usuario;

@CrossOrigin("*")
@RestController
@RequestMapping("/calendario")
public class CalendarioController {

	@Autowired
	CalendarioService service;

	@PostMapping(value = "/salvar")
	public ResponseEntity<Calendario> salvar(@RequestBody Calendario calendario) {
		Calendario entity = service.salvar(calendario);
		return new ResponseEntity<Calendario>(entity, HttpStatus.OK);
	}

	@PostMapping(value = "/listarTodos")
	public ResponseEntity<List<Calendario>> listarTodos(@RequestBody Usuario usuario) {
		List<Calendario> entity = service.buscarTodos(usuario.getId());
		return new ResponseEntity<List<Calendario>>(entity, HttpStatus.OK);
	}

	@PostMapping(value = "/listar")
	public ResponseEntity<List<Calendario>> listar(@RequestBody Usuario usuario) {
		List<Calendario> lista = service.buscarPorUsuario(usuario.getId());
		return new ResponseEntity<List<Calendario>>(lista, HttpStatus.OK);
	}

	@GetMapping(value = "/detalhar/{id}")
	public ResponseEntity<Calendario> detalhar(@PathVariable("id") Long id) {
		Calendario calendario = service.detalhar(id);
		return new ResponseEntity<Calendario>(calendario, HttpStatus.OK);
	}

	@DeleteMapping(value = "/excluir/{id}")
	public ResponseEntity<String> deletar(@PathVariable("id") Long id) {
		service.deletar(id);
		return new ResponseEntity<String>("Deletado", HttpStatus.OK);
	}
}
