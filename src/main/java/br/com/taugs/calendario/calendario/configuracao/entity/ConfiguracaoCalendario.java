package br.com.taugs.calendario.calendario.configuracao.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.taugs.calendario.calendario.entity.Calendario;
import br.com.taugs.calendario.vinculoDiaData.entity.VinculoDiaData;
import br.com.taugs.calendario.vinculoMesData.entity.VinculoMesData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "tb_configuracao")
@NamedQueries({})
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)

public class ConfiguracaoCalendario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_configuracao")
	private Long id;
	
	@OneToMany(mappedBy = "configuracao",cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonManagedReference
	@Fetch(value = FetchMode.SUBSELECT)
	private List<VinculoDiaData> dias;
	
	@OneToMany(mappedBy = "configuracao",cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonManagedReference
	@Fetch(value = FetchMode.SUBSELECT)
	private List<VinculoMesData> meses;
	
	@Column(name = "num_anoInicial")
	private Integer anoInicial;
	
	@Column(name = "num_anoFinal")
	private Integer anoFinal;
	
	@Column(name = "bol_bissexto")
	private Boolean bissexto;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	@JoinColumn(name = "idt_calendario", referencedColumnName = "idt_calendario")
	private Calendario calendario;
}