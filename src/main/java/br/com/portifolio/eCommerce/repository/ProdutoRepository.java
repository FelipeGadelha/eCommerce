package br.com.portifolio.eCommerce.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.portifolio.eCommerce.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
