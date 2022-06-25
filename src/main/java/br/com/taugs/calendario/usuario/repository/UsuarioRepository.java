package br.com.taugs.calendario.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taugs.calendario.usuario.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario buscarPorUserName(String userName);

	List<Usuario> buscarTodos(String nome, String userName);

	Usuario buscarPorId(Long id);
}
