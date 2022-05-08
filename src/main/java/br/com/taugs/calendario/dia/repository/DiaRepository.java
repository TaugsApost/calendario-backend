package br.com.taugs.calendario.dia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taugs.calendario.dia.entity.Dia;

@Repository
public interface DiaRepository extends JpaRepository<Dia, Long>{

	List<Dia> buscarTodos(String nome, String sigla);
}
