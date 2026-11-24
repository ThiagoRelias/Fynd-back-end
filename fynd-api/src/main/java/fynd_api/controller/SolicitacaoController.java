package fynd_api.controller;

import fynd_api.dto.SolicitacaoRequestDTO;
import fynd_api.model.Solicitacao;
import fynd_api.service.SolicitacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Solicitações", description = "Pedidos de quem se diz dono de um objeto")
public class SolicitacaoController {

    private final SolicitacaoService service;

    public SolicitacaoController(SolicitacaoService service) {
        this.service = service;
    }

    @PostMapping("/produtos/{id}/solicitacoes")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Envia uma solicitação para ser reconhecido como dono do objeto")
    public Solicitacao criar(@PathVariable Long id, @Valid @RequestBody SolicitacaoRequestDTO dados) {
        return service.criar(id, dados);
    }

    @GetMapping("/solicitacoes")
    @Operation(summary = "Lista todas as solicitações, das mais recentes para as mais antigas")
    public List<Solicitacao> listar() {
        return service.listar();
    }

    @GetMapping("/produtos/{id}/solicitacoes")
    @Operation(summary = "Lista as solicitações de um objeto")
    public List<Solicitacao> listarPorProduto(@PathVariable Long id) {
        return service.listarPorProduto(id);
    }
}
