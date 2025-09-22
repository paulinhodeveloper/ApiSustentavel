package br.com.sustentavel.api_sustentavel.dto;

import br.com.sustentavel.api_sustentavel.enums.CategoriaAcao;
import br.com.sustentavel.api_sustentavel.model.AcaoSustentavel;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AcaoSustentavelResponse {

    private Long id;
    private String titulo;
    private String descricao;
    private CategoriaAcao categoria;
    private LocalDate dataRealizacao;
    private String responsavel;

    public AcaoSustentavelResponse(AcaoSustentavel entidade) {
        this.id = entidade.getId();
        this.titulo = entidade.getTitulo();
        this.descricao = entidade.getDescricao();
        this.categoria = entidade.getCategoria();
        this.dataRealizacao = entidade.getDataRealizacao();
        this.responsavel = entidade.getResponsavel();
    }
}