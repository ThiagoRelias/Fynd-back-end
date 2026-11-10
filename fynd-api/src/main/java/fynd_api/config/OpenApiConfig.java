package fynd_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI fyndOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Fynd API")
                        .description("API de achados e perdidos do Fynd. Permite cadastrar, listar, editar e excluir os itens encontrados.")
                        .version("1.0.0"));
    }
}
