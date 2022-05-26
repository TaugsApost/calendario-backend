package br.com.taugs.calendario.vinculoDiaData.service;

import java.util.List;

import br.com.taugs.calendario.utils.BaseService;
import br.com.taugs.calendario.vinculoDiaData.entity.VinculoDiaData;
import br.com.taugs.calendario.vinculoDiaData.filter.VinculoDiaDataFilter;

public interface VinculoDiaDataService extends BaseService<VinculoDiaData, Long>{
	
	List<VinculoDiaData> consultar(VinculoDiaDataFilter filter);
}
