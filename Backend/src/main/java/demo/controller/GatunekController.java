package demo.controller;

import demo.models.Gatunek;
import demo.service.GatunekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/gatunki")
public class GatunekController {

    private static final Logger logger = LoggerFactory.getLogger(GatunekController.class);

    @Autowired
    private GatunekService gatunekService;

    @GetMapping
    public ResponseEntity<List<Gatunek>> getAllGatunki() {
        logger.info("Otrzymano żądanie GET /api/gatunki - Pobieranie wszystkich gatunków");
        List<Gatunek> gatunki = gatunekService.findAll();
        logger.info("Zwrócono {} gatunków", gatunki.size());
        return new ResponseEntity<>(gatunki, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gatunek> getGatunekById(@PathVariable Long id) {
        logger.info("Otrzymano żądanie GET /api/gatunki/{} - Pobieranie szczegółów gatunku", id);
        return gatunekService.findById(id)
                .map(gatunek -> {
                    logger.info("Znaleziono gatunek o ID: {}", id);
                    return new ResponseEntity<>(gatunek, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    logger.warn("Nie znaleziono gatunku o ID: {}", id);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }

    @PostMapping
    public ResponseEntity<Gatunek> createGatunek(@RequestBody Gatunek gatunek) {
        logger.info("Otrzymano żądanie POST /api/gatunki - Tworzenie nowego gatunku");
        Gatunek savedGatunek = gatunekService.save(gatunek);
        logger.info("Utworzono nowy gatunek o ID: {}", savedGatunek.getId());
        return new ResponseEntity<>(savedGatunek, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gatunek> updateGatunek(@PathVariable Long id, @RequestBody Gatunek gatunek) {
        logger.info("Otrzymano żądanie PUT /api/gatunki/{} - Aktualizacja gatunku", id);
        return gatunekService.findById(id)
                .map(existingGatunek -> {
                    existingGatunek.setNazwa(gatunek.getNazwa());
                    existingGatunek.setOpis(gatunek.getOpis());
                    gatunekService.save(existingGatunek);
                    logger.info("Zaktualizowano gatunek o ID: {}", id);
                    return new ResponseEntity<>(existingGatunek, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    logger.warn("Nie znaleziono gatunku o ID: {}", id);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGatunek(@PathVariable Long id) {
        logger.info("Otrzymano żądanie DELETE /api/gatunki/{} - Usunięcie gatunku", id);
        return gatunekService.findById(id)
                .map(gatunek -> {
                    gatunekService.delete(gatunek);
                    logger.info("Usunięto gatunek o ID: {}", id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> {
                    logger.warn("Nie znaleziono gatunku o ID: {}", id);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }
}
