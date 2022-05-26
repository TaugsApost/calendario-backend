package br.com.taugs.calendario.calendario.configuracao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taugs.calendario.calendario.configuracao.entity.ConfiguracaoCalendario;

@Repository
public interface ConfiguracaoCalendairoRepository extends JpaRepository<ConfiguracaoCalendario, Long>{

}
