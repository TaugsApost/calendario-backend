package br.com.taugs.calendario.ano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taugs.calendario.ano.entity.Ano;

@Repository
public interface AnoRepository extends JpaRepository<Ano, Long>{

}
