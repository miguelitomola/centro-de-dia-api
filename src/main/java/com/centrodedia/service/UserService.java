package com.centrodedia.service;

import com.centrodedia.model.Relative;
import com.centrodedia.model.User;
import com.centrodedia.repository.RelativeRepository;
import com.centrodedia.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RelativeRepository relativeRepository;

    public UserService(UserRepository userRepository,  RelativeRepository relativeRepository) {
        this.userRepository = userRepository;
        this.relativeRepository = relativeRepository;
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findByName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public Optional<User> findByIdNumber(String idNumber) {
        return userRepository.findByIdNumber(idNumber);
    }

    public List<User> findByRelativeId(Long id) {
        Relative relative = relativeRepository.findById(id).orElse(null);
        return userRepository.findByRelative(relative);
    }

    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User update(Long id, User datosNuevos) {
        User existente = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "No user found with id: " + id));

        existente.setFirstName(datosNuevos.getFirstName());
        existente.setLastName(datosNuevos.getLastName());

        return userRepository.save(existente);
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found with id: " + id));
        user.getRelatives().clear();
        userRepository.delete(user);
    }
}
