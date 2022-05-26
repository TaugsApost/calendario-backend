package br.com.taugs.calendario.vinculoDiaData.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.taugs.calendario.dia.entity.Dia;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VinculoDiaDataFilter {
	
	private Dia dia;
	private int posicao;
	private Boolean diaInicialCalendario;

}
