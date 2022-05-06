package br.com.taugs.calendario.mes.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.taugs.calendario.vinculoDiaData.entity.VinculoDiaData;
import br.com.taugs.calendario.vinculoMesData.entity.VinculoMesData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_mes")
@NamedQueries({})
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Mes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_mes")
	private Long id;
	
	@Column(name = "nom_sigla", length = 3)
	private String sigla;
	
	@Column(name = "nom_nome")
	private String nome;
	
	@OneToMany(mappedBy = "mes")
	@JsonBackReference("mesData")
	private List<VinculoMesData> vinculoMesData;

}
