package dev.teamwin.controletarefa.DTO;


import dev.teamwin.controletarefa.Domain.StatusTarefa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
    private StatusTarefa status;
    private void definirValoresIniciais() {
        if (this.dataCriacao == null) {
            this.dataCriacao = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = StatusTarefa.PENDENTE;
        }
    }
}
