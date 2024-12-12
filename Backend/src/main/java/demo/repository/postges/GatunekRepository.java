package demo.repository.postges;

import demo.models.postgres.Gatunek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GatunekRepository extends JpaRepository<Gatunek, Long> {
    Optional<Gatunek> findByNazwa(String nazwa);
}
