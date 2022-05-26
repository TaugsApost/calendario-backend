package br.com.taugs.calendario.usuario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taugs.calendario.usuario.entity.Usuario;
import br.com.taugs.calendario.usuario.filter.UsuarioFilter;
import br.com.taugs.calendario.usuario.repository.UsuarioRepository;
import br.com.taugs.calendario.utils.BaseServiceBean;

@Service
public class UsuarioServiceBean extends BaseServiceBean<Usuario, Long> implements UsuarioService{

	@Autowired
	UsuarioRepository repository;

	@Override
	public List<Usuario> listar() {
		return this.listarTodos(repository);
	}

	@Override
	public Usuario salvar(Usuario entity) {
		return this.salvarEntity(entity, repository);
	}

	@Override
	public Usuario detalhar(Long id) {
		return this.retornarEntity(id, repository);
	}

	@Override
	public void deletar(Long id) {
		this.deletarEntity(id, repository);
	}

	@Override
	public List<Usuario> consultar(UsuarioFilter filter) {
		List<Usuario> resultado = new ArrayList<Usuario>();
		String nome, userName;
		
		if(filter.getNome()== null) {
			nome = "%%";
		}else {
			nome = "%" + filter.getNome().toUpperCase() + "%";
		}
		
		if (filter.getUserName() == null) {
			userName = "%%";
		} else {
			userName = "%" + filter.getUserName().toUpperCase() + "%";
		}
		
		resultado = repository.buscarTodos(nome, userName);
		
		return resultado;
	}

	@Override
	public Usuario buscarPorUserName(UsuarioFilter filter) {
		Usuario user = null;
		String userName = filter.getUserName();
		user = repository.buscarPorUserName(userName);
		return user;
	}
	
}
