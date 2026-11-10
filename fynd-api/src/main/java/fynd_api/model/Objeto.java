package fynd_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Objeto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 1000)
    private String descricao;

    private String ImagemUrl;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Column(nullable = false)
    private LocalDate dataEncontrado;

    @Column(nullable = false)
    private String LocalEncontrado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusObjeto status;

    @Column(updatable = false)
    private LocalDateTime datacadastro;

    private LocalDate dataDevolucao;

    @PrePersist
    public void prePersist(){
        this.datacadastro = LocalDateTime.now();
        if(this.dataDevolucao == null){
        this.status = StatusObjeto.AGUARDADNDO_DONO;
        }
    }
}
