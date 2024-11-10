package es.marcosar.ejercicio4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequestDTO {
    private Long idPedido;
    private String nombreUsuario;
    private List<ProductoDetalleDTO> productos;
}