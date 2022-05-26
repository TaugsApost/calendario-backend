package br.com.taugs.calendario.vinculoDiaData.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taugs.calendario.utils.BaseServiceBean;
import br.com.taugs.calendario.vinculoDiaData.entity.VinculoDiaData;
import br.com.taugs.calendario.vinculoDiaData.filter.VinculoDiaDataFilter;
import br.com.taugs.calendario.vinculoDiaData.repository.VinculoDiaDataRepository;

@Service
public class VinculoDiaDataServiceBean extends BaseServiceBean<VinculoDiaData, Long> implements VinculoDiaDataService{

	@Autowired
	VinculoDiaDataRepository repositorio;

	@Override
	public List<VinculoDiaData> listar() {
		return this.listarTodos(repositorio);
	}

	@Override
	public VinculoDiaData salvar(VinculoDiaData entity) {
		return this.salvarEntity(entity, repositorio);
	}

	@Override
	public VinculoDiaData detalhar(Long id) {
		VinculoDiaData v = this.retornarEntity(id, repositorio);
		return (v != null ? v : new VinculoDiaData());
	}

	@Override
	public void deletar(Long id) {
		this.deletarEntity(id, repositorio);
		
	}

	@Override
	public List<VinculoDiaData> consultar(VinculoDiaDataFilter filter) {
		List<VinculoDiaData> resultado = new ArrayList<VinculoDiaData>(); String nome, sigla; Integer posicao; Boolean diaInicial;
		
		posicao = filter.getPosicao() == 0 ? null : filter.getPosicao();
		diaInicial = filter.getDiaInicialCalendario();
		
		if(filter.getDia() != null) {
			if(filter.getDia().getNome() != null) {
				nome = "%" + filter.getDia().getNome().toUpperCase() + "%";
			}else {
				nome = "%%";
			}
			if(filter.getDia().getSigla() != null) {
				sigla = "%" + filter.getDia().getSigla().toUpperCase() + "%";
			}else {
				sigla = "%%";
			}
		}else {
			nome = "%%";
			sigla = "%%";
		}
		
		resultado = repositorio.buscarTodos(nome, sigla, posicao, diaInicial);
		
		return resultado;
	}
	
	
}
