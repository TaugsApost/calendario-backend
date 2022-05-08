package br.com.taugs.calendario.data.entity;

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

import br.com.taugs.calendario.ano.entity.Ano;
import br.com.taugs.calendario.vinculoDiaData.entity.VinculoDiaData;
import br.com.taugs.calendario.vinculoMesData.entity.VinculoMesData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_data")
@NamedQueries({})
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_data")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idt_vinculoDiaData", referencedColumnName = "idt_vinculoDiaData")
	private VinculoDiaData dia;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idt_vinculoMesData", referencedColumnName = "idt_vinculoMesData")
	private VinculoMesData mes;
	
	@ManyToOne()
	@JoinColumn(name = "idt_ano", insertable = false, updatable = false)
	private Ano ano;
	
	@Column(name="num_posicaoMes")
	private int posicaoMes;
	
	@Column(name="num_posicaoDia")
	private int posicaoAno;

}
