package demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demo.models.Film;
import demo.repository.FilmRepository;


import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;


    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public Optional<Film> findById(Long id) {
        return filmRepository.findById(id);
    }

    public Film save(Film film) {
        return filmRepository.save(film);
    }

    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }
    public void deleteAll() {
        filmRepository.deleteAll();
    }
}
