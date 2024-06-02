package demo.repository;

import demo.models.Ocena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcenaRepository extends JpaRepository<Ocena, Long> {
    List<Ocena> findByFilmId(Long filmId);

    @Query("SELECT AVG(o.wartosc) FROM Ocena o WHERE o.film.id = :filmId")
    Double findAverageOcenaByFilmId(Long filmId);
}
