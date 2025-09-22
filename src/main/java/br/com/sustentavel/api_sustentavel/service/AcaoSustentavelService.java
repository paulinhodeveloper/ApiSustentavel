package br.com.sustentavel.api_sustentavel.service;

import br.com.sustentavel.api_sustentavel.dto.AcaoSustentavelRequest;
import br.com.sustentavel.api_sustentavel.model.AcaoSustentavel;
import br.com.sustentavel.api_sustentavel.repository.AcaoSustentavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AcaoSustentavelService {

    @Autowired
    private AcaoSustentavelRepository repository;

    public List<AcaoSustentavel> listarTodas() {
        return repository.findAll();
    }

    public AcaoSustentavel salvar(AcaoSustentavelRequest requestDTO) {
        AcaoSustentavel novaAcao = new AcaoSustentavel();
        novaAcao.setTitulo(requestDTO.getTitulo());
        novaAcao.setDescricao(requestDTO.getDescricao());
        novaAcao.setCategoria(requestDTO.getCategoria());
        novaAcao.setDataRealizacao(requestDTO.getDataRealizacao());
        novaAcao.setResponsavel(requestDTO.getResponsavel());
        return repository.save(novaAcao);
    }

    public AcaoSustentavel buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ação sustentável não encontrada com o ID: " + id));
    }

    public AcaoSustentavel atualizar(Long id, AcaoSustentavelRequest requestDTO) {
        AcaoSustentavel acaoExistente = buscarPorId(id);

        acaoExistente.setTitulo(requestDTO.getTitulo());
        acaoExistente.setDescricao(requestDTO.getDescricao());
        acaoExistente.setCategoria(requestDTO.getCategoria());
        acaoExistente.setDataRealizacao(requestDTO.getDataRealizacao());
        acaoExistente.setResponsavel(requestDTO.getResponsavel());

        return repository.save(acaoExistente);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Ação sustentável não encontrada com o ID: " + id);
        }
        repository.deleteById(id);
    }
}