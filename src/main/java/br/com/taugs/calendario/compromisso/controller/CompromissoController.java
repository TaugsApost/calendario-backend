package br.com.taugs.calendario.compromisso.controller;

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

import br.com.taugs.calendario.compromisso.entity.Compromisso;
import br.com.taugs.calendario.compromisso.filter.CompromissoFilter;
import br.com.taugs.calendario.compromisso.filter.CompromissoResponse;
import br.com.taugs.calendario.compromisso.service.CompromissoService;
import br.com.taugs.calendario.usuario.entity.Usuario;

@CrossOrigin("*")
@RestController
@RequestMapping("/compromisso")
public class CompromissoController {

	@Autowired
	CompromissoService service;

	@PostMapping(value = "/listarTodos")
	public ResponseEntity<List<Compromisso>> listar(@RequestBody Usuario user) {
		List<Compromisso> resultado = service.buscarPorUsuario(user.getId());
		return new ResponseEntity<List<Compromisso>>(resultado, HttpStatus.OK);
	}

	@PostMapping(value = "/consultar")
	public ResponseEntity<List<CompromissoResponse>> consultar(@RequestBody CompromissoFilter filtro) {
		List<CompromissoResponse> resultado = service.consultar(filtro);
		return new ResponseEntity<List<CompromissoResponse>>(resultado, HttpStatus.OK);
	}

	@PostMapping(value = "/salvar")
	public ResponseEntity<Compromisso> salvar(@RequestBody Compromisso compromisso) {
		Compromisso entity = service.salvar(compromisso);
		return new ResponseEntity<Compromisso>(entity, HttpStatus.OK);
	}

	@GetMapping(value = "/detalhar/{id}")
	public ResponseEntity<Compromisso> detalhar(@PathVariable("id") Long id) {
		Compromisso compromisso = service.detalhar(id);
		return new ResponseEntity<Compromisso>(compromisso, HttpStatus.OK);
	}

	@DeleteMapping(value = "/excluir/{id}")
	public ResponseEntity<String> deletar(@PathVariable("id") Long id) {
		service.deletar(id);
		return new ResponseEntity<String>("Deletado", HttpStatus.OK);
	}

}
