package br.com.sustentavel.api_sustentavel.model;

import br.com.sustentavel.api_sustentavel.enums.CategoriaAcao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "acoes_sustentaveis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcaoSustentavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 500)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private CategoriaAcao categoria;

    @Column(nullable = false)
    private LocalDate dataRealizacao;

    @Column(nullable = false)
    private String responsavel;
}