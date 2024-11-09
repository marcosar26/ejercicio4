package es.marcosar.ejercicio4.controller;

import es.marcosar.ejercicio4.dto.ProductoDto;
import es.marcosar.ejercicio4.model.Producto;
import es.marcosar.ejercicio4.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDto>> findAll() {
        List<ProductoDto> usuarios = productoService.findAll();
        return usuarios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(usuarios);
    }

    @PutMapping("/add")
    public ResponseEntity<ProductoDto> add(@RequestBody Producto producto) {
        ProductoDto prod = productoService.add(producto);
        return prod == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(prod);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductoDto> update(@RequestBody Producto producto) {
        ProductoDto prod = productoService.update(producto);
        return prod == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(prod);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ProductoDto> delete(@RequestParam Long id) {
        ProductoDto prod = productoService.delete(id);
        return prod == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(prod);
    }
}
