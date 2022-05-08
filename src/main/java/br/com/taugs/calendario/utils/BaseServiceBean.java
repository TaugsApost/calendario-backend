package br.com.taugs.calendario.utils;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseServiceBean<E, ID> implements BaseService<E, ID>{
	
	protected List<E> listarTodos(JpaRepository<E, ID> repositorio){
		return repositorio.findAll();
	}
	
	protected E salvarEntity(E entity, JpaRepository<E, ID> repositorio) {
		beforeSave(entity);
		repositorio.save(entity);
		afterSave(entity);
		return entity;
	}
	
	protected E retornarEntity(ID id, JpaRepository<E, ID> repositorio) {
		if(repositorio.findById(id).isPresent()) {
			return repositorio.findById(id).get();
		}else {
			return null;
		}
	}
	
	protected void beforeSave(E entity) {
		
	}
	
	protected void afterSave(E entity) {
		
	}
	
	protected void deletarEntity(ID id,JpaRepository<E, ID> repositorio) {
		E e = retornarEntity(id, repositorio);
		repositorio.delete(e);
	}

}
