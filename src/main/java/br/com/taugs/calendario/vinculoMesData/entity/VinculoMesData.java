package br.com.taugs.calendario.vinculoMesData.entity;

import java.math.BigDecimal;
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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.taugs.calendario.calendario.configuracao.entity.ConfiguracaoCalendario;
import br.com.taugs.calendario.data.entity.Data;
import br.com.taugs.calendario.mes.entity.Mes;
import br.com.taugs.calendario.vinculoDiaData.entity.VinculoDiaData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_vinculoMesData")
@NamedQueries({
	@NamedQuery(name = VinculoMesData.BUSCAR_POR_ID, query = "SELECT registro FROM VinculoMesData registro WHERE registro.id = :id"),
	@NamedQuery(name = VinculoMesData.BUSCAR_TODOS, query = VinculoMesData.BUSCAR_TODOS_QUERY)
})
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class VinculoMesData {
	
	public static final String BUSCAR_POR_ID = "VinculoMesData.buscarPorId";
	public static final String BUSCAR_TODOS = "VinculoMesData.buscarTodos";
	public static final String BUSCAR_TODOS_QUERY = "SELECT registro FROM VinculoMesData registro WHERE "//
			+ "1 = 1 "//
			+ "AND ((UPPER(REPLACE(registro.mes.nome, 'áãàâäçéèëêùûüúóôöïîíÁÀÂÄÃÇÉÈËÊÙÛÜÚÓÔÖÏÎÍ','aaaaaceeeeuuuuoooiiiAAAAACEEEEUUUUOOOIII')) LIKE :nome)) "//
			+ "AND ((UPPER(REPLACE(registro.mes.sigla, 'áãàâäçéèëêùûüúóôöïîíÁÀÂÄÃÇÉÈËÊÙÛÜÚÓÔÖÏÎÍ','aaaaaceeeeuuuuoooiiiAAAAACEEEEUUUUOOOIII')) LIKE :sigla)) "//
			+ "AND (((registro.posicao = :posicao) OR :posicao IS NULL) OR NULL IS NULL) "//
			+ "AND (((registro.numDias = :numDias) OR :nDias IS NULL) OR NULL IS NULL) " //
			+ "AND (((registro.bissexto = :bissexto) OR :bissexto IS NULL) OR NULL IS NULL) ";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_vinculoMesData")
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name = "idt_mes", referencedColumnName = "idt_mes")
	private Mes mes;
	
	@Column(name="num_posicao")
	private Integer posicao;
	
	@Column(name="num_nDias")
	private Integer numDias;
	
	@Column(name="bol_bissexto")
	private Boolean bissexto;
	
	@OneToMany(mappedBy = "mes", cascade = CascadeType.ALL)
	@JsonBackReference("dataMesData")
	List<Data>datas;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idt_configuracao", referencedColumnName = "idt_configuracao")
	@JsonBackReference
	private ConfiguracaoCalendario configuracao;

}
