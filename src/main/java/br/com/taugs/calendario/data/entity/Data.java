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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.taugs.calendario.ano.entity.Ano;
import br.com.taugs.calendario.calendario.configuracao.entity.ConfiguracaoCalendario;
import br.com.taugs.calendario.calendario.entity.Calendario;
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

	public Data(VinculoDiaData dia, VinculoMesData mes, Ano ano, int posicaoMes, int posicaoAno) {
		super();
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.posicaoMes = posicaoMes;
		this.posicaoAno = posicaoAno;
	}

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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idt_ano", referencedColumnName = "idt_ano")
	private Ano ano;

	@Column(name = "num_posicaoMes")
	private int posicaoMes;

	@Column(name = "num_posicaoAno")
	private int posicaoAno;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idt_calendario", referencedColumnName = "idt_calendario")
	@JsonBackReference
	private Calendario calendario;

}
