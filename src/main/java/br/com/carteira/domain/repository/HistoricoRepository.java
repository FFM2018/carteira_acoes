package br.com.carteira.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.carteira.domain.model.Historico;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long>{
	List<Historico> findAllByOrderByAcaoIdDesc();
	
	@Query("SELECT h FROM Historico h WHERE h.operacao = 'C' AND h.acao.id NOT IN ( SELECT h2.acao.id FROM Historico h2 WHERE h2.operacao = 'V')")
	  List<Historico> findCarteira();
}
