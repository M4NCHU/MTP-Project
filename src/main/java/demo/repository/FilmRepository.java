package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import demo.models.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
