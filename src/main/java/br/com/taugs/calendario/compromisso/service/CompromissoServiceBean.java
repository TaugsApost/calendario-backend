package br.com.taugs.calendario.compromisso.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taugs.calendario.compromisso.entity.Compromisso;
import br.com.taugs.calendario.compromisso.filter.CompromissoFilter;
import br.com.taugs.calendario.compromisso.filter.CompromissoMapper;
import br.com.taugs.calendario.compromisso.filter.CompromissoResponse;
import br.com.taugs.calendario.compromisso.repository.CompromissoRepositorio;
import br.com.taugs.calendario.horario.entity.Horario;
import br.com.taugs.calendario.utils.BaseServiceBean;

@Service
public class CompromissoServiceBean extends BaseServiceBean<Compromisso, Long> implements CompromissoService {

	private Horario horario;

	@Autowired
	CompromissoRepositorio repositorio;

	@Override
	public List<Compromisso> listar() {
		return this.listarTodos(repositorio);
	}

	@Override
	public Compromisso salvar(Compromisso entity) {
		return this.salvarEntity(entity, repositorio);
	}

	@Override
	public Compromisso detalhar(Long id) {
		Compromisso c = this.retornarEntity(id, repositorio);
		return (c != null ? c : new Compromisso());
	}

	@Override
	public void deletar(Long id) {
		Compromisso c = detalhar(id);
		if (c.getIdUsuario() == null) {
			this.deletarEntity(id, repositorio);
		} else {
			c.setIdUsuario(null);
			c.setData(null);
			c.setHorario(null);
			c = repositorio.save(c);
			deletar(id);
		}
	}

	@Override
	public List<Compromisso> buscarPorUsuario(Long idUsuario) {
		List<Compromisso> resultado = repositorio.buscarPorUsuario(idUsuario);
		return resultado;
	}

	@Override
	public List<CompromissoResponse> consultar(CompromissoFilter filter) {
		List<Compromisso> lista = new ArrayList<Compromisso>();
		List<CompromissoResponse> resultado;
		if (filter.getTitulo() == null) {
			filter.setTitulo("%%");
		} else {
			filter.setTitulo("%" + filter.getTitulo().toUpperCase() + "%");
		}
		if (filter.getDescricao() == null) {
			filter.setDescricao("%%");
		} else {
			filter.setDescricao("%" + filter.getDescricao().toUpperCase() + "%");
		}
		lista = repositorio.buscarTodos(filter.getIdUsuario(), filter.getTitulo(), filter.getDescricao(), filter.getMinutos(), filter.getHora(), filter.getData());
		resultado = CompromissoMapper.toResponse(lista);
		return resultado;
	}

	@Override
	protected void beforeSave(Compromisso entity) {
		entity.setDescricao(entity.getDescricao().replaceAll("<p>", ""));
		entity.setDescricao(entity.getDescricao().replaceAll("</p>", ""));
		super.beforeSave(entity);
	}

}
