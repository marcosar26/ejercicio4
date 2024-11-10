package es.marcosar.ejercicio4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDetalleDTO {
    private String nombreProducto;
    private Integer cantidad;
}
