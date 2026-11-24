package fynd_api.dto;

public record StatsResponseDTO(
        long total,
        long aguardandoDono,
        long devolvidos,
        double tempoMedioDias
) {}
