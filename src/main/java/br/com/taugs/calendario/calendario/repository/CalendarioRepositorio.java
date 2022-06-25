package br.com.taugs.calendario.calendario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taugs.calendario.calendario.entity.Calendario;

@Repository
public interface CalendarioRepositorio extends JpaRepository<Calendario, Long> {

	List<Calendario> buscarTodos(Long id);

	List<Calendario> buscarOutros(Long id);

}
