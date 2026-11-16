package fynd_api.config;

import fynd_api.model.Produto;
import fynd_api.model.StatusObjeto;
import fynd_api.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DadosIniciais implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;

    public DadosIniciais(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void run(String... args) {
        if (produtoRepository.count() > 0) {
            return;
        }

        Produto produto = new Produto();
        produto.setNome("Item de exemplo fynd");
        produto.setDescricao("Produto cadastrado automaticamente para demonstrar a listagem com imagem.");
        produto.setQuantidade(1);
        produto.setImagemUrl("/imagens/fynd-logo.svg");
        produto.setEncontradoEm("Secretaria acadêmica");
        produto.setCategoria("Outros");
        produto.setStatus(StatusObjeto.AGUARDANDO_DONO);
        produtoRepository.save(produto);
    }
}
