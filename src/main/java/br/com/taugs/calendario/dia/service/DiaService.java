package br.com.taugs.calendario.dia.service;

import java.util.List;

import br.com.taugs.calendario.dia.entity.Dia;
import br.com.taugs.calendario.dia.filter.DiaFilter;
import br.com.taugs.calendario.usuario.entity.Usuario;
import br.com.taugs.calendario.utils.BaseService;

public interface DiaService extends BaseService<Dia, Long> {

	List<Dia> consultar(DiaFilter filter);

	Dia salvar(Dia entity, Long idUser);

	List<Dia> buscarTodosVinculados(Usuario user);

}
