package br.com.portifolio.eCommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.portifolio.eCommerce.model.Produto;
import br.com.portifolio.eCommerce.repository.ProdutoRepository;
import br.com.portifolio.eCommerce.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/cadastrarProduto")
	public ModelAndView formulario() {
		
		ModelAndView modelAndView = new ModelAndView("produtos/formProdutos");
		modelAndView.addObject("produto", new Produto());
		return modelAndView;
	}
	
	@RequestMapping(value = "**/salvarProduto", method = RequestMethod.POST)
	public ModelAndView cadastrar(Produto produto) {
		produtoService.save(produto);
		ModelAndView modelAndView = new ModelAndView("produtos/formProdutos");
		modelAndView.addObject("produto", new Produto());
		return modelAndView;
	}
	
	@RequestMapping(value = "/listaProdutos")
	public ModelAndView listaProdutos() {
		ModelAndView modelAndView = new ModelAndView("produtos/listaProdutos");
		Iterable<Produto> produtos = produtoService.findAll();
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
	
	@GetMapping(value = "/editar/{id}")
	public ModelAndView editarProduto(@PathVariable("id") Long id) {
		System.out.println(id);
		Optional<Produto> produto = produtoRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("produtos/formProdutos");
		modelAndView.addObject("produto", produto.get());
		return modelAndView;
	}
	
	@RequestMapping(value="/detalhe/{id}", method=RequestMethod.GET)
	public ModelAndView detalhesProduto(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("produtos/detalhesProdutos");
		Produto produto = produtoService.getOne(id);
		modelAndView.addObject("produto", produto);
		return modelAndView;
	}
	
	@RequestMapping("/deletarProduto")
	public String deletarProduto(Long id) {
		produtoService.deleteById(id);
		return "redirect:/produto/listaProdutos";	
	}
}
