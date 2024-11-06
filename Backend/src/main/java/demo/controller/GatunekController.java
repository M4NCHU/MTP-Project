package demo.controller;

import demo.models.Gatunek;
import demo.service.GatunekService;
import demo.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gatunki")
public class GatunekController {

    @Autowired
    private GatunekService gatunekService;

    @Autowired
    private LoggingService loggingService;

    @GetMapping
    public ResponseEntity<List<Gatunek>> getAllGatunki() {
        loggingService.log("INFO", "GatunekController", "Pobieranie wszystkich gatunków", null);
        List<Gatunek> gatunki = gatunekService.findAll();
        loggingService.log("INFO", "GatunekController", "Zwrócono " + gatunki.size() + " gatunków", null);
        return new ResponseEntity<>(gatunki, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gatunek> getGatunekById(@PathVariable Long id) {
        loggingService.log("INFO", "GatunekController", "Pobieranie szczegółów gatunku o ID " + id, null);
        return gatunekService.findById(id)
                .map(gatunek -> {
                    loggingService.log("INFO", "GatunekController", "Znaleziono gatunek o ID " + id, null);
                    return new ResponseEntity<>(gatunek, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    loggingService.log("WARN", "GatunekController", "Nie znaleziono gatunku o ID " + id, null);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }

    @PostMapping
    public ResponseEntity<Gatunek> createGatunek(@RequestBody Gatunek gatunek) {
        loggingService.log("INFO", "GatunekController", "Tworzenie nowego gatunku", null);
        Gatunek savedGatunek = gatunekService.save(gatunek);
        loggingService.log("INFO", "GatunekController", "Utworzono nowy gatunek o ID " + savedGatunek.getId(), null);
        return new ResponseEntity<>(savedGatunek, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gatunek> updateGatunek(@PathVariable Long id, @RequestBody Gatunek gatunek) {
        loggingService.log("INFO", "GatunekController", "Aktualizacja gatunku o ID " + id, null);
        return gatunekService.findById(id)
                .map(existingGatunek -> {
                    existingGatunek.setNazwa(gatunek.getNazwa());
                    existingGatunek.setOpis(gatunek.getOpis());
                    gatunekService.save(existingGatunek);
                    loggingService.log("INFO", "GatunekController", "Zaktualizowano gatunek o ID " + id, null);
                    return new ResponseEntity<>(existingGatunek, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    loggingService.log("WARN", "GatunekController", "Nie znaleziono gatunku o ID " + id, null);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGatunek(@PathVariable Long id) {
        loggingService.log("INFO", "GatunekController", "Usuwanie gatunku o ID " + id, null);
        return gatunekService.findById(id)
                .map(gatunek -> {
                    gatunekService.delete(gatunek);
                    loggingService.log("INFO", "GatunekController", "Usunięto gatunek o ID " + id, null);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> {
                    loggingService.log("WARN", "GatunekController", "Nie znaleziono gatunku o ID " + id, null);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }
}
