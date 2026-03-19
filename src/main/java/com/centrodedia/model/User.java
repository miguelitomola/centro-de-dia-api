package com.centrodedia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del usuario es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "El apellido del usuario es obligatorio")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    @Column(nullable = false)
    private String lastName;

    @Pattern(regexp = "^[XYZxyz]?\\\\d{7,8}[A-Za-z]$")
    @Column(unique = true)
    private String idNumber;

    @ManyToMany
    @JoinTable(
        name = "users_relatives",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "relative_id")
    )

    private List<Relative> relatives = new ArrayList<>();
}
