package fynd_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequestDTO(

        @NotBlank(message = "O nome da categoria é obrigatório")
        @Size(max = 30, message = "O nome da categoria pode ter no máximo 30 caracteres")
        String nome
) {}
