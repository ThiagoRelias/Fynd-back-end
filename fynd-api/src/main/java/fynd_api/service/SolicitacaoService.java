package fynd_api.service;

import fynd_api.dto.SolicitacaoRequestDTO;
import fynd_api.exception.RecursoNaoEncontradoException;
import fynd_api.model.Produto;
import fynd_api.model.Solicitacao;
import fynd_api.repository.SolicitacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitacaoService {

    private final SolicitacaoRepository repository;
    private final ProdutoService produtoService;

    public SolicitacaoService(SolicitacaoRepository repository, ProdutoService produtoService) {
        this.repository = repository;
        this.produtoService = produtoService;
    }

    public Solicitacao criar(Long produtoId, SolicitacaoRequestDTO dados) {
        Produto produto = produtoService.buscarPorId(produtoId);

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setProduto(produto);
        solicitacao.setNome(dados.nome().trim());
        solicitacao.setContato(dados.contato().trim());
        solicitacao.setDescricao(dados.descricao().trim());
        return repository.save(solicitacao);
    }

    public List<Solicitacao> listar() {
        return repository.findAllByOrderByDataSolicitacaoDesc();
    }

    public List<Solicitacao> listarPorProduto(Long produtoId) {
        produtoService.buscarPorId(produtoId);
        return repository.findByProdutoIdOrderByDataSolicitacaoDesc(produtoId);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException(id);
        }
        repository.deleteById(id);
    }
}
