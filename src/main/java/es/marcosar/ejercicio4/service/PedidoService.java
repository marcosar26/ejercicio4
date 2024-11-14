package es.marcosar.ejercicio4.service;

import es.marcosar.ejercicio4.dto.DetallesPedidoRequestDTO;
import es.marcosar.ejercicio4.dto.PedidoRequestDTO;
import es.marcosar.ejercicio4.model.DetallesPedido;
import es.marcosar.ejercicio4.model.Pedido;
import es.marcosar.ejercicio4.model.Producto;
import es.marcosar.ejercicio4.model.Usuario;
import es.marcosar.ejercicio4.repository.DetallesPedidoRepository;
import es.marcosar.ejercicio4.repository.PedidoRepository;
import es.marcosar.ejercicio4.repository.ProductoRepository;
import es.marcosar.ejercicio4.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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
    @Autowired
    private DetallesPedidoService detallesPedidoService;

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

    public Set<PedidoRequestDTO> getPedidosConDetalles() {
        Set<PedidoRequestDTO> pedidosConDetalles = new HashSet<>();

        List<Pedido> pedidos = findAll();
        for (Pedido pedido : pedidos) {
            PedidoRequestDTO pedidoDTO = detallesPedidoService.obtenerDetalles(pedido.getId());
            pedidosConDetalles.add(pedidoDTO);
        }

        return pedidosConDetalles;
    }

    @Transactional
    public void eliminarPedido(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        if (pedido.isEmpty()) {
            throw new RuntimeException("El pedido no existe");
        }

        if (detallesPedidoRepository.existsByPedidoId(id)) {
            detallesPedidoRepository.deleteByPedidoId(id);
        }

        pedidoRepository.deleteById(id);
    }
}
