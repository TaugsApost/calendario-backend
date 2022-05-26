package br.com.taugs.calendario.vinculoDiaData.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taugs.calendario.vinculoDiaData.entity.VinculoDiaData;

@Repository
public interface VinculoDiaDataRepository extends JpaRepository<VinculoDiaData, Long>{

	List<VinculoDiaData> buscarTodos(String nome, String sigla, Integer posicao, Boolean diaInicialCalendario);
}
