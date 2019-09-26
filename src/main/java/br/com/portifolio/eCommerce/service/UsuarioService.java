package br.com.portifolio.eCommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.portifolio.eCommerce.model.Usuario;
import br.com.portifolio.eCommerce.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Iterable<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	
	public Usuario getOne(Long id) {
		return usuarioRepository.getOne(id);
	}
	
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public void update(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}

}
