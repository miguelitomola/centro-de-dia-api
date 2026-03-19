package com.centrodedia.service;

import com.centrodedia.model.Relative;
import com.centrodedia.model.User;
import com.centrodedia.repository.RelativeRepository;
import com.centrodedia.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RelativeService {

    private final RelativeRepository relativeRepository;
    private final UserRepository userRepository;

    public RelativeService(RelativeRepository relativeRepository, UserRepository userRepository) {
        this.relativeRepository = relativeRepository;
        this.userRepository = userRepository;
    }

    public List<Relative> listAll() {
        return relativeRepository.findAll();
    }

    public Optional<Relative> findById(Long id) {
        return relativeRepository.findById(id);
    }

    public List<Relative> findByUserId(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return relativeRepository.findByUser(user);
    }

    public List<Relative> findByName(String firstName, String lastName) {
        return relativeRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public Optional<Relative> findByIdNumber(String idNumber) {
        return relativeRepository.findByIdNumber(idNumber);
    }

    @Transactional
    public Relative create(Relative relative) {
        return relativeRepository.save(relative);
    }

    @Transactional
    public Relative update(Long id, Relative datosNuevos) {
        Relative existente = relativeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "No relative found with id: " + id));

        existente.setFirstName(datosNuevos.getFirstName());
        existente.setLastName(datosNuevos.getLastName());

        return relativeRepository.save(existente);
    }

    @Transactional
    public void delete(Long id) {
        // BUSCAR EL FAMILIAR
        Relative relative = relativeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No relative found with id: " + id));

        // ENCONTRAR QUIÉN LO TIENE ASIGNADO
        // Como 'User' es el "dueño" de la relación (quien tiene el @JoinTable),
        // el Relative no sabe automáticamente a qué usuarios pertenece.
        // Buscamos manualmente todos los usuarios que tienen este familiar en su lista.
        List<User> users = userRepository.findByRelative(relative);

        // DESVINCULAR (Romper la relación)
        for (User user : users) {
            // Quitamos al familiar de la lista del usuario.
            // Esto solo actualiza el objeto en memoria Java.
            user.getRelatives().remove(relative);

            // Guardamos el usuario. Aquí es donde ocurre la "magia" de JPA:
            // Hibernate detecta que la lista cambió y lanza un DELETE a la tabla intermedia
            // 'users_relatives' para eliminar esa conexión específica.
            userRepository.save(user);
        }
        // BORRAR EL FAMILIAR
        // Ahora que ya no hay referencias a este familiar en la tabla intermedia,
        // podemos borrarlo físicamente de la tabla 'relatives' sin errores.
        relativeRepository.delete(relative);
    }
}
