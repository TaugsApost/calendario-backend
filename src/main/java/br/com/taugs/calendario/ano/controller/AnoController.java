package br.com.taugs.calendario.ano.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.taugs.calendario.ano.entity.Ano;
import br.com.taugs.calendario.ano.service.AnoService;
import br.com.taugs.calendario.vinculoDiaData.entity.VinculoDiaData;
import br.com.taugs.calendario.vinculoDiaData.service.VinculoDiaDataService;


@CrossOrigin("*")
@RestController
@RequestMapping("/ano")
public class AnoController {
	
	@Autowired
	AnoService service;
	
	@GetMapping(value = "/listar")
	public ResponseEntity<List<Ano>> listar(){ 
		List<Ano> ano = service.listar();
		return new ResponseEntity<List<Ano>> (ano, HttpStatus.OK);
	}

}
