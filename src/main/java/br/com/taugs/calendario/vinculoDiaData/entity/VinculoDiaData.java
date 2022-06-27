package br.com.taugs.calendario.vinculoDiaData.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.taugs.calendario.calendario.configuracao.entity.ConfiguracaoCalendario;
import br.com.taugs.calendario.data.entity.Data;
import br.com.taugs.calendario.dia.entity.Dia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_vinculoDiaData")
@NamedQueries({ @NamedQuery(name = VinculoDiaData.BUSCAR_POR_ID, query = "SELECT registro FROM VinculoDiaData registro WHERE registro.id = :id"), @NamedQuery(name = VinculoDiaData.BUSCAR_TODOS, query = VinculoDiaData.BUSCAR_TODOS_QUERY) })
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class VinculoDiaData {

	public static final String BUSCAR_POR_ID = "VinculoDiaData.buscarPorId";
	public static final String BUSCAR_TODOS = "VinculoDiaData.buscarTodos";
	public static final String BUSCAR_TODOS_QUERY = "SELECT registro FROM VinculoDiaData registro WHERE "//
	        + "1 = 1 "//
	        + "AND ((UPPER(REPLACE(registro.dia.nome, 'áãàâäçéèëêùûüúóôöïîíÁÀÂÄÃÇÉÈËÊÙÛÜÚÓÔÖÏÎÍ','aaaaaceeeeuuuuoooiiiAAAAACEEEEUUUUOOOIII')) LIKE :nome)) "//
	        + "AND ((UPPER(REPLACE(registro.dia.sigla, 'áãàâäçéèëêùûüúóôöïîíÁÀÂÄÃÇÉÈËÊÙÛÜÚÓÔÖÏÎÍ','aaaaaceeeeuuuuoooiiiAAAAACEEEEUUUUOOOIII')) LIKE :sigla)) "//
	        + "AND (((registro.posicao = :posicao) OR :posicao IS NULL) OR NULL IS NULL) " + "AND (((registro.diaInicialCalendario = :diaInicialCalendario) OR :diaInicialCalendario IS NULL) OR NULL IS NULL)";//

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_vinculoDiaData")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "idt_dia", referencedColumnName = "idt_dia")
	private Dia dia;

	@Column(name = "num_posicao")
	private Integer posicao;

	@OneToMany(mappedBy = "dia", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonBackReference("dataDiaData")
	List<Data> datas;

	@Column(name = "bol_diaInicial")
	private Boolean diaInicialCalendario;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idt_configuracao", referencedColumnName = "idt_configuracao")
	@JsonBackReference
	@ToString.Exclude
	private ConfiguracaoCalendario configuracao;
}
