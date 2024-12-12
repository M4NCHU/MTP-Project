package demo.controller;

import demo.models.postgres.Ocena;
import demo.service.OcenaService;
import demo.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/oceny")
@CrossOrigin(origins = "http://localhost:3000")
public class OcenaController {

    @Autowired
    private OcenaService ocenaService;

    @Autowired
    private LoggingService loggingService;

    @GetMapping
    public ResponseEntity<?> getAllOceny() {
        loggingService.log("INFO", "OcenaController", "Pobieranie wszystkich ocen", null);
        return new ResponseEntity<>(ocenaService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ocena> createOcena(@RequestBody Ocena ocena) {
        loggingService.log("INFO", "OcenaController", "Dodawanie nowej oceny", null);
        try {
            Ocena savedOcena = ocenaService.save(ocena);
            loggingService.log("INFO", "OcenaController", "Ocena dodana pomyślnie z ID " + savedOcena.getId(), null);
            return new ResponseEntity<>(savedOcena, HttpStatus.CREATED);
        } catch (Exception e) {
            loggingService.log("ERROR", "OcenaController", "Błąd podczas dodawania oceny", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOcenaById(@PathVariable Long id) {
        loggingService.log("INFO", "OcenaController", "Pobieranie szczegółów oceny o ID " + id, null);
        Ocena ocena = ocenaService.findById(id).orElse(null);
        if (ocena != null) {
            loggingService.log("INFO", "OcenaController", "Ocena o ID " + id + " znaleziona", null);
            return new ResponseEntity<>(ocena, HttpStatus.OK);
        } else {
            loggingService.log("WARN", "OcenaController", "Ocena o ID " + id + " nie znaleziona", null);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
