package fynd_api.controller;

import fynd_api.model.Produto;
import fynd_api.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.aspectj.apache.bcel.Repository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar (@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto adicionar (@RequestBody Produto produto) {
        return repository.save(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id,
                                             @Valid @RequestBody Produto dados) {
        return repository.findById(id).map(p -> {
            p.setNome(dados.getNome());
            p.setDescricao(dados.getDescricao());
            p.setQuantidade(dados.getQuantidade());
            p.setImagemUrl(dados.getImagemUrl());
            return ResponseEntity.ok(repository.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}