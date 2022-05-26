package br.com.taugs.calendario.vinculoMesData.controller;

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

import br.com.taugs.calendario.vinculoMesData.entity.VinculoMesData;
import br.com.taugs.calendario.vinculoMesData.filter.VinculoMesDataFilter;
import br.com.taugs.calendario.vinculoMesData.service.VinculoMesDataService;


@CrossOrigin("*")
@RestController
@RequestMapping("/vinculoMesData")
public class VinculoMesDataController {

	@Autowired
	VinculoMesDataService service;
	
	@PostMapping(value = "/consultar")
	public ResponseEntity<List<VinculoMesData>> consultar(@RequestBody VinculoMesDataFilter filtro){ 
		List<VinculoMesData> resultado = service.consultar(filtro);
		return new ResponseEntity<List<VinculoMesData>> (resultado, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listar")
	public ResponseEntity<List<VinculoMesData>> listar(){ 
		List<VinculoMesData> vinculo = service.listar();
		return new ResponseEntity<List<VinculoMesData>> (vinculo, HttpStatus.OK);
	}

	@PostMapping(value = "/salvar")
	public ResponseEntity<VinculoMesData> salvar(@RequestBody VinculoMesData vinculo){ 
		VinculoMesData entity = service.salvar(vinculo);
		return new ResponseEntity<VinculoMesData>(entity, HttpStatus.OK);
	}
	
	@GetMapping(value = "/detalhar/{id}")
	public ResponseEntity<VinculoMesData> detalhar(@PathVariable("id") Long id){ 
		VinculoMesData vinculo = service.detalhar(id);
		return new ResponseEntity<VinculoMesData> (vinculo, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/excluir/{id}")
	public ResponseEntity<String> deletar(@PathVariable("id") Long id){ 
		service.deletar(id);
		return new ResponseEntity<String> ("Deletado", HttpStatus.OK);
	}
}
