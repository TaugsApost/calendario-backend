package br.com.taugs.calendario.ano.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.taugs.calendario.data.entity.Data;
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

	public Ano(int posicao, Integer nMeses, Integer numDias, boolean bissexto, String nome) {
		super();
		this.posicao = posicao;
		this.numMeses = nMeses;
		this.numDias = numDias;
		this.bissexto = bissexto;
		this.nome = nome;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_ano")
	private Long id;

	@Column(name = "num_posicao")
	private int posicao;

	@Column(name = "num_meses")
	private Integer numMeses;

	@Column(name = "num_dias")
	private Integer numDias;

	@Column(name = "bol_bissexto")
	private boolean bissexto;

	@Column(name = "nom_nome")
	private String nome;

	@OneToMany(mappedBy = "ano", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonBackReference("dataAno")
	List<Data> datas;

}
