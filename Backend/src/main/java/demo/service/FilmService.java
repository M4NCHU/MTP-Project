package demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demo.models.postgres.Film;
import demo.repository.postges.FilmRepository;


import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Film entities.
 */
@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    /**
     * Fetches all films from the database.
     *
     * @return a list of all films.
     */
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    /**
     * Finds a film by its ID.
     *
     * @param id the ID of the film.
     * @return an Optional containing the film, if found.
     */
    public Optional<Film> findById(Long id) {
        return filmRepository.findById(id);
    }

    /**
     * Saves or updates a film in the database.
     *
     * @param film the film entity to save.
     * @return the saved film.
     */
    public Film save(Film film) {
        return filmRepository.save(film);
    }

    /**
     * Deletes a film by its ID.
     *
     * @param id the ID of the film to delete.
     */
    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }

    /**
     * Deletes all films from the database.
     */
    public void deleteAll() {
        filmRepository.deleteAll();
    }
}
