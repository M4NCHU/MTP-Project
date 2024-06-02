package demo.controller;

import demo.models.Gatunek;
import demo.service.GatunekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gatunki")
public class GatunekController {

    @Autowired
    private GatunekService gatunekService;

    @GetMapping
    public ResponseEntity<?> getAllGatunki() {
        return new ResponseEntity<>(gatunekService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGatunekById(@PathVariable Long id) {
        Gatunek gatunek = gatunekService.findById(id).orElse(null);
        if (gatunek != null) {
            return new ResponseEntity<>(gatunek, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
