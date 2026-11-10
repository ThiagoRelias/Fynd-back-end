package fynd_api.model;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String imagemUrl;

    @Column(nullable = false)
    private String encontradoEm;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusObjeto status;

    @PrePersist
    public void aoCriar() {
        this.dataCadastro = LocalDateTime.now();
    }
}