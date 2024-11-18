package demo.service;

import demo.models.Gatunek;
import demo.repository.GatunekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing {@link Gatunek} entities.
 */
@Service
public class GatunekService {

    @Autowired
    private GatunekRepository gatunekRepository;

    /**
     * Retrieves all genres from the database.
     *
     * @return a list of all genres.
     */
    public List<Gatunek> findAll() {
        return gatunekRepository.findAll();
    }

    /**
     * Finds a genre by its ID.
     *
     * @param id the ID of the genre.
     * @return an {@link Optional} containing the genre if found.
     */
    public Optional<Gatunek> findById(Long id) {
        return gatunekRepository.findById(id);
    }

    /**
     * Saves or updates a genre in the database.
     *
     * @param gatunek the genre entity to save.
     * @return the saved genre.
     */
    public Gatunek save(Gatunek gatunek) {
        return gatunekRepository.save(gatunek);
    }

    /**
     * Deletes a specified genre from the database.
     *
     * @param gatunek the genre entity to delete.
     */
    public void delete(Gatunek gatunek) {
        gatunekRepository.delete(gatunek);
    }

    /**
     * Deletes a genre by its ID.
     *
     * @param id the ID of the genre to delete.
     */
    public void deleteById(Long id) {
        gatunekRepository.deleteById(id);
    }

    /**
     * Deletes all genres from the database.
     */
    public void deleteAll() {
        gatunekRepository.deleteAll();
    }
}
