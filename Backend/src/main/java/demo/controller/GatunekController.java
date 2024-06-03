package demo.controller;

import demo.models.Gatunek;
import demo.service.GatunekService;
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

    @GetMapping
    public ResponseEntity<List<Gatunek>> getAllGatunki() {
        List<Gatunek> gatunki = gatunekService.findAll();
        return new ResponseEntity<>(gatunki, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gatunek> getGatunekById(@PathVariable Long id) {
        return gatunekService.findById(id)
                .map(gatunek -> new ResponseEntity<>(gatunek, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Gatunek> createGatunek(@RequestBody Gatunek gatunek) {
        Gatunek savedGatunek = gatunekService.save(gatunek);
        return new ResponseEntity<>(savedGatunek, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gatunek> updateGatunek(@PathVariable Long id, @RequestBody Gatunek gatunek) {
        return gatunekService.findById(id)
                .map(existingGatunek -> {
                    existingGatunek.setNazwa(gatunek.getNazwa());
                    existingGatunek.setOpis(gatunek.getOpis());
                    gatunekService.save(existingGatunek);
                    return new ResponseEntity<>(existingGatunek, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGatunek(@PathVariable Long id) {
        return gatunekService.findById(id)
                .map(gatunek -> {
                    gatunekService.delete(gatunek);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
