package br.com.taugs.calendario.vinculoMesData.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taugs.calendario.vinculoMesData.entity.VinculoMesData;

@Repository
public interface VinculoMesDataRepository extends JpaRepository<VinculoMesData, Long>{
	List<VinculoMesData> buscarTodos(String nome, String sigla, Integer posicao, Boolean bissexto, Integer nDias);
}
