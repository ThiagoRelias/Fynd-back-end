package fynd_api.service;

import fynd_api.dto.CategoriaRequestDTO;
import fynd_api.model.Categoria;
import fynd_api.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class    CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> listar() {
        return repository.findAllByOrderByNomeAsc();
    }

    public Categoria criar(CategoriaRequestDTO dados) {
        return buscarOuCriar(dados.nome());
    }

    public Categoria buscarOuCriar(String nome) {
        String nomeLimpo = nome.trim();
        return repository.findByNomeIgnoreCase(nomeLimpo)
                .orElseGet(() -> {
                    Categoria categoria = new Categoria();
                    categoria.setNome(nomeLimpo);
                    return repository.save(categoria);
                });
    }
}
