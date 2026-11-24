package fynd_api.controller;

import fynd_api.dto.StatsResponseDTO;
import fynd_api.service.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
@Tag(name = "Estatísticas", description = "Números exibidos nos cards da tela inicial")
public class StatsController {

    private final StatsService service;

    public StatsController(StatsService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Total de objetos, devolvidos e tempo médio de retorno em dias")
    public StatsResponseDTO buscar() {
        return service.calcular();
    }
}
