package br.com.taugs.calendario.vinculoMesData.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.taugs.calendario.dia.entity.Dia;
import br.com.taugs.calendario.mes.entity.Mes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_vinculoMesData")
@NamedQueries({})
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class VinculoMesData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_vinculoMesData")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonManagedReference("mesData")
	@JoinColumn(name = "idt_mes", insertable = false, updatable = false)
	private Mes mes;
	
	@Column(name="num_posicao")
	private int posicao;
	
	@Column(name="num_nDias")
	private BigDecimal nDias;
	
	@Column(name="bol_bissexto")
	private boolean bissexto;

}
