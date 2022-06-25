package br.com.taugs.calendario.mes.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MesFilter {

	private String nome;
	private String sigla;
	private Long idUser;
}
