package pe.edu.cibertec.cl02MiguelAranguren.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.cl02MiguelAranguren.entity.Film;

public interface FilmRepository extends CrudRepository<Film, Integer> {
}
