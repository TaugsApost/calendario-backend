package br.com.taugs.calendario.vinculoMesData.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taugs.calendario.utils.BaseServiceBean;
import br.com.taugs.calendario.vinculoMesData.entity.VinculoMesData;
import br.com.taugs.calendario.vinculoMesData.filter.VinculoMesDataFilter;
import br.com.taugs.calendario.vinculoMesData.repository.VinculoMesDataRepository;

@Service
public class VinculoMesDataServiceBean extends BaseServiceBean<VinculoMesData, Long> implements VinculoMesDataService{

	@Autowired
	VinculoMesDataRepository repositorio;
	
	@Override
	public List<VinculoMesData> listar() {
		return this.listarTodos(repositorio);
	}

	@Override
	public VinculoMesData salvar(VinculoMesData entity) {
		return this.salvarEntity(entity, repositorio);
	}

	@Override
	public VinculoMesData detalhar(Long id) {
		VinculoMesData v = this.retornarEntity(id, repositorio);
		return v != null ? v : new VinculoMesData();
	}

	@Override
	public void deletar(Long id) {
		this.deletarEntity(id, repositorio);
	}

	@Override
	public List<VinculoMesData> consultar(VinculoMesDataFilter filter) {
		List<VinculoMesData> resultado = new ArrayList<VinculoMesData>();
		String nome; String sigla; Boolean bissexto; Integer posicao; Integer nDias;
		
		System.out.println(filter.getPosicao() + " - " + filter.getBissexto() + " - " +filter.getNDias());
		posicao = filter.getPosicao();
		nDias = filter.getNDias();
		bissexto = filter.getBissexto();
		
		if(filter.getMes() != null) {
			if(filter.getMes().getNome() != null) {
				nome = "%" + filter.getMes().getNome().toUpperCase() + "%";
			}else {
				nome = "%%";
			}
			if(filter.getMes().getSigla() != null) {
				sigla = "%" + filter.getMes().getSigla().toUpperCase() + "%";
			}else {
				sigla = "%%";
			}
		}else {
			nome = "%%";
			sigla="%%";
		}
		
		resultado = repositorio.buscarTodos(nome, sigla, posicao, bissexto, nDias);
		return resultado;
	}

}
