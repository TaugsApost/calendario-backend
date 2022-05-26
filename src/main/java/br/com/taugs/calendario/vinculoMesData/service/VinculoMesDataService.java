package br.com.taugs.calendario.vinculoMesData.service;

import java.util.List;

import br.com.taugs.calendario.utils.BaseService;
import br.com.taugs.calendario.vinculoMesData.entity.VinculoMesData;
import br.com.taugs.calendario.vinculoMesData.filter.VinculoMesDataFilter;

public interface VinculoMesDataService extends BaseService<VinculoMesData, Long>{
	
	List<VinculoMesData> consultar(VinculoMesDataFilter filter);
}
