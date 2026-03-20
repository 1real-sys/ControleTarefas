package dev.teamwin.controletarefa.Domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserDomain usuario;

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
