package es.marcosar.ejercicio4.repository;

import es.marcosar.ejercicio4.model.DetallesPedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallesPedidoRepository extends CrudRepository<DetallesPedido, Long> {
}
