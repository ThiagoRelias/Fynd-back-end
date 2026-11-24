package fynd_api.config;

import fynd_api.model.Produto;
import fynd_api.model.StatusObjeto;
import fynd_api.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DadosIniciais implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final ProdutoRepository produtoRepository;

    public DadosIniciais(JdbcTemplate jdbcTemplate, ProdutoRepository produtoRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void run(String... args) {
        ajustarColunaStatus("produto");
        ajustarColunaStatus("objeto");
        removerExemplosSemImagem();

        cadastrarExemplo(
                "Produto de teste 1",
                "Produto cadastrado automaticamente para testar os cards.",
                "Secretaria acadêmica",
                "Outros",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRggEiBPMkZqqkDrT76PP-69_NgZZIL8laT-tHuzEbOwA&s"
        );

        cadastrarExemplo(
                "Produto de teste 2",
                "Produto cadastrado automaticamente para testar os cards.",
                "Cantina",
                "Outros",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMO6SB1SdJ4jszgeTZO8HrkjlvWbGenYh0rZa0pyjMFI-Jtx6Y_Il2uh0q&s=10"
        );

        cadastrarExemplo(
                "Produto de teste 3",
                "Produto cadastrado automaticamente para testar os cards.",
                "Biblioteca central - 2º andar",
                "Outros",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmuLK3Rvwm-z2Z7k2UJmM-Bm_EkZ_Xf0pbKS2d5gOi2skVz3Rmd-qyPUG2&s=10"
        );

        cadastrarExemplo(
                "Produto de teste 4",
                "Produto cadastrado automaticamente para testar os cards.",
                "Estacionamento bloco B",
                "Outros",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTAeSgrywiC0-z3r8-x1SJF6Xs-0BuM0gG4nEpnjXgtHQ&s=10"
        );
    }

    private void ajustarColunaStatus(String tabela) {
        jdbcTemplate.execute("ALTER TABLE " + tabela + " MODIFY status VARCHAR(20) NOT NULL");
        jdbcTemplate.update("UPDATE " + tabela + " SET status = 'AGUARDANDO_DONO' WHERE status = 'AGUARDADNDO_DONO'");
    }

    private void removerExemplosSemImagem() {
        jdbcTemplate.update("DELETE FROM produto WHERE imagem_url = '' AND nome IN ('Item de exemplo fynd', 'Fones de ouvido sem fio pretos')");
    }

    private void cadastrarExemplo(String nome, String descricao, String encontradoEm, String categoria, String imagemUrl) {
        Integer existentes = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM produto WHERE nome = ?", Integer.class, nome);
        if (existentes != null && existentes > 0) {
            return;
        }

        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setQuantidade(1);
        produto.setImagemUrl(imagemUrl);
        produto.setEncontradoEm(encontradoEm);
        produto.setCategoria(categoria);
        produto.setStatus(StatusObjeto.AGUARDANDO_DONO);
        produtoRepository.save(produto);
    }
}
