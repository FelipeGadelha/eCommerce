package br.com.portifolio.eCommerce.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Carrinho implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<Produto, Integer> itens = new LinkedHashMap<>();

	public Collection<Produto> getItens() {
		return itens.keySet();
	}
	
	public Map<Produto, Integer> getMap() {
		return itens;
	}
	
	public void add(Produto item) {
		itens.put(item, getQuantidade(item) + 1);
		//itens.forEach((p, i) -> System.out.println(p.getNome() + ": " + i));
	}
	private Integer getQuantidade(Produto produto) {
		if (!itens.containsKey(produto)) {
			itens.put(produto, 0);
		}
		return itens.get(produto);
	}
	public int getQuantidade() {
		return itens.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
	}
	public double getTotal(Produto produto) {
		double total = produto.getTotal(getQuantidade(produto));
		return total;
	}
	public double getTotal() {
		double total = 0;
		for (Produto produto : itens.keySet()) {
			total = total + getTotal(produto);
		}
		return total;
	}

	public int getQuantidadeProduto(Produto produto) {
		return getQuantidade(produto);
	}

	public void remover(Long id) {
		Produto produto = new Produto();
		produto.setId(id);
		if (getQuantidade(produto) >= 2) {
			itens.put(produto, getQuantidade(produto) - 1);
		} else {
			this.itens.remove(produto);
		}
	}
	public void limpa() {
		this.itens.clear();
	}

	
}
