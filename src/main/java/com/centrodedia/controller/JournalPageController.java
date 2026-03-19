package com.centrodedia.controller;

import com.centrodedia.model.JournalPage;
import com.centrodedia.service.JournalPageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/journal-pages")
public class JournalPageController {

    private final JournalPageService journalPageService;

    public JournalPageController(JournalPageService journalPageService) {
        this.journalPageService = journalPageService;
    }

    @GetMapping
    public List<JournalPage> getJournalPages() {
        return journalPageService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalPage> getJournalPageById(@PathVariable Long id) {
        return journalPageService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{id}")
    public List<JournalPage> getJournalPagesByUserId(@PathVariable Long id) {
        return journalPageService.findByUserId(id);
    }

    @GetMapping("/date")
    public List<JournalPage> getJournalPageByDate(
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam int day) {
        LocalDate date = LocalDate.of(year, month, day);
        return journalPageService.findByDate(date);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JournalPage createJournalPage(@Valid @RequestBody JournalPage journalPage) {
        return journalPageService.create(journalPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JournalPage> updateJournalPage(@PathVariable Long id, @Valid @RequestBody JournalPage journalPage) {
        try {
            JournalPage updatedJournalPage = journalPageService.update(id, journalPage);
            return ResponseEntity.ok(updatedJournalPage);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJournalPage(@PathVariable Long id) {
        journalPageService.delete(id);
    }
}
