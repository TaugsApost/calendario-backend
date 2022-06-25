package br.com.taugs.calendario.compromisso.service;

import java.util.List;

import br.com.taugs.calendario.compromisso.entity.Compromisso;
import br.com.taugs.calendario.compromisso.filter.CompromissoFilter;
import br.com.taugs.calendario.compromisso.filter.CompromissoResponse;
import br.com.taugs.calendario.utils.BaseService;

public interface CompromissoService extends BaseService<Compromisso, Long> {

	List<Compromisso> buscarPorUsuario(Long idUsuario);

	List<CompromissoResponse> consultar(CompromissoFilter filter);
}
