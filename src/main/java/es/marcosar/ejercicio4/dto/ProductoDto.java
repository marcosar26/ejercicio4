package es.marcosar.ejercicio4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto {
    private String nombre;
    private Float precio;
    private Integer cantidadDisponible;
}
