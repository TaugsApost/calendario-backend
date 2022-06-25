package br.com.taugs.calendario.dia.controller;

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

import br.com.taugs.calendario.dia.entity.Dia;
import br.com.taugs.calendario.dia.filter.DiaFilter;
import br.com.taugs.calendario.dia.service.DiaService;
import br.com.taugs.calendario.usuario.entity.Usuario;

@CrossOrigin("*")
@RestController
@RequestMapping("/dia")
public class DiaController {

	@Autowired
	DiaService service;

	@GetMapping(value = "/listar")
	public ResponseEntity<List<Dia>> listar() {
		List<Dia> dia = service.listar();
		return new ResponseEntity<List<Dia>>(dia, HttpStatus.OK);
	}

	@PostMapping(value = "/salvar")
	public ResponseEntity<Dia> salvar(@RequestBody Dia dia) {
		Dia entity = service.salvar(dia);
		return new ResponseEntity<Dia>(entity, HttpStatus.OK);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Dia> salvar(@RequestBody Dia dia, Long idUser) {
		Dia entity = service.salvar(dia, idUser);
		return new ResponseEntity<Dia>(entity, HttpStatus.OK);
	}

	@PostMapping(value = "/consultar")
	public ResponseEntity<List<Dia>> consultar(@RequestBody DiaFilter filtro) {
		List<Dia> resultado = service.consultar(filtro);
		return new ResponseEntity<List<Dia>>(resultado, HttpStatus.OK);
	}

	@PostMapping(value = "/listarTodos")
	public ResponseEntity<List<Dia>> listar(@RequestBody Usuario user) {
		List<Dia> resultado = service.buscarTodosVinculados(user);
		return new ResponseEntity<List<Dia>>(resultado, HttpStatus.OK);
	}

	@GetMapping(value = "/detalhar/{id}")
	public ResponseEntity<Dia> detalhar(@PathVariable("id") Long id) {
		Dia dia = service.detalhar(id);
		return new ResponseEntity<Dia>(dia, HttpStatus.OK);
	}

	@DeleteMapping(value = "/excluir/{id}")
	public ResponseEntity<String> deletar(@PathVariable("id") Long id) {
		service.deletar(id);
		return new ResponseEntity<String>("Deletado", HttpStatus.OK);
	}

}
