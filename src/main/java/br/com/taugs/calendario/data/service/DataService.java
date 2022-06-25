package br.com.taugs.calendario.data.service;

import java.util.List;

import br.com.taugs.calendario.data.entity.Data;
import br.com.taugs.calendario.data.filter.DataFilter;
import br.com.taugs.calendario.utils.BaseService;

public interface DataService extends BaseService<Data, Long> {

	List<Data> consultar(DataFilter filter);
}
