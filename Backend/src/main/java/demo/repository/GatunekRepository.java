package demo.repository;

import demo.models.Gatunek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GatunekRepository extends JpaRepository<Gatunek, Long> {
    Optional<Gatunek> findByNazwa(String nazwa);
}
