package es.marcosar.ejercicio4.repository;

import es.marcosar.ejercicio4.model.DetallesPedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallesPedidoRepository extends CrudRepository<DetallesPedido, Long> {
    List<DetallesPedido> findByPedidoId(Long pedidoId);

    boolean existsByPedidoId(Long pedidoId);

    void deleteByPedidoId(Long pedidoId);
}
