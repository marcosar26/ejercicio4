package es.marcosar.ejercicio4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearPedidoRequestDTO {
    private Long idUsuario;
    private Set<DetallesPedidoRequestDTO> detallesPedidos;
}
