package br.com.taugs.calendario.dia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taugs.calendario.dia.entity.Dia;
import br.com.taugs.calendario.dia.filter.DiaFilter;
import br.com.taugs.calendario.dia.repository.DiaRepository;
import br.com.taugs.calendario.utils.BaseServiceBean;

@Service
public class DiaServiceBean extends BaseServiceBean<Dia, Long> implements DiaService {

	@Autowired
	DiaRepository repositorio;

	// EntityManager em

	@Override
	public List<Dia> listar() {
		return this.listarTodos(repositorio);
	}

	@Override
	public Dia salvar(Dia entity) {
		return this.salvarEntity(entity, repositorio);
	}

	@Override
	public Dia detalhar(Long id) {
		Dia d = this.retornarEntity(id, repositorio);
		return (d != null ? d : new Dia());
	}

	@Override
	public void deletar(Long id) {
		this.deletarEntity(id, repositorio);
	}

	@Override
	public List<Dia> consultar(DiaFilter filter) {
		List<Dia> resultado = new ArrayList<Dia>();
		
		String nome, sigla;
		
		if(filter.getNome() == null) {
			nome = "%%";
		}else {
			nome = "%" + filter.getNome().toUpperCase() + "%";
		}
		
		if(filter.getSigla() == null) {
			sigla = "%%";
		}else {
			sigla = "%" + filter.getSigla().toUpperCase() + "%";
		}

		resultado = repositorio.buscarTodos(nome, sigla);

		return resultado;
	}

}
