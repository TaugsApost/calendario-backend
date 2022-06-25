package br.com.taugs.calendario.mes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taugs.calendario.mes.entity.Mes;

@Repository
public interface MesRepository extends JpaRepository<Mes, Long> {
	List<Mes> buscarTodos(String nome, String sigla, Long idUsuario);

	List<Mes> buscarOutros(Long id);

	List<Mes> buscarTodosVinculados(Long id);
}
