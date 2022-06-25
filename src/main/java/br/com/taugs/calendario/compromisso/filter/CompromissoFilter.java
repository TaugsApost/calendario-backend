package br.com.taugs.calendario.compromisso.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.taugs.calendario.data.entity.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompromissoFilter {

	private String titulo;
	private String descricao;
	private Data data;
	private Integer hora;
	private Integer minutos;
	private Long idUsuario;

}
