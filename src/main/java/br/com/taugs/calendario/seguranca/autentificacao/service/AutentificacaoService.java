package br.com.taugs.calendario.seguranca.autentificacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taugs.calendario.usuario.entity.Usuario;
import br.com.taugs.calendario.usuario.filter.UsuarioFilter;
import br.com.taugs.calendario.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AutentificacaoService {

	@Autowired
	private UsuarioService userService;

	public Usuario autentificacao(UsuarioFilter filter) throws Exception {
		Usuario usuario = null;
		Usuario valid = null;

		valid = userService.buscarPorUserName(filter);
		if (valid == null) {
			throw new Exception("Usuario ou senha incorretos");
		} else if (filter.getSenha().equals(valid.getSenha())) {
			usuario = valid;
		} else {
			throw new Exception("Usuario ou senha incorretos");
		}

		return usuario;
	}
}
