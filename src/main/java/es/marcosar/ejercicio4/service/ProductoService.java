package es.marcosar.ejercicio4.service;

import es.marcosar.ejercicio4.dto.ProductoDto;
import es.marcosar.ejercicio4.model.Producto;
import es.marcosar.ejercicio4.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    private ProductoDto mapToDto(Producto producto) {
        return new ProductoDto(producto.getNombre(), producto.getPrecio(), producto.getCantidad_disponible());
    }

    public List<ProductoDto> findAll() {
        List<ProductoDto> productos = new ArrayList<>();
        productoRepository.findAll().forEach(p -> productos.add(mapToDto(p)));
        return productos;
    }

    public ProductoDto add(Producto producto) {
        return mapToDto(productoRepository.save(producto));
    }

    public ProductoDto update(Producto producto) {
        Producto prod = productoRepository.findById(producto.getId()).orElseThrow();
        prod.setNombre(producto.getNombre());
        prod.setDescripcion(producto.getDescripcion());
        prod.setPrecio(producto.getPrecio());
        prod.setCantidad_disponible(producto.getCantidad_disponible());
        return mapToDto(productoRepository.save(prod));
    }

    public ProductoDto delete(Long id) {
        Producto prod = productoRepository.findById(id).orElseThrow();
        productoRepository.delete(prod);
        return mapToDto(prod);
    }
}
