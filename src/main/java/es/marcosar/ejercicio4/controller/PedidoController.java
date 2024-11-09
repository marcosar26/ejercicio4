package es.marcosar.ejercicio4.controller;

import es.marcosar.ejercicio4.dto.CrearPedidoRequestDTO;
import es.marcosar.ejercicio4.model.Pedido;
import es.marcosar.ejercicio4.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> pedidos = pedidoService.findAll();
        return pedidos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pedidos);
    }

    @PostMapping("/crear")
    public ResponseEntity<Pedido> crearPedido(@Valid @RequestBody CrearPedidoRequestDTO pedido) {
        Pedido p = pedidoService.crearPedido(pedido.getDetallesPedidos(), pedido.getIdUsuario());
        return p == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(p);
    }
}
