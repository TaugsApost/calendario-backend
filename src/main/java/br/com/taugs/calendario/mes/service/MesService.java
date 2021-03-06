package br.com.taugs.calendario.mes.service;

import java.util.List;

import br.com.taugs.calendario.mes.entity.Mes;
import br.com.taugs.calendario.mes.filter.MesFilter;
import br.com.taugs.calendario.utils.BaseService;

public interface MesService extends BaseService<Mes, Long> {
	List<Mes> consultar(MesFilter filter);

	Mes salvar(Mes entity, Long idUuser);

	List<Mes> buscarTodosVinculados(Long idUuser);
}
