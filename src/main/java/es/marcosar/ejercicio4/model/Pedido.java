package es.marcosar.ejercicio4.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    private LocalDateTime fecha_pedido;

    public Pedido(Usuario usuario, LocalDateTime fecha_pedido) {
        this.usuario = usuario;
        this.fecha_pedido = fecha_pedido;
    }
}
