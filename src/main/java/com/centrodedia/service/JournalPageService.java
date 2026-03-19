package com.centrodedia.service;

import com.centrodedia.model.CentreResponsible;
import com.centrodedia.model.JournalPage;
import com.centrodedia.model.User;
import com.centrodedia.repository.JournalPageRepository;
import com.centrodedia.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JournalPageService {

    private final JournalPageRepository journalPageRepository;
    private final UserRepository userRepository;

    public JournalPageService(JournalPageRepository journalPageRepository, UserRepository userRepository) {
        this.journalPageRepository = journalPageRepository;
        this.userRepository = userRepository;
    }

    public List<JournalPage> listAll() {
        return journalPageRepository.findAll();
    }

    public Optional<JournalPage> findById(Long id) {
        return journalPageRepository.findById(id);
    }

    public List<JournalPage> findByDate(LocalDate date) {
        return journalPageRepository.findByDate(date);
    }

    public List<JournalPage> findByUserId(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return journalPageRepository.findByUser(user);
    }

    public List<JournalPage> findByCentreResponsible(CentreResponsible centreResponsible) {
        return journalPageRepository.findByCentreResponsible(centreResponsible);
    }

    @Transactional
    public JournalPage create(JournalPage journalPage) {
        return journalPageRepository.save(journalPage);
    }

    @Transactional
    public JournalPage update(Long id, JournalPage datosNuevos) {
        JournalPage existente = journalPageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "No journalPage found with id: " + id));

        existente.setBreakfast(datosNuevos.getBreakfast());
        existente.setLunch(datosNuevos.getLunch());
        existente.setAfternoonSnack(datosNuevos.getAfternoonSnack());
        existente.setUrination(datosNuevos.getUrination());
        existente.setDefecation(datosNuevos.getDefecation());
        existente.setCentreComment(datosNuevos.getCentreComment());
        existente.setRelativesComment(datosNuevos.getRelativesComment());

        return journalPageRepository.save(existente);
    }

    @Transactional
    public void delete(Long id) {
        if (!journalPageRepository.existsById(id)) {
            throw new RuntimeException("No journalPage found with id: " + id);
        }
        journalPageRepository.deleteById(id);
    }
}
