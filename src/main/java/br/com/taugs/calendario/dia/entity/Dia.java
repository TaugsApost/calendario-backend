package br.com.taugs.calendario.dia.entity;

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

import br.com.taugs.calendario.usuario.entity.Usuario;
import br.com.taugs.calendario.vinculoDiaData.entity.VinculoDiaData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_dia")
@NamedQueries({ //
        @NamedQuery(name = Dia.BUSCAR_POR_ID, query = "SELECT registro FROM Dia registro WHERE registro.id = :id"), //
        @NamedQuery(name = Dia.BUSCAR_TODOS, query = Dia.BUSCAR_TODOS_QUERY), //
        @NamedQuery(name = Dia.BUSCAR_TODOS_VINCULADOS, query = "SELECT registro FROM Dia registro WHERE registro.idUsuario = :id"), //
        @NamedQuery(name = Dia.BUSCAR_OUTROS, query = "SELECT registro FROM Dia registro WHERE registro.idUsuario <> :id AND registro.privado = false"), })
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dia {

	public static final String BUSCAR_POR_ID = "Dia.buscarPorId";
	public static final String BUSCAR_TODOS = "Dia.buscarTodos";
	public static final String BUSCAR_TODOS_VINCULADOS = "Dia.buscarTodosVinculados";
	public static final String BUSCAR_OUTROS = "Dia.buscarOutros";

	public static final String BUSCAR_TODOS_QUERY = "SELECT registro FROM Dia registro WHERE "//
	        + "1 = 1 "//
	        + "AND ((UPPER(REPLACE(registro.nome, 'áãàâäçéèëêùûüúóôöïîíÁÀÂÄÃÇÉÈËÊÙÛÜÚÓÔÖÏÎÍ','aaaaaceeeeuuuuoooiiiAAAAACEEEEUUUUOOOIII')) LIKE :nome)) "//
	        + "AND ((UPPER(REPLACE(registro.sigla, 'áãàâäçéèëêùûüúóôöïîíÁÀÂÄÃÇÉÈËÊÙÛÜÚÓÔÖÏÎÍ','aaaaaceeeeuuuuoooiiiAAAAACEEEEUUUUOOOIII')) LIKE :sigla)) " //
	        + "AND registro.idUsuario = :idUsuario";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_dia")
	private Long id;

	@Column(name = "bol_privado")
	private boolean privado;

	@Column(name = "nom_nome", nullable = false)
	private String nome;

	@Column(name = "nom_sigla", length = 3, nullable = false)
	private String sigla;

	@OneToMany(mappedBy = "dia", cascade = CascadeType.ALL)
	@JsonBackReference("diaData")
	private List<VinculoDiaData> vinculoDiaData;

	@Column(name = "idt_user")
	private Long idUsuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference("user_dia")
	@JoinColumn(name = "idt_user", insertable = false, updatable = false)
	private Usuario usuario;

}
