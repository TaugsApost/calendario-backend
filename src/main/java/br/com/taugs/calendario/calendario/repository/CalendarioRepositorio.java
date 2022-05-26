package br.com.taugs.calendario.calendario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taugs.calendario.calendario.entity.Calendario;

@Repository
public interface CalendarioRepositorio extends JpaRepository<Calendario, Long>{

}
