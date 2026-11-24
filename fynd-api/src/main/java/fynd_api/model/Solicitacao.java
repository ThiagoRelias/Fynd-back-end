package fynd_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Produto produto;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String contato;

    @Column(nullable = false, length = 1000)
    private String descricao;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataSolicitacao;

    @PrePersist
    public void aoCriar() {
        this.dataSolicitacao = LocalDateTime.now();
    }
}
