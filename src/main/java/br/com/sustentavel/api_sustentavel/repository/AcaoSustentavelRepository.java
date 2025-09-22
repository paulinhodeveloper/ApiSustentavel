package br.com.sustentavel.api_sustentavel.repository;

import br.com.sustentavel.api_sustentavel.enums.CategoriaAcao;
import br.com.sustentavel.api_sustentavel.model.AcaoSustentavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcaoSustentavelRepository extends JpaRepository<AcaoSustentavel, Long> {

    List<AcaoSustentavel> findByCategoria(CategoriaAcao categoria);

}