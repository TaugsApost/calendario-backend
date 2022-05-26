package br.com.taugs.calendario.vinculoDiaData.controller;

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

import br.com.taugs.calendario.vinculoDiaData.entity.VinculoDiaData;
import br.com.taugs.calendario.vinculoDiaData.filter.VinculoDiaDataFilter;
import br.com.taugs.calendario.vinculoDiaData.service.VinculoDiaDataService;


@CrossOrigin("*")
@RestController
@RequestMapping("/vinculoDiaData")
public class VinculoDiaDataController {
	
	@Autowired
	VinculoDiaDataService service;
	
	@PostMapping(value = "/consultar")
	public ResponseEntity<List<VinculoDiaData>> consultar(@RequestBody VinculoDiaDataFilter filtro){ 
		List<VinculoDiaData> resultado = service.consultar(filtro);
		return new ResponseEntity<List<VinculoDiaData>> (resultado, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listar")
	public ResponseEntity<List<VinculoDiaData>> listar(){ 
		List<VinculoDiaData> vinculo = service.listar();
		return new ResponseEntity<List<VinculoDiaData>> (vinculo, HttpStatus.OK);
	}

	@PostMapping(value = "/salvar")
	public ResponseEntity<VinculoDiaData> salvar(@RequestBody VinculoDiaData vinculo){ 
		VinculoDiaData entity = service.salvar(vinculo);
		return new ResponseEntity<VinculoDiaData>(entity, HttpStatus.OK);
	}
	
	@GetMapping(value = "/detalhar/{id}")
	public ResponseEntity<VinculoDiaData> detalhar(@PathVariable("id") Long id){ 
		VinculoDiaData vinculo = service.detalhar(id);
		return new ResponseEntity<VinculoDiaData> (vinculo, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/excluir/{id}")
	public ResponseEntity<String> deletar(@PathVariable("id") Long id){ 
		service.deletar(id);
		return new ResponseEntity<String> ("Deletado", HttpStatus.OK);
	}
}
