package com.centrodedia.service;

import com.centrodedia.model.CentreResponsible;
import com.centrodedia.repository.CentreResponsibleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CentreResponsibleService {

    private final CentreResponsibleRepository centreResponsibleRepository;

    public CentreResponsibleService(CentreResponsibleRepository centreResponsibleRepository) {
        this.centreResponsibleRepository = centreResponsibleRepository;
    }

    public List<CentreResponsible> listAll() {
        return centreResponsibleRepository.findAll();
    }

    public Optional<CentreResponsible> findById(Long id) {
        return centreResponsibleRepository.findById(id);
    }

    public List<CentreResponsible> findByName(String firstName, String lastName) {
        return centreResponsibleRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public Optional<CentreResponsible> findByIdNumber(String idNumber) {
        return centreResponsibleRepository.findByIdNumber(idNumber);
    }

    @Transactional
    public CentreResponsible create(CentreResponsible centreResponsible) {
        return centreResponsibleRepository.save(centreResponsible);
    }

    @Transactional
    public CentreResponsible update(Long id, CentreResponsible datosNuevos) {
        CentreResponsible existente = centreResponsibleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "No responsible found with id:" + id));

        existente.setFirstName(datosNuevos.getFirstName());
        existente.setLastName(datosNuevos.getLastName());

        return centreResponsibleRepository.save(existente);
    }

    @Transactional
    public void delete(Long id) {
        if (!centreResponsibleRepository.existsById(id)) {
            throw new RuntimeException("No centreResponsible found with id: " + id);
        }
        centreResponsibleRepository.deleteById(id);
    }
}
