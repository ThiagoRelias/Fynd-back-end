package fynd_api.controller;

import fynd_api.model.Imagem;
import fynd_api.service.ImagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/api/imagens")
@Tag(name = "Imagens", description = "Envio e exibição das imagens dos objetos, guardadas no banco")
public class ImagemController {

    private final ImagemService service;

    public ImagemController(ImagemService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Envia uma imagem e devolve o endereço para usar no imagemUrl")
    public Map<String, String> enviar(@RequestParam("arquivo") MultipartFile arquivo) {
        String caminho = service.salvar(arquivo);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path(caminho).toUriString();
        return Map.of("url", url);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibe uma imagem salva")
    public ResponseEntity<byte[]> exibir(@PathVariable Long id) {
        Imagem imagem = service.buscar(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imagem.getTipo()))
                .cacheControl(CacheControl.maxAge(Duration.ofDays(7)))
                .body(imagem.getDados());
    }
}
