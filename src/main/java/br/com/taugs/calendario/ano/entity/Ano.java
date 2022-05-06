package br.com.taugs.calendario.ano.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "tb_ano")
@NamedQueries({})
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ano {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_ano")
	private Long id;
	
	@Column(name = "num_posicao")
	private int posicao;
	
	@Column(name = "num_meses")
	private BigDecimal nMeses;
	
	@Column(name = "bol_bissexto")
	private boolean bissexto;
	
	@Column(name = "nom_nome")
	private String nome;
	

}
