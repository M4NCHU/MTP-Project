package demo.service;

import demo.models.Ocena;
import demo.repository.OcenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing {@link Ocena} entities.
 */
@Service
public class OcenaService {

    @Autowired
    private OcenaRepository ocenaRepository;

    /**
     * Retrieves all ratings from the database.
     *
     * @return a list of all ratings.
     */
    public List<Ocena> findAll() {
        return ocenaRepository.findAll();
    }

    /**
     * Finds a rating by its ID.
     *
     * @param id the ID of the rating.
     * @return an {@link Optional} containing the rating if found.
     */
    public Optional<Ocena> findById(Long id) {
        return ocenaRepository.findById(id);
    }

    /**
     * Saves or updates a rating in the database.
     *
     * @param ocena the rating entity to save.
     * @return the saved rating.
     */
    public Ocena save(Ocena ocena) {
        return ocenaRepository.save(ocena);
    }

    /**
     * Deletes a rating by its ID.
     *
     * @param id the ID of the rating to delete.
     */
    public void deleteById(Long id) {
        ocenaRepository.deleteById(id);
    }

    /**
     * Deletes all ratings from the database.
     */
    public void deleteAll() {
        ocenaRepository.deleteAll();
    }
}
