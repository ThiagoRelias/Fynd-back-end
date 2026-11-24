package fynd_api.service;

import fynd_api.dto.StatsResponseDTO;
import fynd_api.model.Produto;
import fynd_api.model.StatusObjeto;
import fynd_api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class StatsService {

    private final ProdutoRepository repository;

    public StatsService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public StatsResponseDTO calcular() {
        List<Produto> produtos = repository.findAll();

        long total = produtos.size();
        long devolvidos = produtos.stream()
                .filter(p -> p.getStatus() == StatusObjeto.DEVOLVIDO)
                .count();

        double tempoMedioDias = produtos.stream()
                .filter(p -> p.getStatus() == StatusObjeto.DEVOLVIDO && p.getDataDevolucao() != null)
                .mapToDouble(p -> Duration.between(p.getDataCadastro(), p.getDataDevolucao()).toHours() / 24.0)
                .average()
                .orElse(0);

        return new StatsResponseDTO(total, total - devolvidos, devolvidos, Math.round(tempoMedioDias * 10) / 10.0);
    }
}
