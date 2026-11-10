package fynd_api.service;
import fynd_api.exception.RecursoNaoEncontradoException;
import fynd_api.model.Produto;
import fynd_api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService (ProdutoRepository repository) {
        this.repository = repository ;
    }
    public List<Produto> listar() {
        return repository.findAll();
    }
    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(()->new RecursoNaoEncontradoException(id));
    }
    public Produto criar (Produto produto) {
        produto.setId(null);
        return repository.save(produto);
    }
    public Produto atualizar(Long id, Produto dados) {
        Produto produto = buscarPorId(id);
        produto.setNome(dados.getNome());
        produto.setDescricao(dados.getDescricao());
        produto.setQuantidade(dados.getQuantidade());
        produto.setImagemUrl(dados.getImagemUrl());
        return repository.save(produto);
    }

    public void excluir(Long id) {
        Produto produto = buscarPorId(id);
        repository.delete(produto);
    }
}