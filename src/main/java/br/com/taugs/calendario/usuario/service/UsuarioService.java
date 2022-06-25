package br.com.taugs.calendario.usuario.service;

import java.util.List;

import br.com.taugs.calendario.usuario.entity.Usuario;
import br.com.taugs.calendario.usuario.filter.UsuarioFilter;
import br.com.taugs.calendario.utils.BaseService;

public interface UsuarioService extends BaseService<Usuario, Long> {

	List<Usuario> consultar(UsuarioFilter filter);

	Usuario buscarPorUserName(UsuarioFilter filter);

	Usuario buscarPorId(Long id);
}
