package br.com.sustentavel.api_sustentavel.controller;

import br.com.sustentavel.api_sustentavel.dto.AcaoSustentavelRequest;
import br.com.sustentavel.api_sustentavel.dto.AcaoSustentavelResponse;
import br.com.sustentavel.api_sustentavel.model.AcaoSustentavel;
import br.com.sustentavel.api_sustentavel.service.AcaoSustentavelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/acoes") // Define o caminho base para todos os endpoints
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