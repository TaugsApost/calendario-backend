package br.com.taugs.calendario.compromisso.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.taugs.calendario.data.entity.Data;
import br.com.taugs.calendario.horario.entity.Horario;
import br.com.taugs.calendario.usuario.entity.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_compromisso")
@NamedQueries({ //
        @NamedQuery(name = Compromisso.BUSCAR_POR_USUARIO, query = "SELECT registro FROM Compromisso registro WHERE registro.idUsuario = :idUsuario"), //
        @NamedQuery(name = Compromisso.BUSCAR_TODOS, query = Compromisso.BUSCAR_TODOS_QUERY) //
})
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Compromisso {

	public static final String BUSCAR_POR_USUARIO = "Compromisso.buscarPorUsuario";
	public static final String BUSCAR_TODOS = "Compromisso.buscarTodos";
	public static final String BUSCAR_TODOS_QUERY = "SELECT registro FROM Compromisso registro WHERE " //
	        + "1 = 1 " //
	        + "AND (registro.idUsuario = :idUsuario) " //
	        + "AND ((UPPER(REPLACE(registro.titulo, 'áãàâäçéèëêùûüúóôöïîíÁÀÂÄÃÇÉÈËÊÙÛÜÚÓÔÖÏÎÍ','aaaaaceeeeuuuuoooiiiAAAAACEEEEUUUUOOOIII')) LIKE :titulo)) " //
	        + "AND ((UPPER(REPLACE(registro.descricao, 'áãàâäçéèëêùûüúóôöïîíÁÀÂÄÃÇÉÈËÊÙÛÜÚÓÔÖÏÎÍ','aaaaaceeeeuuuuoooiiiAAAAACEEEEUUUUOOOIII')) LIKE :descricao)) " //
	        + "AND ((horario.minutos = :minutos) OR :minutos IS NULL) " //
	        + "AND ((horario.hora = :hora) OR :hora IS NULL) "//
	        + "AND ((registro.data = :data) OR :data IS NULL)";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_compromisso")
	private Long id;

	@Column(name = "nom_titulo")
	private String titulo;

	@Column(name = "nom_descricao")
	private String descricao;

	@OneToOne
	@JoinColumn(name = "idt_data", referencedColumnName = "idt_data")
	private Data data;

	@OneToOne(cascade = { CascadeType.ALL, CascadeType.REMOVE }, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "idt_horario", referencedColumnName = "idt_horario")
	private Horario horario;

	@Column(name = "idt_user")
	private Long idUsuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	@JoinColumn(name = "idt_user", insertable = false, updatable = false)
	private Usuario usuario;

}
