package br.com.taugs.calendario.calendario.configuracao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.taugs.calendario.calendario.configuracao.entity.ConfiguracaoCalendario;
import br.com.taugs.calendario.calendario.configuracao.service.ConfiguracaoCalendarioService;

@CrossOrigin("*")
@RestController
@RequestMapping("/config")
public class ConfiguracaoCalendarioController {

	@Autowired
	ConfiguracaoCalendarioService service;

	@PostMapping(value = "/salvar")
	public ResponseEntity<ConfiguracaoCalendario> salvar(@RequestBody ConfiguracaoCalendario config) {
		ConfiguracaoCalendario entity = service.salvar(config);
		return new ResponseEntity<ConfiguracaoCalendario>(entity, HttpStatus.OK);
	}

}
