package br.com.taugs.calendario.data.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taugs.calendario.data.entity.Data;
import br.com.taugs.calendario.data.filter.DataFilter;
import br.com.taugs.calendario.data.repository.DataRepository;
import br.com.taugs.calendario.utils.BaseServiceBean;

@Service
public class DataServiceBean extends BaseServiceBean<Data, Long> implements DataService {

	@Autowired
	DataRepository repositorio;

	@Override
	public List<Data> listar() {
		return this.listarTodos(repositorio);
	}

	@Override
	public Data salvar(Data entity) {
		return this.salvarEntity(entity, repositorio);
	}

	@Override
	public Data detalhar(Long id) {
		Data d = this.retornarEntity(id, repositorio);
		return (d == null ? new Data() : d);
	}

	@Override
	public void deletar(Long id) {
		this.deletarEntity(id, repositorio);
	}

	@Override
	public List<Data> consultar(DataFilter filter) {
		List<Data> resultado = new ArrayList<Data>();

		resultado = repositorio.buscarDia(filter.getCalendario(), filter.getMes(), filter.getNomeAno());

		return resultado;
	}

}
