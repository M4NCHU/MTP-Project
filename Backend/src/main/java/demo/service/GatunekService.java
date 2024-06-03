package demo.service;

import demo.models.Gatunek;
import demo.repository.GatunekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GatunekService {

    @Autowired
    private GatunekRepository gatunekRepository;

    public List<Gatunek> findAll() {
        return gatunekRepository.findAll();
    }

    public Optional<Gatunek> findById(Long id) {
        return gatunekRepository.findById(id);
    }

    public Gatunek save(Gatunek gatunek) {
        return gatunekRepository.save(gatunek);
    }

    public void delete(Gatunek gatunek) {
        gatunekRepository.delete(gatunek);
    }

    public void deleteById(Long id) {
        gatunekRepository.deleteById(id);
    }

    public void deleteAll() {
        gatunekRepository.deleteAll();
    }
}
