package com.centrodedia.controller;

import com.centrodedia.model.CentreResponsible;
import com.centrodedia.service.CentreResponsibleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responsibles")
public class CentreResponsibleController {

    private final CentreResponsibleService centreResponsibleService;

    public CentreResponsibleController(CentreResponsibleService centreResponsibleService) {
        this.centreResponsibleService = centreResponsibleService;
    }

    @GetMapping
    public List<CentreResponsible> getCentreResponsibles() {
        return centreResponsibleService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentreResponsible> getCentreResponsibleById(@PathVariable Long id) {
        return centreResponsibleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/idnumber/{idNumber}")
    public ResponseEntity<CentreResponsible> getCentreResponsibleByIdNumber(@PathVariable String idNumber) {
        return centreResponsibleService.findByIdNumber(idNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name")
    public List<CentreResponsible> getCentreResponsiblesByName(@RequestParam String firstname, @RequestParam String lastname) {
        return centreResponsibleService.findByName(firstname, lastname);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CentreResponsible> create(@Valid @RequestBody CentreResponsible centreResponsible) {
        CentreResponsible newCentreResponsible = centreResponsibleService.create(centreResponsible);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCentreResponsible);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentreResponsible> update(
            @PathVariable Long id, @Valid @RequestBody CentreResponsible centreResponsible) {
        try {
            CentreResponsible actualizada = centreResponsibleService.update(id, centreResponsible);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        try {
            centreResponsibleService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
