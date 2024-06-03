package demo.controller;

import demo.dto.FilmDTO;
import demo.models.Film;
import demo.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/filmy")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping
    public ResponseEntity<List<FilmDTO>> getAllFilms() {
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
        return new ResponseEntity<>(filmDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmDetails(@PathVariable Long id) {
        Film film = filmService.findById(id).orElse(null);
        return film != null ? new ResponseEntity<>(film, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/zapiszFilm")
    public ResponseEntity<Film> showFormForAdd() {
        return new ResponseEntity<>(new Film(), HttpStatus.OK);
    }

    @PostMapping("/zapiszFilm")
    public ResponseEntity<Film> saveFilm(@RequestBody Film film) {
        filmService.save(film);
        return new ResponseEntity<>(film, HttpStatus.CREATED);
    }

    @GetMapping("/edytujFilm/{id}")
    public ResponseEntity<Film> showFormForUpdate(@PathVariable Long id) {
        Film film = filmService.findById(id).orElse(null);
        return film != null ? new ResponseEntity<>(film, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/edytujFilm/{id}")
    public ResponseEntity<Film> updateFilm(@RequestBody Film film, @PathVariable Long id) {
        Film existingFilm = filmService.findById(id).orElse(null);
        if (existingFilm == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Uaktualnienie istniejących danych
        existingFilm.setTytul(film.getTytul());
        existingFilm.setOpis(film.getOpis());
        existingFilm.setRokWydania(film.getRokWydania());
        existingFilm.setDlugosc(film.getDlugosc());
        // Zapis zmodyfikowanego filmu
        filmService.save(existingFilm);
        return new ResponseEntity<>(existingFilm, HttpStatus.OK);
    }


    @DeleteMapping("/usunFilm/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        filmService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
