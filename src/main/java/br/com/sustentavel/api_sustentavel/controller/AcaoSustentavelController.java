package br.com.sustentavel.api_sustentavel.controller;

import br.com.sustentavel.api_sustentavel.dto.AcaoSustentavelRequest;
import br.com.sustentavel.api_sustentavel.dto.AcaoSustentavelResponse;
import br.com.sustentavel.api_sustentavel.enums.CategoriaAcao;
import br.com.sustentavel.api_sustentavel.model.AcaoSustentavel;
import br.com.sustentavel.api_sustentavel.service.AcaoSustentavelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/acoes")
public class AcaoSustentavelController {

    @Autowired
    private AcaoSustentavelService service;

    @PostMapping
    public ResponseEntity<AcaoSustentavelResponse> criarAcao(@Valid @RequestBody AcaoSustentavelRequest request) {
        AcaoSustentavel novaAcao = service.salvar(request);
        return new ResponseEntity<>(new AcaoSustentavelResponse(novaAcao), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AcaoSustentavelResponse>> listarTodasAcoes() {
        List<AcaoSustentavel> acoes = service.listarTodas();
        List<AcaoSustentavelResponse> responses = acoes.stream()
                .map(AcaoSustentavelResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcaoSustentavelResponse> buscarAcaoPorId(@PathVariable Long id) {
        AcaoSustentavel acao = service.buscarPorId(id);
        return ResponseEntity.ok(new AcaoSustentavelResponse(acao));
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<AcaoSustentavelResponse>> buscarPorCategoria(@RequestParam("categoria") String categoria) {
        CategoriaAcao categoriaEnum;
        try {
            categoriaEnum = CategoriaAcao.valueOf(categoria.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Categoria inválida: '" + categoria + "'. Os valores aceitos são: " +
                            Arrays.toString(CategoriaAcao.values())
            );
        }

        List<AcaoSustentavel> acoes = service.listarPorCategoria(categoriaEnum);
        List<AcaoSustentavelResponse> responses = acoes.stream()
                .map(AcaoSustentavelResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcaoSustentavelResponse> atualizarAcao(@PathVariable Long id, @Valid @RequestBody AcaoSustentavelRequest request) {
        AcaoSustentavel acaoAtualizada = service.atualizar(id, request);
        return ResponseEntity.ok(new AcaoSustentavelResponse(acaoAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAcao(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}