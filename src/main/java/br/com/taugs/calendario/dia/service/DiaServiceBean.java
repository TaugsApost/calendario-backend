package br.com.taugs.calendario.dia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taugs.calendario.dia.entity.Dia;
import br.com.taugs.calendario.dia.filter.DiaFilter;
import br.com.taugs.calendario.dia.repository.DiaRepository;
import br.com.taugs.calendario.usuario.entity.Usuario;
import br.com.taugs.calendario.usuario.repository.UsuarioRepository;
import br.com.taugs.calendario.usuario.service.UsuarioService;
import br.com.taugs.calendario.utils.BaseServiceBean;

@Service
public class DiaServiceBean extends BaseServiceBean<Dia, Long> implements DiaService {

	@Autowired
	DiaRepository repositorio;

	@Autowired
	UsuarioRepository userRepositorio;

	@Autowired
	UsuarioService userService;

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
		// Dia d = this.detalhar(id);
		// d.setIdUsuario(null);
		// this.salvar(d);
		// this.deletarEntity(id, repositorio);
		Dia d = this.detalhar(id);
		Usuario u = userService.detalhar(d.getIdUsuario());
		u.getDias().remove(d);
		userRepositorio.save(u);
	}

	@Override
	public List<Dia> consultar(DiaFilter filter) {
		List<Dia> resultado = new ArrayList<Dia>();

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
	public List<Dia> buscarTodosVinculados(Usuario user) {
		List<Dia> resultado = new ArrayList<Dia>();
		resultado = repositorio.buscarTodosVinculados(user.getId());
		resultado.addAll(repositorio.buscarOutros(user.getId()));
		return resultado;
	}

	@Override
	public Dia salvar(Dia entity, Long idUser) {
		entity.setIdUsuario(idUser);
		return this.salvarEntity(entity, repositorio);
	}

}
