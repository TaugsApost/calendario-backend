package br.com.taugs.calendario.vinculoMesData.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.taugs.calendario.mes.entity.Mes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VinculoMesDataFilter {
	
	private Mes mes;
	private Integer posicao;
	private Boolean bissexto;
	private Integer nDias;
}
