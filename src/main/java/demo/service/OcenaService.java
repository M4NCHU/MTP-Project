package demo.service;

import demo.models.Ocena;
import demo.repository.OcenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OcenaService {

    @Autowired
    private OcenaRepository ocenaRepository;

    public List<Ocena> findAll() {
        return ocenaRepository.findAll();
    }

    public Optional<Ocena> findById(Long id) {
        return ocenaRepository.findById(id);
    }

    public Ocena save(Ocena ocena) {
        return ocenaRepository.save(ocena);
    }

    public void deleteById(Long id) {
        ocenaRepository.deleteById(id);
    }

    public void deleteAll() {
        ocenaRepository.deleteAll();
    }
}
