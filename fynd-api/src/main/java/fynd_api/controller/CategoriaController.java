package fynd_api.controller;

import fynd_api.dto.CategoriaRequestDTO;
import fynd_api.model.Categoria;
import fynd_api.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "Categorias usadas no cadastro dos objetos")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lista todas as categorias em ordem alfabética")
    public List<Categoria> listar() {
        return service.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria uma nova categoria ou devolve a existente com o mesmo nome")
    public Categoria criar(@Valid @RequestBody CategoriaRequestDTO dados) {
        return service.criar(dados);
    }
}
