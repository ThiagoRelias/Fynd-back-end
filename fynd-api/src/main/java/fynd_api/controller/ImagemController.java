package fynd_api.controller;

import fynd_api.service.ImagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/imagens")
@Tag(name = "Imagens", description = "Envio das imagens usadas no cadastro dos objetos")
public class ImagemController {

    private final ImagemService service;

    public ImagemController(ImagemService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Envia uma imagem e devolve o endereço para usar no imagemUrl")
    public Map<String, String> enviar(@RequestParam("arquivo") MultipartFile arquivo) {
        return Map.of("url", service.salvar(arquivo));
    }
}
