package br.com.carteira.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.carteira.domain.model.Carteira;

@Repository
public interface CarteiraRepository  extends JpaRepository<Carteira, Long>{
	
//	@Query("SELECT operacao, acao_id FROM historico WHERE operacao = 'C'")
//	  List<Historico> findCarteiraCompra();
}
