package fynd_api.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
public class Produto {
    @Setter
    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "O nome é obrigatorio")
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "Quantidade é obrigatória")
    @PositiveOrZero
    private String quantidade;

    @NotBlank(message = "Imagem é obrigatória")
    private String ImagemUrl;

    @NotBlank(message = "Encontrado em é obrigatório")
    private String EncontradoEm;

    @NotBlank(message = "Categoria é obrigatória")
    private String Categoria;

    @NotBlank(message = "Data é obrigatória")
    private LocalDateTime dataCadastro;

    @Enumerated(EnumType.STRING)
    private StatusObjeto status;

}

