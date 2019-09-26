package br.com.portifolio.eCommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.portifolio.eCommerce.model.Usuario;
import br.com.portifolio.eCommerce.model.Vendas;
import br.com.portifolio.eCommerce.repository.UsuarioRepository;
import br.com.portifolio.eCommerce.repository.VendasRepository;
import br.com.portifolio.eCommerce.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private VendasRepository vendasRepository;
	
	@GetMapping(value = "/cadastrarUsuario")
	public ModelAndView formulario() {
		ModelAndView modelAndView = new ModelAndView("usuarios/formUsuarios");
		modelAndView.addObject("usuario", new Usuario());
		return modelAndView;
	}
	
	@RequestMapping(value = "**/salvarUsuario", method = RequestMethod.POST)
	public ModelAndView cadastrar(Usuario usuario) {
		usuarioService.save(usuario);
		ModelAndView modelAndView = new ModelAndView("usuarios/formUsuarios");
		modelAndView.addObject("usuario", new Usuario());
		return modelAndView;
	}
	
	@RequestMapping(value = "/listaUsuarios")
	public ModelAndView listaUsuarios() {
		ModelAndView modelAndView = new ModelAndView("usuarios/listaUsuarios");
		Iterable<Usuario> usuarios = usuarioService.findAll();
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView;
	}
	
	@RequestMapping(value = "/editar/{id}")
	public ModelAndView editarUsuario(@PathVariable("id") Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		ModelAndView modelAndView = new ModelAndView("usuarios/formUsuarios");
		modelAndView.addObject("usuario", usuario.get());
		return modelAndView;
	}
	
	@RequestMapping(value="/detalhe/{id}", method=RequestMethod.GET)
	public ModelAndView detalhesUsuario(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("usuarios/detalhesUsuarios");
		Usuario usuario = usuarioService.getOne(id);
		Iterable<Vendas> vendas = vendasRepository.findByUsuario(usuario);
		modelAndView.addObject("usuario", usuario);
		modelAndView.addObject("vendas", vendas);
		return modelAndView;
	}
	
	@RequestMapping("/deletarUsuario")
	public String deletarUsuario(Long id) {
		usuarioService.deleteById(id);
		return "redirect:/usuario/listaUsuarios";	
	}
	
	@RequestMapping("/deletaVenda")
	public String deletarVenda(Long id) {
		Optional<Vendas> venda = vendasRepository.findById(id);
		vendasRepository.deleteById(id);
		
		Usuario usuario = venda.get().getUsuario();
		long usuarioId = usuario.getId();
		String user = "" + usuarioId;
		
		
		return "redirect:/usuario/detalhe/" + user;
	}
	
	
	
	
}













