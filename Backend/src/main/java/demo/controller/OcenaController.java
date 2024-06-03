package demo.controller;

import demo.models.Ocena;
import demo.service.OcenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/oceny")
public class OcenaController {

    @Autowired
    private OcenaService ocenaService;

    @GetMapping
    public ResponseEntity<?> getAllOceny() {
        return new ResponseEntity<>(ocenaService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ocena> createOcena(@RequestBody Ocena ocena) {
        try {
            Ocena savedOcena = ocenaService.save(ocena);
            return new ResponseEntity<>(savedOcena, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOcenaById(@PathVariable Long id) {
        Ocena ocena = ocenaService.findById(id).orElse(null);
        if (ocena != null) {
            return new ResponseEntity<>(ocena, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
