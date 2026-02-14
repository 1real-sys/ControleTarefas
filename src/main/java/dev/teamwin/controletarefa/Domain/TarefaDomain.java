package dev.teamwin.controletarefa.Domain;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_tarefas")
public class TarefaDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", unique = true, nullable = false)
    private String titulo;
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusTarefa status;

    @PrePersist
    private void definirValoresIniciais() {
        if (this.dataCriacao == null) {
            this.dataCriacao = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = StatusTarefa.PENDENTE;
        }
    }

}
