package es.marcosar.ejercicio4.repository;

import es.marcosar.ejercicio4.model.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {
}