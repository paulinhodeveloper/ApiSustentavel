package br.com.sustentavel.api_sustentavel.repository;

import br.com.sustentavel.api_sustentavel.model.AcaoSustentavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcaoSustentavelRepository extends JpaRepository<AcaoSustentavel, Long> {

}