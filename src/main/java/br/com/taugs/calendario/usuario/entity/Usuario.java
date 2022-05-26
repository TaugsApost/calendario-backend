package br.com.taugs.calendario.usuario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.taugs.calendario.mes.entity.Mes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_usuario")
@NamedQueries({
	@NamedQuery(name = Usuario.BUSCAR_POR_ID, query = "SELECT registro FROM Usuario registro WHERE registro.id = :id"),
	@NamedQuery(name = Usuario.BUSCAR_POR_USER_NAME, query = "SELECT registro FROM Usuario registro WHERE registro.userName = :userName"),
	@NamedQuery(name = Usuario.BUSCAR_TODOS, query = Usuario.BUSCAR_TODOS_QUERY)
})
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Usuario{
	
	public static final String BUSCAR_POR_ID = "Usuario.buscarPorId";
	public static final String BUSCAR_POR_USER_NAME = "Usuario.buscarPorUserName";
	public static final String BUSCAR_TODOS = "Usuario.buscarTodos";
	public static final String BUSCAR_TODOS_QUERY = "SELECT registro FROM Usuario registro WHERE "//
			+ "1 = 1 "//
			+ "AND ((UPPER(REPLACE(registro.nome, 'áãàâäçéèëêùûüúóôöïîíÁÀÂÄÃÇÉÈËÊÙÛÜÚÓÔÖÏÎÍ','aaaaaceeeeuuuuoooiiiAAAAACEEEEUUUUOOOIII')) LIKE :nome)) "//
			+ "AND ((UPPER(REPLACE(registro.userName, 'áãàâäçéèëêùûüúóôöïîíÁÀÂÄÃÇÉÈËÊÙÛÜÚÓÔÖÏÎÍ','aaaaaceeeeuuuuoooiiiAAAAACEEEEUUUUOOOIII')) LIKE :userName))";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idt_user")
	private Long id;
	
	@Column(name = "nom_nome", nullable = false)
	private String nome;
	
	@Column(name = "nom_userName", nullable = false)
	private String userName;
	
	@Column(name = "nom_senha", nullable = false)
	private String senha;
	
	@Column(name = "tk_token")
	private String token;

}
