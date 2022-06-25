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
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.taugs.calendario.ano.entity.Ano;
import br.com.taugs.calendario.calendario.entity.Calendario;
import br.com.taugs.calendario.vinculoDiaData.entity.VinculoDiaData;
import br.com.taugs.calendario.vinculoMesData.entity.VinculoMesData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_data")
@NamedQueries({//
        @NamedQuery(name = Data.BUSCAR_DIA, query = Data.BUSCAR_DIA_QUERY) //
})
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

	public static final String BUSCAR_DIA = "Data.buscarDia";
	public static final String BUSCAR_DIA_QUERY = "SELECT registro FROM Data registro WHERE " //
	        + "1 = 1 " //
	        + "AND (registro.calendario = :calendario) " //
	        + "AND (registro.mes = :mes) " //
	        + "AND (registro.ano.nome = :ano) "; //

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

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "idt_vinculoDiaData", referencedColumnName = "idt_vinculoDiaData")
	private VinculoDiaData dia;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "idt_vinculoMesData", referencedColumnName = "idt_vinculoMesData")
	private VinculoMesData mes;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "idt_ano", referencedColumnName = "idt_ano")
	private Ano ano;

	@Column(name = "num_posicaoMes")
	private int posicaoMes;

	@Column(name = "num_posicaoAno")
	private int posicaoAno;

	@Column(name = "idt_calendario")
	private Long idCalendario;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idt_calendario", referencedColumnName = "idt_calendario", insertable = false, updatable = false)
	@JsonBackReference("calen")
	private Calendario calendario;

}
