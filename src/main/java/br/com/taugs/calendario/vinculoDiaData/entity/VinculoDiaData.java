package br.com.taugs.calendario.vinculoDiaData.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.taugs.calendario.data.entity.Data;
import br.com.taugs.calendario.dia.entity.Dia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "tb_vinculoDiaData")
@NamedQueries({})
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class VinculoDiaData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_vinculoDiaData")
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name = "idt_dia", referencedColumnName = "idt_dia")
	private Dia dia;
	
	@Column(name="num_posicao")
	private int posicao;
	
	@OneToMany(mappedBy = "dia", cascade = CascadeType.ALL)
	@JsonBackReference("dataDiaData")
	List<Data>datas;
}
