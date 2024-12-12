package demo.controller;

import demo.dto.FilmDTO;
import demo.models.postgres.Film;
import demo.service.FilmService;
import demo.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/filmy")
@CrossOrigin(origins = "http://localhost:3000")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private LoggingService loggingService;

    @GetMapping
    public ResponseEntity<List<FilmDTO>> getAllFilms() {
        loggingService.log("INFO", "FilmController", "Pobieranie wszystkich filmów", null);
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
        loggingService.log("INFO", "FilmController", "Znaleziono " + filmDTOs.size() + " filmów", null);
        return new ResponseEntity<>(filmDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmDetails(@PathVariable Long id) {
        loggingService.log("INFO", "FilmController", "Pobieranie szczegółów filmu o ID " + id, null);
        Film film = filmService.findById(id).orElse(null);
        if (film != null) {
            loggingService.log("INFO", "FilmController", "Znaleziono film o ID " + id, null);
            return new ResponseEntity<>(film, HttpStatus.OK);
        } else {
            loggingService.log("WARN", "FilmController", "Nie znaleziono filmu o ID " + id, null);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/zapiszFilm")
    public ResponseEntity<Film> showFormForAdd() {
        loggingService.log("INFO", "FilmController", "Wyświetlanie formularza dodawania filmu", null);
        return new ResponseEntity<>(new Film(), HttpStatus.OK);
    }

    @PostMapping("/zapiszFilm")
    public ResponseEntity<Film> saveFilm(@RequestBody Film film) {
        loggingService.log("INFO", "FilmController", "Zapisywanie nowego filmu: " + film.getTytul(), null);
        filmService.save(film);
        loggingService.log("INFO", "FilmController", "Zapisano nowy film: " + film.getTytul(), null);
        return new ResponseEntity<>(film, HttpStatus.CREATED);
    }

    @GetMapping("/edytujFilm/{id}")
    public ResponseEntity<Film> showFormForUpdate(@PathVariable Long id) {
        loggingService.log("INFO", "FilmController", "Pobieranie filmu o ID " + id + " do edycji", null);
        Film film = filmService.findById(id).orElse(null);
        if (film != null) {
            loggingService.log("INFO", "FilmController", "Znaleziono film o ID " + id + " do edycji", null);
            return new ResponseEntity<>(film, HttpStatus.OK);
        } else {
            loggingService.log("WARN", "FilmController", "Nie znaleziono filmu o ID " + id + " do edycji", null);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edytujFilm/{id}")
    public ResponseEntity<Film> updateFilm(@RequestBody Film film, @PathVariable Long id) {
        loggingService.log("INFO", "FilmController", "Aktualizacja filmu o ID " + id, null);
        Film existingFilm = filmService.findById(id).orElse(null);
        if (existingFilm == null) {
            loggingService.log("WARN", "FilmController", "Nie znaleziono filmu o ID " + id + " do aktualizacji", null);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingFilm.setTytul(film.getTytul());
        existingFilm.setOpis(film.getOpis());
        existingFilm.setRokWydania(film.getRokWydania());
        existingFilm.setDlugosc(film.getDlugosc());
        filmService.save(existingFilm);
        loggingService.log("INFO", "FilmController", "Zaktualizowano film o ID " + id, null);
        return new ResponseEntity<>(existingFilm, HttpStatus.OK);
    }

    @DeleteMapping("/usunFilm/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        loggingService.log("INFO", "FilmController", "Usuwanie filmu o ID " + id, null);
        filmService.deleteById(id);
        loggingService.log("INFO", "FilmController", "Usunięto film o ID " + id, null);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
