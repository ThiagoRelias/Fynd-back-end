package fynd_api.service;

import fynd_api.exception.RecursoNaoEncontradoException;
import fynd_api.model.Imagem;
import fynd_api.repository.ImagemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Set;

@Service
public class ImagemService {

    private static final Set<String> TIPOS_PERMITIDOS = Set.of("image/png", "image/jpeg", "image/webp", "image/gif");

    private final ImagemRepository repository;

    public ImagemService(ImagemRepository repository) {
        this.repository = repository;
    }

    public String salvar(MultipartFile arquivo) {
        if (arquivo == null || arquivo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Selecione uma imagem");
        }

        String tipo = arquivo.getContentType();
        if (tipo == null || !TIPOS_PERMITIDOS.contains(tipo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A imagem deve ser PNG, JPG, WEBP ou GIF");
        }

        Imagem imagem = new Imagem();
        imagem.setTipo(tipo);
        try {
            imagem.setDados(arquivo.getBytes());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível ler a imagem");
        }

        Imagem salva = repository.save(imagem);
        return "/api/imagens/" + salva.getId();
    }

    public Imagem buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(id));
    }
}
