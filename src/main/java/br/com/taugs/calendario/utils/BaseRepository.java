package br.com.taugs.calendario.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity<Long>, ID> extends JpaRepository<E, ID> {

}
