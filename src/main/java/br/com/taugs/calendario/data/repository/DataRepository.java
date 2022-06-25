package br.com.taugs.calendario.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taugs.calendario.calendario.entity.Calendario;
import br.com.taugs.calendario.data.entity.Data;
import br.com.taugs.calendario.vinculoMesData.entity.VinculoMesData;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {

	List<Data> buscarDia(Calendario calendario, VinculoMesData mes, String ano);

}
