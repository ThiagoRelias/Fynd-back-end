package fynd_api.config;

import fynd_api.service.ImagemService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UploadConfig implements WebMvcConfigurer {

    private final ImagemService imagemService;

    public UploadConfig(ImagemService imagemService) {
        this.imagemService = imagemService;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String local = imagemService.getPasta().toUri().toString();
        if (!local.endsWith("/")) {
            local = local + "/";
        }
        registry.addResourceHandler("/uploads/**").addResourceLocations(local);
    }
}
