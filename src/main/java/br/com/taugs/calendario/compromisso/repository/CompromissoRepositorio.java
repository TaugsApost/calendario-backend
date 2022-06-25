package br.com.taugs.calendario.compromisso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taugs.calendario.compromisso.entity.Compromisso;
import br.com.taugs.calendario.data.entity.Data;

@Repository
public interface CompromissoRepositorio extends JpaRepository<Compromisso, Long> {

	List<Compromisso> buscarPorUsuario(Long idUsuario);

	List<Compromisso> buscarTodos(Long idUsuario, String titulo, String descricao, Integer minutos, Integer hora, Data data);

}
