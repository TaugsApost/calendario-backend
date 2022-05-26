package br.com.taugs.calendario.seguranca.autentificacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.taugs.calendario.seguranca.autentificacao.service.AutentificacaoService;
import br.com.taugs.calendario.usuario.entity.Usuario;
import br.com.taugs.calendario.usuario.filter.UsuarioFilter;
import br.com.taugs.calendario.usuario.service.UsuarioService;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AutentificacaoController {

	@Autowired
	AutentificacaoService service;
	
	@PostMapping(value = "/logar")
	public ResponseEntity<Usuario> logar(@RequestBody UsuarioFilter filtro) throws Exception{ 
		Usuario entity = service.autentificacao(filtro);
		return new ResponseEntity<Usuario> (entity, HttpStatus.OK);
	}
}
