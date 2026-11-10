package fynd_api.service;

import fynd_api.dto.ProdutoRequestDTO;
import fynd_api.exception.RecursoNaoEncontradoException;
import fynd_api.model.Produto;
import fynd_api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> listar() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(id));
    }

    public Produto criar(ProdutoRequestDTO dados) {
        Produto produto = new Produto();
        copiarDados(dados, produto);
        return repository.save(produto);
    }

    public Produto atualizar(Long id, ProdutoRequestDTO dados) {
        Produto produto = buscarPorId(id);
        copiarDados(dados, produto);
        return repository.save(produto);
    }

    public void excluir(Long id) {
        Produto produto = buscarPorId(id);
        repository.delete(produto);
    }

    private void copiarDados(ProdutoRequestDTO dados, Produto produto) {
        produto.setNome(dados.nome());
        produto.setDescricao(dados.descricao());
        produto.setQuantidade(dados.quantidade());
        produto.setImagemUrl(dados.imagemUrl());
        produto.setEncontradoEm(dados.encontradoEm());
        produto.setCategoria(dados.categoria());
        produto.setStatus(dados.status());
    }
}
