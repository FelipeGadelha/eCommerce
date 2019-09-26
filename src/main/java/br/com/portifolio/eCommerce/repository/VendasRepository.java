package br.com.portifolio.eCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portifolio.eCommerce.model.Usuario;
import br.com.portifolio.eCommerce.model.Vendas;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, Long>{
	
	Iterable<Vendas> findByUsuario(Usuario usuario);

}
