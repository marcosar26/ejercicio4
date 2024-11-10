package es.marcosar.ejercicio4.controller;

import es.marcosar.ejercicio4.dto.CrearPedidoRequestDTO;
import es.marcosar.ejercicio4.dto.PedidoRequestDTO;
import es.marcosar.ejercicio4.model.Pedido;
import es.marcosar.ejercicio4.service.DetallesPedidoService;
import es.marcosar.ejercicio4.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private DetallesPedidoService detallesPedidoService;

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

    @GetMapping("/{idPedido}/detalles")
    public ResponseEntity<PedidoRequestDTO> obtenerDetallesPedido(@PathVariable Long idPedido) {
        PedidoRequestDTO prd = detallesPedidoService.obtenerDetalles(idPedido);
        return prd == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(prd);
    }

    @GetMapping("/detalles")
    public ResponseEntity<Set<PedidoRequestDTO>> obtenerDetallesDeTodosLosPedidos() {
        Set<PedidoRequestDTO> set = pedidoService.getPedidosConDetalles();
        return set.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(set);
    }
}
