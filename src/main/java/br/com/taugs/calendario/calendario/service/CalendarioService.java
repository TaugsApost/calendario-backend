package br.com.taugs.calendario.calendario.service;

import java.util.List;

import br.com.taugs.calendario.calendario.entity.Calendario;
import br.com.taugs.calendario.utils.BaseService;

public interface CalendarioService extends BaseService<Calendario, Long> {

	List<Calendario> buscarTodos(Long id);

	List<Calendario> buscarPorUsuario(Long id);

}
