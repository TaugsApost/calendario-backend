package br.com.taugs.calendario.calendario.configuracao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taugs.calendario.calendario.configuracao.entity.ConfiguracaoCalendario;
import br.com.taugs.calendario.calendario.configuracao.repository.ConfiguracaoCalendairoRepository;
import br.com.taugs.calendario.utils.BaseServiceBean;

@Service
public class ConfiguracaoCalendarioServiceBean extends BaseServiceBean<ConfiguracaoCalendario, Long> implements ConfiguracaoCalendarioService{

	@Autowired
	ConfiguracaoCalendairoRepository repositorio;
	
	@Override
	public List<ConfiguracaoCalendario> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfiguracaoCalendario salvar(ConfiguracaoCalendario entity) {
		return this.salvarEntity(entity, repositorio);
	}

	@Override
	public ConfiguracaoCalendario detalhar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Long id) {
		// TODO Auto-generated method stub
		
	}

}
