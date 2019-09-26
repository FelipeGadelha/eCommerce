package br.com.portifolio.eCommerce.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Vendas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Usuario usuario;
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<CarrinhoItem> itens; 
	private double total;
	private LocalDateTime dataVenda = LocalDateTime.now();
	
	public Vendas() {
		
	}
	
	public Vendas(Collection<CarrinhoItem> itens, Usuario usuarioLogado) {
		this.itens = itens;
		this.total = itens.stream().mapToDouble(CarrinhoItem::getTotal).sum();
		this.usuario = usuarioLogado;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Collection<CarrinhoItem> getItens() {
		return itens;
	}
	public double getTotal() {
		return total;
	}
	public LocalDateTime getDataVenda() {
		return dataVenda;
	}

	
}
