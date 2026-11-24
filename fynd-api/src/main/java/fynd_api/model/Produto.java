package fynd_api.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fynd_api.config.UploadConfig;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer quantidade;

    @JsonSerialize(using = UploadConfig.class)
    @Column(nullable = false)
    private String imagemUrl;

    @Column(nullable = false)
    private String encontradoEm;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(nullable = false, length = 20)
    private StatusObjeto status;

    private LocalDateTime dataDevolucao;

    @PrePersist
    public void aoCriar() {
        this.dataCadastro = LocalDateTime.now();
    }
}