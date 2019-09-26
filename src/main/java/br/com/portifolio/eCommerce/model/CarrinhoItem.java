package br.com.portifolio.eCommerce.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CarrinhoItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Produto produto;
	private int quantidade;
	private double total;
	
	public CarrinhoItem() {
	}
	
	public CarrinhoItem(Produto produto, Integer quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.total= quantidade * produto.getValor();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getTotal() {
		return total;
	}
	@Override
	public String toString() {
		return "CarrinhoItem [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + "]";
	}

	
	
	
	
}
