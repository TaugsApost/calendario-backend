package br.com.taugs.calendario.mes.entity;

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
import br.com.taugs.calendario.vinculoMesData.entity.VinculoMesData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_mes")
@NamedQueries({ @NamedQuery(name = Mes.BUSCAR_POR_ID, query = "SELECT registro FROM Mes registro WHERE registro.id = :id"), //
        @NamedQuery(name = Mes.BUSCAR_TODOS, query = Mes.BUSCAR_TODOS_QUERY), //
        @NamedQuery(name = Mes.BUSCAR_TODOS_VINCULADOS, query = "SELECT registro FROM Mes registro WHERE registro.idUsuario = :id"),//
        @NamedQuery(name = Mes.BUSCAR_OUTROS, query = "SELECT registro FROM Mes registro WHERE registro.idUsuario <> :id AND registro.privado = false")//
})//
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Mes {

	public static final String BUSCAR_POR_ID = "Mes.buscarPorId";
	public static final String BUSCAR_TODOS = "Mes.buscarTodos";
	public static final String BUSCAR_TODOS_VINCULADOS = "Mes.buscarTodosVinculados";
	public static final String BUSCAR_OUTROS = "Mes.buscarOutros";
	public static final String BUSCAR_TODOS_QUERY = "SELECT registro FROM Mes registro WHERE "//
	        + "1 = 1 "//
	        + "AND ((UPPER(REPLACE(registro.nome, 'áãàâäçéèëêùûüúóôöïîíÁÀÂÄÃÇÉÈËÊÙÛÜÚÓÔÖÏÎÍ','aaaaaceeeeuuuuoooiiiAAAAACEEEEUUUUOOOIII')) LIKE :nome)) "//
	        + "AND ((UPPER(REPLACE(registro.sigla, 'áãàâäçéèëêùûüúóôöïîíÁÀÂÄÃÇÉÈËÊÙÛÜÚÓÔÖÏÎÍ','aaaaaceeeeuuuuoooiiiAAAAACEEEEUUUUOOOIII')) LIKE :sigla)) " //
	        + "AND registro.idUsuario = :idUsuario";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_mes")
	private Long id;

	@Column(name = "bol_privado")
	private boolean privado;

	@Column(name = "nom_sigla", length = 3)
	private String sigla;

	@Column(name = "nom_nome")
	private String nome;

	@Column(name = "idt_user")
	private Long idUsuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference("user_mes")
	@JoinColumn(name = "idt_user", insertable = false, updatable = false)
	private Usuario usuario;

	@OneToMany(mappedBy = "mes", cascade = CascadeType.ALL)
	@JsonBackReference("mesData")
	private List<VinculoMesData> vinculoMesData;

}
