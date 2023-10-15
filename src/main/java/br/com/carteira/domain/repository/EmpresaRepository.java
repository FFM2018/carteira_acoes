package br.com.carteira.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import br.com.carteira.domain.model.Empresa;
import br.com.carteira.domain.model.Setor;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
	@Query("select s from Setor s")
	List<Setor> getAllSetor();
	
	@Query("select e from Empresa e where e.cnpj = :cnpjEmpresa")
	Optional<Empresa> findByCnpj(@Param("cnpjEmpresa") String cnpjEmpresa);
	
//	@Query("from Produto where restaurante.id = :restaurante and id = :produto")
//	Optional<Produto> findById(@Param("restaurante") Long restauranteId,@Param("produto") Long produtoId);
}
