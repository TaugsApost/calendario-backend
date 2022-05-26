package br.com.taugs.calendario.calendario.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.taugs.calendario.calendario.configuracao.entity.ConfiguracaoCalendario;
import br.com.taugs.calendario.data.entity.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_calendario")
@NamedQueries({})
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Calendario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_calendario")
	private Long id;
	
	@OneToMany(mappedBy = "calendario",cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonManagedReference
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Data> datas;
	
	@OneToOne(mappedBy = "calendario", cascade = CascadeType.ALL)
	@JsonManagedReference
	private ConfiguracaoCalendario configuracao;
	
	
}
