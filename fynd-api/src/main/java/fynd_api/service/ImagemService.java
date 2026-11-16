package fynd_api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@Service
public class ImagemService {

    private static final Map<String, String> TIPOS_PERMITIDOS = Map.of(
            "image/png", ".png",
            "image/jpeg", ".jpg",
            "image/webp", ".webp",
            "image/gif", ".gif"
    );

    private final Path pasta;

    public ImagemService(@Value("${fynd.upload.pasta:uploads}") String pasta) {
        this.pasta = Paths.get(pasta).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.pasta);
        } catch (IOException e) {
            throw new UncheckedIOException("Não foi possível criar a pasta de uploads", e);
        }
    }

    public String salvar(MultipartFile arquivo) {
        if (arquivo == null || arquivo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Selecione uma imagem");
        }

        String extensao = TIPOS_PERMITIDOS.get(arquivo.getContentType());
        if (extensao == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A imagem deve ser PNG, JPG, WEBP ou GIF");
        }

        String nome = UUID.randomUUID() + extensao;
        try {
            arquivo.transferTo(pasta.resolve(nome));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível salvar a imagem");
        }
        return "/uploads/" + nome;
    }

    public Path getPasta() {
        return pasta;
    }
}
