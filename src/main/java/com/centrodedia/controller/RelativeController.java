package com.centrodedia.controller;

import com.centrodedia.model.Relative;
import com.centrodedia.service.RelativeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relatives")
public class RelativeController {

    private final RelativeService relativeService;

    public RelativeController(RelativeService relativeService) {
        this.relativeService = relativeService;
    }

    @GetMapping
    public List<Relative> getRelatives() {
        return relativeService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Relative> getRelativeById(@PathVariable Long id) {
        return relativeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/idnumber/{idNumber}")
    public ResponseEntity<Relative> getRelativeByIdNumber(@PathVariable String idNumber) {
        return relativeService.findByIdNumber(idNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name")
    public List<Relative> getRelativesByName(@RequestParam String firstname, @RequestParam String lastname) {
        return relativeService.findByName(firstname, lastname);
    }

    @GetMapping("/user/{id}")
    public List<Relative> getRelativesByUserId(@PathVariable Long id) {
        return relativeService.findByUserId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Relative> create(@Valid @RequestBody Relative relative) {
        Relative newRelative = relativeService.create(relative);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRelative);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Relative> update(
            @PathVariable Long id, @Valid @RequestBody Relative relative) {
        try {
            Relative actualizada = relativeService.update(id, relative);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        try {
            relativeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
