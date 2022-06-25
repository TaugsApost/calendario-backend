package br.com.taugs.calendario.mes.controller;

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

import br.com.taugs.calendario.mes.entity.Mes;
import br.com.taugs.calendario.mes.filter.MesFilter;
import br.com.taugs.calendario.mes.service.MesService;
import br.com.taugs.calendario.usuario.entity.Usuario;

@CrossOrigin("*")
@RestController
@RequestMapping("/mes")
public class MesController {

	@Autowired
	MesService service;

	@PostMapping(value = "/listarTodos")
	public ResponseEntity<List<Mes>> listar(@RequestBody Usuario user) {
		List<Mes> resultado = service.buscarTodosVinculados(user.getId());
		return new ResponseEntity<List<Mes>>(resultado, HttpStatus.OK);
	}

	@PostMapping(value = "/consultar")
	public ResponseEntity<List<Mes>> consultar(@RequestBody MesFilter filtro) {
		List<Mes> resultado = service.consultar(filtro);
		return new ResponseEntity<List<Mes>>(resultado, HttpStatus.OK);
	}

	@GetMapping(value = "/listar")
	public ResponseEntity<List<Mes>> listar() {
		List<Mes> mes = service.listar();
		return new ResponseEntity<List<Mes>>(mes, HttpStatus.OK);
	}

	@PostMapping(value = "/salvar")
	public ResponseEntity<Mes> salvar(@RequestBody Mes mes) {
		Mes entity = service.salvar(mes);
		return new ResponseEntity<Mes>(entity, HttpStatus.OK);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Mes> salvar(@RequestBody Mes mes, Long idUser) {
		Mes entity = service.salvar(mes);
		return new ResponseEntity<Mes>(entity, HttpStatus.OK);
	}

	@GetMapping(value = "/detalhar/{id}")
	public ResponseEntity<Mes> detalhar(@PathVariable("id") Long id) {
		Mes mes = service.detalhar(id);
		return new ResponseEntity<Mes>(mes, HttpStatus.OK);
	}

	@DeleteMapping(value = "/excluir/{id}")
	public ResponseEntity<String> deletar(@PathVariable("id") Long id) {
		service.deletar(id);
		return new ResponseEntity<String>("Deletado", HttpStatus.OK);
	}

}
