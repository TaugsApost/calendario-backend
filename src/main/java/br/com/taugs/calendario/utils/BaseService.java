package br.com.taugs.calendario.utils;

import java.util.List;

public interface BaseService<E, ID> {
	
	List<E> listar();
	E salvar(E entity);
	E detalhar(ID id);
	void deletar(ID id);

}
