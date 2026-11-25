package fynd_api.service;

import fynd_api.dto.ProdutoRequestDTO;
import fynd_api.exception.RecursoNaoEncontradoException;
import fynd_api.model.Produto;
import fynd_api.model.StatusObjeto;
import fynd_api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final CategoriaService categoriaService;

    public ProdutoService(ProdutoRepository repository, CategoriaService categoriaService) {
        this.repository = repository;
        this.categoriaService = categoriaService;
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
        produto.setImagemUrl(caminhoLocal(dados.imagemUrl()));
        produto.setEncontradoEm(dados.encontradoEm());
        atualizarDataEncontrado(dados, produto);
        produto.setCategoria(categoriaService.buscarOuCriar(dados.categoria()).getNome());
        produto.setStatus(dados.status());
        atualizarDataDevolucao(produto);
    }

    private void atualizarDataEncontrado(ProdutoRequestDTO dados, Produto produto) {
        if (dados.dataEncontrado() != null) {
            produto.setDataEncontrado(dados.dataEncontrado());
        } else if (produto.getDataEncontrado() == null) {
            produto.setDataEncontrado(LocalDate.now());
        }
    }

    private void atualizarDataDevolucao(Produto produto) {
        if (produto.getStatus() == StatusObjeto.DEVOLVIDO) {
            if (produto.getDataDevolucao() == null) {
                produto.setDataDevolucao(LocalDateTime.now());
            }
        } else {
            produto.setDataDevolucao(null);
        }
    }

    private String caminhoLocal(String url) {
        for (String prefixo : new String[]{"/api/imagens/", "/imagens/"}) {
            int inicio = url.indexOf(prefixo);
            if (inicio >= 0) {
                return url.substring(inicio);
            }
        }
        return url;
    }
}
