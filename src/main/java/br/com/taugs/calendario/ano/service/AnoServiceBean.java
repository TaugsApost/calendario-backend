package br.com.taugs.calendario.ano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taugs.calendario.ano.entity.Ano;
import br.com.taugs.calendario.ano.repository.AnoRepository;
import br.com.taugs.calendario.utils.BaseServiceBean;

@Service
public class AnoServiceBean extends BaseServiceBean<Ano, Long> implements AnoService{

	@Autowired
	AnoRepository repositorio;
	
	@Override
	public List<Ano> listar() {
		return this.listarTodos(repositorio);
	}

	@Override
	public Ano salvar(Ano entity) {
		return this.salvarEntity(entity, repositorio);
	}

	@Override
	public Ano detalhar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Long id) {
		// TODO Auto-generated method stub
		
	}

}
