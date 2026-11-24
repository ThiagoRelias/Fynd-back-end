package fynd_api.repository;

import fynd_api.model.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

    List<Solicitacao> findAllByOrderByDataSolicitacaoDesc();

    List<Solicitacao> findByProdutoIdOrderByDataSolicitacaoDesc(Long produtoId);
}
