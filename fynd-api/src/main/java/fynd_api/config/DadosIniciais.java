package fynd_api.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DadosIniciais implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public DadosIniciais(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {
        ajustarColunaStatus("produto");
        ajustarColunaStatus("objeto");
        removerProdutoExemplo();
    }

    private void ajustarColunaStatus(String tabela) {
        jdbcTemplate.execute("ALTER TABLE " + tabela + " MODIFY status VARCHAR(20) NOT NULL");
        jdbcTemplate.update("UPDATE " + tabela + " SET status = 'AGUARDANDO_DONO' WHERE status = 'AGUARDADNDO_DONO'");
    }

    private void removerProdutoExemplo() {
        jdbcTemplate.update("DELETE FROM produto WHERE nome = 'Item de exemplo fynd' AND imagem_url LIKE '/imagens/fynd-logo.%'");
    }
}
