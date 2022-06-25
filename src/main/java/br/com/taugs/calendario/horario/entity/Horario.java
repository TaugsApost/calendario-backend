package br.com.taugs.calendario.horario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_horario")
@NamedQueries({})
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Horario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_horario")
	private Long id;

	@Column(name = "num_min")
	private Integer minutos;

	@Column(name = "num_hora")
	private Integer hora;

}
