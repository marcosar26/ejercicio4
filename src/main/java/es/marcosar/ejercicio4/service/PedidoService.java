package es.marcosar.ejercicio4.service;

import es.marcosar.ejercicio4.dto.DetallesPedidoRequestDTO;
import es.marcosar.ejercicio4.model.DetallesPedido;
import es.marcosar.ejercicio4.model.Pedido;
import es.marcosar.ejercicio4.model.Producto;
import es.marcosar.ejercicio4.model.Usuario;
import es.marcosar.ejercicio4.repository.DetallesPedidoRepository;
import es.marcosar.ejercicio4.repository.PedidoRepository;
import es.marcosar.ejercicio4.repository.ProductoRepository;
import es.marcosar.ejercicio4.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private DetallesPedidoRepository detallesPedidoRepository;

    public List<Pedido> findAll() {
        List<Pedido> pedidos = new ArrayList<>();
        pedidoRepository.findAll().forEach(pedidos::add);
        return pedidos;
    }

    public Pedido crearPedido(Set<DetallesPedidoRequestDTO> detallesPedidosDTO, Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow();

        Pedido pedido = new Pedido(usuario, LocalDateTime.now());

        pedido = pedidoRepository.save(pedido);

        for (DetallesPedidoRequestDTO detalleDTO : detallesPedidosDTO) {

            Producto producto = productoRepository.findById(detalleDTO.getIdProducto()).orElseThrow();

            DetallesPedido detalles = new DetallesPedido(pedido, producto, detalleDTO.getCantidad());

            detallesPedidoRepository.save(detalles);
        }

        return pedido;
    }
}
