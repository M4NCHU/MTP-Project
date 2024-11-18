package demo.service;

import demo.models.Gatunek;
import demo.repository.GatunekRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GatunekServiceTest {

    @Mock
    private GatunekRepository gatunekRepository;

    @InjectMocks
    private GatunekService gatunekService;

    public GatunekServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Gatunek gatunek = new Gatunek();
        when(gatunekRepository.save(gatunek)).thenReturn(gatunek);

        Gatunek saved = gatunekService.save(gatunek);

        assertEquals(gatunek, saved);
        verify(gatunekRepository, times(1)).save(gatunek);
    }

    @Test
    void testFindById() {
        Gatunek gatunek = new Gatunek();
        when(gatunekRepository.findById(1L)).thenReturn(Optional.of(gatunek));

        Optional<Gatunek> result = gatunekService.findById(1L);

        assertEquals(gatunek, result.orElse(null));
        verify(gatunekRepository, times(1)).findById(1L);
    }
}
