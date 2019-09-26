package br.com.portifolio.eCommerce.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String nome;
	
	@ManyToMany
	private List<Usuario> usuarios;
	
	public Role() {
	}

	public Role(String nome) {
		this.nome = nome;
		
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String getAuthority() {
		
		return this.nome;
	}

}
