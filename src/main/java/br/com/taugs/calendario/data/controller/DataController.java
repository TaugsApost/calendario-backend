package br.com.taugs.calendario.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.taugs.calendario.data.entity.Data;
import br.com.taugs.calendario.data.filter.DataFilter;
import br.com.taugs.calendario.data.service.DataService;

@CrossOrigin("*")
@RestController
@RequestMapping("/data")
public class DataController {

	@Autowired
	DataService service;

	@PostMapping(value = "/consultar")
	public ResponseEntity<List<Data>> consultar(@RequestBody DataFilter filtro) {
		List<Data> resultado = service.consultar(filtro);
		return new ResponseEntity<List<Data>>(resultado, HttpStatus.OK);
	}

}
