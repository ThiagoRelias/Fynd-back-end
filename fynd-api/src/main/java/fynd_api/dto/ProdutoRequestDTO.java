package fynd_api.dto;

import fynd_api.model.StatusObjeto;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ProdutoRequestDTO(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "A descrição é obrigatória")
        String descricao,

        @NotNull(message = "A quantidade é obrigatória")
        @PositiveOrZero(message = "A quantidade não pode ser negativa")
        Integer quantidade,

        @NotBlank(message = "A imagem é obrigatória")
        String imagemUrl,

        @NotBlank(message = "Encontrado em é obrigatório")
        String encontradoEm,

        @PastOrPresent(message = "A data em que foi encontrado não pode ser no futuro")
        LocalDate dataEncontrado,

        @NotBlank(message = "A categoria é obrigatória")
        String categoria,

        @NotNull(message = "O status é obrigatório")
        StatusObjeto status
) {}