package br.com.taugs.calendario.calendario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.taugs.calendario.ano.entity.Ano;
import br.com.taugs.calendario.calendario.entity.Calendario;
import br.com.taugs.calendario.calendario.service.CalendarioService;

@CrossOrigin("*")
@RestController
@RequestMapping("/calendario")
public class CalendarioController {

	@Autowired
	CalendarioService service;
	
	@PostMapping(value = "/salvar")
	public ResponseEntity<Calendario> salvar(@RequestBody Calendario calendario){ 
		Calendario entity = service.salvar(calendario);
		return new ResponseEntity<Calendario>(entity, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listar")
	public ResponseEntity<List<Calendario>> listar(){ 
		List<Calendario> lista = service.listar();
		return new ResponseEntity<List<Calendario>> (lista, HttpStatus.OK);
	}
}
