package fynd_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SolicitacaoRequestDTO(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O contato é obrigatório")
        String contato,

        @NotBlank(message = "Descreva algo que prove que o objeto é seu")
        @Size(max = 1000, message = "A descrição pode ter no máximo 1000 caracteres")
        String descricao
) {}
