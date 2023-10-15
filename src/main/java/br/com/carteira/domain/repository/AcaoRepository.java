package br.com.carteira.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.carteira.domain.model.Acao;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Long>{

}
