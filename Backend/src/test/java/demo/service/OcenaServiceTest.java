package demo.service;

import demo.models.postgres.Ocena;
import demo.repository.postges.OcenaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OcenaServiceTest {

    @Mock
    private OcenaRepository ocenaRepository;

    @InjectMocks
    private OcenaService ocenaService;

    public OcenaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Ocena ocena = new Ocena();
        when(ocenaRepository.findAll()).thenReturn(List.of(ocena));

        List<Ocena> oceny = ocenaService.findAll();

        assertEquals(1, oceny.size());
        verify(ocenaRepository, times(1)).findAll();
    }

    @Test
    void testDeleteById() {
        ocenaService.deleteById(1L);

        verify(ocenaRepository, times(1)).deleteById(1L);
    }
}
