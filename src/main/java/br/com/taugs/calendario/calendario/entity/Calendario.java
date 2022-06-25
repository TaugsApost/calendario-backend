package br.com.taugs.calendario.calendario.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.taugs.calendario.calendario.configuracao.entity.ConfiguracaoCalendario;
import br.com.taugs.calendario.data.entity.Data;
import br.com.taugs.calendario.usuario.entity.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_calendario")
@NamedQueries({//
        @NamedQuery(name = Calendario.BUSCAR_TODOS, query = "SELECT registro FROM Calendario registro WHERE registro.idUsuario = :id"), //
        @NamedQuery(name = Calendario.BUSCAR_OUTROS, query = "SELECT registro FROM Calendario registro WHERE registro.idUsuario <> :id AND registro.configuracao.privado = false")//
})//
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Calendario {

	public static final String BUSCAR_TODOS = "Calendario.buscarTodos";
	public static final String BUSCAR_OUTROS = "Calendario.buscarOutros";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_calendario")
	private Long id;

	@OneToMany(mappedBy = "calendario", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonManagedReference("calen")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Data> datas;

	@OneToOne(mappedBy = "calendario", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonManagedReference
	private ConfiguracaoCalendario configuracao;

	@Column(name = "idt_user")
	private Long idUsuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference("user_calendario")
	@JoinColumn(name = "idt_user", insertable = false, updatable = false)
	private Usuario usuario;

}
