package br.com.taugs.calendario.data.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.taugs.calendario.calendario.entity.Calendario;
import br.com.taugs.calendario.vinculoMesData.entity.VinculoMesData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataFilter {

	private Calendario calendario;
	private String nomeAno;
	private VinculoMesData mes;

}
