package demo.service;

import demo.models.postgres.Film;
import demo.repository.postges.FilmRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FilmServiceTest {

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private FilmService filmService;

    public FilmServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Film film = new Film();
        when(filmRepository.findAll()).thenReturn(List.of(film));

        List<Film> films = filmService.findAll();

        assertEquals(1, films.size());
        verify(filmRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Film film = new Film();
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));

        Optional<Film> result = filmService.findById(1L);

        assertEquals(film, result.orElse(null));
        verify(filmRepository, times(1)).findById(1L);
    }
}
