package fynd_api.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

public class UploadConfig extends JsonSerializer<String> {

    @Override
    public void serialize(String valor, JsonGenerator gerador, SerializerProvider provider) throws IOException {
        if (valor.startsWith("http") || RequestContextHolder.getRequestAttributes() == null) {
            gerador.writeString(valor);
            return;
        }
        gerador.writeString(ServletUriComponentsBuilder.fromCurrentContextPath().path(valor).toUriString());
    }
}
