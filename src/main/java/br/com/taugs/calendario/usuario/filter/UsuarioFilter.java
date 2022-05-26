package br.com.taugs.calendario.usuario.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.taugs.calendario.mes.filter.MesFilter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioFilter {
	
	private String nome;
	private String userName;
	private String senha;
}
