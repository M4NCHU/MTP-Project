package demo.controller;

import demo.dto.FilmDTO;
import demo.models.Film;
import demo.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/filmy")
public class FilmController {

    private static final Logger logger = LoggerFactory.getLogger(FilmController.class);

    @Autowired
    private FilmService filmService;

    @GetMapping
    public ResponseEntity<List<FilmDTO>> getAllFilms() {
        logger.info("Otrzymano żądanie GET /api/filmy - Pobieranie wszystkich filmów");
        List<Film> films = filmService.findAll();
        List<FilmDTO> filmDTOs = films.stream().map(film -> new FilmDTO(
                film.getId(),
                film.getTytul(),
                film.getOpis(),
                film.getRokWydania(),
                film.getDlugosc(),
                film.getPlakatURL(),
                film.getNazwaGatunku()
        )).collect(Collectors.toList());
        logger.info("Znaleziono {} filmów", filmDTOs.size());
        return new ResponseEntity<>(filmDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmDetails(@PathVariable Long id) {
        logger.info("Otrzymano żądanie GET /api/filmy/{} - Pobieranie szczegółów filmu", id);
        Film film = filmService.findById(id).orElse(null);
        if (film != null) {
            logger.info("Znaleziono film o ID {}", id);
            return new ResponseEntity<>(film, HttpStatus.OK);
        } else {
            logger.warn("Nie znaleziono filmu o ID {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/zapiszFilm")
    public ResponseEntity<Film> showFormForAdd() {
        logger.info("Otrzymano żądanie GET /api/filmy/zapiszFilm - Wyświetlanie formularza dodawania filmu");
        return new ResponseEntity<>(new Film(), HttpStatus.OK);
    }

    @PostMapping("/zapiszFilm")
    public ResponseEntity<Film> saveFilm(@RequestBody Film film) {
        logger.info("Otrzymano żądanie POST /api/filmy/zapiszFilm - Zapisywanie nowego filmu: {}", film.getTytul());
        filmService.save(film);
        logger.info("Zapisano nowy film: {}", film.getTytul());
        return new ResponseEntity<>(film, HttpStatus.CREATED);
    }

    @GetMapping("/edytujFilm/{id}")
    public ResponseEntity<Film> showFormForUpdate(@PathVariable Long id) {
        logger.info("Otrzymano żądanie GET /api/filmy/edytujFilm/{} - Pobieranie filmu do edycji", id);
        Film film = filmService.findById(id).orElse(null);
        if (film != null) {
            logger.info("Znaleziono film o ID {} do edycji", id);
            return new ResponseEntity<>(film, HttpStatus.OK);
        } else {
            logger.warn("Nie znaleziono filmu o ID {} do edycji", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edytujFilm/{id}")
    public ResponseEntity<Film> updateFilm(@RequestBody Film film, @PathVariable Long id) {
        logger.info("Otrzymano żądanie PUT /api/filmy/edytujFilm/{} - Aktualizacja filmu", id);
        Film existingFilm = filmService.findById(id).orElse(null);
        if (existingFilm == null) {
            logger.warn("Nie znaleziono filmu o ID {} do aktualizacji", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Uaktualnienie istniejących danych
        existingFilm.setTytul(film.getTytul());
        existingFilm.setOpis(film.getOpis());
        existingFilm.setRokWydania(film.getRokWydania());
        existingFilm.setDlugosc(film.getDlugosc());
        // Zapis zmodyfikowanego filmu
        filmService.save(existingFilm);
        logger.info("Zaktualizowano film o ID {}", id);
        return new ResponseEntity<>(existingFilm, HttpStatus.OK);
    }

    @DeleteMapping("/usunFilm/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        logger.info("Otrzymano żądanie DELETE /api/filmy/usunFilm/{} - Usuwanie filmu", id);
        filmService.deleteById(id);
        logger.info("Usunięto film o ID {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
