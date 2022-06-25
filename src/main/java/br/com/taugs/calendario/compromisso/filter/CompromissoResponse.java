package br.com.taugs.calendario.compromisso.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompromissoResponse {

	private Long id;
	private String titulo;
	private String descricao;
	private String textoData;
	private String textoHorario;

}
