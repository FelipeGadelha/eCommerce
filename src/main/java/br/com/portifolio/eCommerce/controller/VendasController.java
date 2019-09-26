package br.com.portifolio.eCommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.portifolio.eCommerce.model.Vendas;
import br.com.portifolio.eCommerce.repository.VendasRepository;

@Controller
@RequestMapping("/vendas")
public class VendasController {

	@Autowired
	private VendasRepository vendasRepository;	
	
	@RequestMapping(value = "/listaVendas")
	public ModelAndView listaVendas() {
		ModelAndView modelAndView = new ModelAndView();
		Iterable<Vendas> vendas = vendasRepository.findAll();
		modelAndView.addObject("vendas", vendas);
		return modelAndView;		
	}
	
	@RequestMapping("/deletaVenda")
	public String deletarVenda(Long id) {
		vendasRepository.deleteById(id);
		return "redirect:/vendas/listaVendas";
	}
	
	@RequestMapping(value = "/detalhe/{id}", method = RequestMethod.GET)
	public ModelAndView detalhesVendas(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("vendas/detalhesVendas");
		Vendas venda = vendasRepository.getOne(id);
		modelAndView.addObject("venda", venda);
		return modelAndView;
	}
	
	
}
