package br.com.portifolio.eCommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.portifolio.eCommerce.model.Produto;
import br.com.portifolio.eCommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Iterable<Produto> findAll(){
		return produtoRepository.findAll();
	}
	
	public Produto getOne(Long id) {
		return produtoRepository.getOne(id);
	}
	
	public void save(Produto produto) {
		produtoRepository.save(produto);
	}
	
	public void update(Produto produto) {
		produtoRepository.save(produto);
	}
	
	public void deleteById(Long id) {
		produtoRepository.deleteById(id);
	}
	
}
