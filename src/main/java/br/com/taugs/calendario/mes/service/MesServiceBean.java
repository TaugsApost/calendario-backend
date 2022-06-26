package br.com.taugs.calendario.mes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taugs.calendario.mes.entity.Mes;
import br.com.taugs.calendario.mes.filter.MesFilter;
import br.com.taugs.calendario.mes.repository.MesRepository;
import br.com.taugs.calendario.usuario.entity.Usuario;
import br.com.taugs.calendario.usuario.repository.UsuarioRepository;
import br.com.taugs.calendario.usuario.service.UsuarioService;
import br.com.taugs.calendario.utils.BaseServiceBean;

@Service
public class MesServiceBean extends BaseServiceBean<Mes, Long> implements MesService {

	@Autowired
	MesRepository repositorio;

	@Autowired
	UsuarioRepository userRepositorio;

	@Autowired
	UsuarioService userService;

	@Override
	public List<Mes> listar() {
		return this.listarTodos(repositorio);
	}

	@Override
	public Mes salvar(Mes entity) {
		return this.salvarEntity(entity, repositorio);
	}

	@Override
	public Mes detalhar(Long id) {
		Mes m = this.retornarEntity(id, repositorio);
		return (m != null ? m : new Mes());
	}

	@Override
	public void deletar(Long id) {
		Mes m = this.detalhar(id);
		Usuario u = userService.detalhar(m.getIdUsuario());
		u.getMeses().remove(m);
		userRepositorio.save(u);
	}

	@Override
	public List<Mes> consultar(MesFilter filter) {
		List<Mes> resultado = new ArrayList<Mes>();

		String nome, sigla;

		if (filter.getNome() == null) {
			nome = "%%";
		} else {
			nome = "%" + filter.getNome().toUpperCase() + "%";
		}

		if (filter.getSigla() == null) {
			sigla = "%%";
		} else {
			sigla = "%" + filter.getSigla().toUpperCase() + "%";
		}

		resultado = repositorio.buscarTodos(nome, sigla, filter.getIdUser());

		return resultado;
	}

	@Override
	public List<Mes> buscarTodosVinculados(Long idUuser) {
		List<Mes> resultado = new ArrayList<Mes>();
		resultado = repositorio.buscarTodosVinculados(idUuser);
		resultado.addAll(repositorio.buscarOutros(idUuser));
		return resultado;
	}

	@Override
	public Mes salvar(Mes entity, Long idUuser) {
		entity.setIdUsuario(idUuser);
		return this.salvarEntity(entity, repositorio);
	}

}
