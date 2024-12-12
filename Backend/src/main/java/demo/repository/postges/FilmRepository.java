package demo.repository.postges;

import org.springframework.data.jpa.repository.JpaRepository;
import demo.models.postgres.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
