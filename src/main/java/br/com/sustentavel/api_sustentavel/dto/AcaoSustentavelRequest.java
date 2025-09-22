package br.com.sustentavel.api_sustentavel.dto;

import br.com.sustentavel.api_sustentavel.enums.CategoriaAcao;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AcaoSustentavelRequest {

    @NotBlank(message = "O título é obrigatório.")
    @Size(min = 5, max = 100, message = "O título deve ter entre 5 e 100 caracteres.")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória.")
    @Size(max = 500, message = "A descrição não pode exceder 500 caracteres.")
    private String descricao;

    @NotNull(message = "A categoria é obrigatória.")
    private CategoriaAcao categoria;

    @NotNull(message = "A data de realização é obrigatória.")
    @FutureOrPresent(message = "A data de realização não pode ser no passado.")
    private LocalDate dataRealizacao;

    @NotBlank(message = "O nome do responsável é obrigatório.")
    private String responsavel;
}