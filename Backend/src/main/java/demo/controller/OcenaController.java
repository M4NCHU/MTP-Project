package demo.controller;

import demo.models.Ocena;
import demo.service.OcenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/oceny")
public class OcenaController {

    private static final Logger logger = LoggerFactory.getLogger(OcenaController.class);

    @Autowired
    private OcenaService ocenaService;

    @GetMapping
    public ResponseEntity<?> getAllOceny() {
        logger.info("Otrzymano żądanie GET /api/oceny - Pobieranie wszystkich ocen");
        return new ResponseEntity<>(ocenaService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ocena> createOcena(@RequestBody Ocena ocena) {
        logger.info("Otrzymano żądanie POST /api/oceny - Dodawanie nowej oceny");
        try {
            Ocena savedOcena = ocenaService.save(ocena);
            logger.info("Ocena dodana pomyślnie z ID {}", savedOcena.getId());
            return new ResponseEntity<>(savedOcena, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Błąd podczas dodawania oceny: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOcenaById(@PathVariable Long id) {
        logger.info("Otrzymano żądanie GET /api/oceny/{} - Pobieranie szczegółów oceny", id);
        Ocena ocena = ocenaService.findById(id).orElse(null);
        if (ocena != null) {
            logger.info("Ocena o ID {} znaleziona", id);
            return new ResponseEntity<>(ocena, HttpStatus.OK);
        } else {
            logger.warn("Ocena o ID {} nie znaleziona", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
