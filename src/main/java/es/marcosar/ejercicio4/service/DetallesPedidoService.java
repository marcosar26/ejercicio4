package es.marcosar.ejercicio4.service;

import es.marcosar.ejercicio4.dto.PedidoRequestDTO;
import es.marcosar.ejercicio4.dto.ProductoDetalleDTO;
import es.marcosar.ejercicio4.model.DetallesPedido;
import es.marcosar.ejercicio4.model.Pedido;
import es.marcosar.ejercicio4.repository.DetallesPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DetallesPedidoService {
    @Autowired
    private DetallesPedidoRepository detallesPedidoRepository;

    @Transactional(readOnly = true)
    public PedidoRequestDTO obtenerDetalles(Long idPedido) {
        List<DetallesPedido> detalles = detallesPedidoRepository.findByPedidoId(idPedido);

        if (detalles.isEmpty()) {
            return null;
        }

        Pedido pedido = detalles.getFirst().getPedido();

        List<ProductoDetalleDTO> productosComprados = detalles.stream()
                .map(detalle -> new ProductoDetalleDTO(detalle.getProducto().getNombre(), detalle.getCantidad()))
                .toList();

        return new PedidoRequestDTO(
                pedido.getId(),
                pedido.getUsuario().getNombre(),
                productosComprados
        );
    }
}

