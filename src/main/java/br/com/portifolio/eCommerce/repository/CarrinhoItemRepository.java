package br.com.portifolio.eCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portifolio.eCommerce.model.CarrinhoItem;

@Repository
public interface CarrinhoItemRepository extends JpaRepository<CarrinhoItem, Long>{
	
	//Iterable<Vendas> findByUsuario(Usuario usuario);

}
