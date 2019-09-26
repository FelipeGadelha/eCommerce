package br.com.portifolio.eCommerce.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.portifolio.eCommerce.model.Carrinho;
import br.com.portifolio.eCommerce.model.CarrinhoItem;
import br.com.portifolio.eCommerce.model.Produto;
import br.com.portifolio.eCommerce.model.Usuario;
import br.com.portifolio.eCommerce.model.Vendas;
import br.com.portifolio.eCommerce.repository.CarrinhoItemRepository;
import br.com.portifolio.eCommerce.repository.ProdutoRepository;
import br.com.portifolio.eCommerce.repository.UsuarioRepository;
import br.com.portifolio.eCommerce.repository.VendasRepository;

@Controller
@RequestMapping(value = "/carrinho")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoController {

	@Autowired
	private Carrinho carrinho;
	
	@Autowired
	private ProdutoRepository produtoRepository; 
	
	@Autowired
	private VendasRepository vendasRepository;
	
	@Autowired
	private CarrinhoItemRepository carrinhoItemRepository;
	
	@Autowired
	private UsuarioRepository usuarioPeository;
	
	@RequestMapping("/add")
	@Transactional(readOnly = true)
	public ModelAndView add(Long id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho/compras");
		Produto produto = produtoRepository.findById(id).orElse(new Produto());
		carrinho.add(produto);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/compras", method = RequestMethod.GET)
	@Transactional
	public ModelAndView listaItens() {
		ModelAndView modelAndView = new ModelAndView("carrinho/carrinho");
		Collection<Produto> produtos = carrinho.getItens();
		modelAndView.addObject("carrinho", carrinho);
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
	
	@RequestMapping("/remover")
	public ModelAndView remover(Long id) {
		carrinho.remover(id);
		return new ModelAndView("redirect:/carrinho/compras");
	}
	
	@RequestMapping("/finalizar")
	public ModelAndView finalizar() {
		ModelAndView modelAndView = new ModelAndView("redirect:/produto/listaProdutos");
		Map<Produto, Integer> map = carrinho.getMap();
		List<CarrinhoItem> itens = new ArrayList<>();
		
		for (Entry<Produto, Integer> entry : map.entrySet()) {
			CarrinhoItem carrinhoItem = new CarrinhoItem(entry.getKey(), entry.getValue());
			itens.add(carrinhoItem);
		}
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email;
		if (principal instanceof UserDetails) {
		    email = ((UserDetails)principal).getUsername();   
		} else {
		    email = principal.toString();
		}
		Usuario usuarioLogado = usuarioPeository.findByEmail(email);  
		Vendas venda = new Vendas(itens, usuarioLogado);
		itens.forEach(i -> carrinhoItemRepository.save(i));
		vendasRepository.save(venda);
		
		carrinho.limpa();
		itens.forEach(System.out::println);
		return modelAndView;
		
	}
	
	
	
	
}
