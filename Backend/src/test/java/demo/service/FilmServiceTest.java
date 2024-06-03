package demo.service;

import demo.models.Film;
import demo.repository.FilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FilmServiceTest {

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private FilmService filmService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        Long filmId = 1L;
        Film film = new Film();
        film.setId(filmId);
        film.setTytul("Test Film");

        when(filmRepository.findById(filmId)).thenReturn(Optional.of(film));

        Optional<Film> foundFilm = filmService.findById(filmId);

        assertEquals(true, foundFilm.isPresent());
        assertEquals("Test Film", foundFilm.get().getTytul());

        verify(filmRepository, times(1)).findById(filmId);
    }

    @Test
    public void testSave() {
        Film film = new Film();
        film.setTytul("New Film");

        when(filmRepository.save(film)).thenReturn(film);

        Film savedFilm = filmService.save(film);

        assertEquals("New Film", savedFilm.getTytul());

        verify(filmRepository, times(1)).save(film);
    }
}
