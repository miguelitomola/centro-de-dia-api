package com.centrodedia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "centre_responsibles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CentreResponsible {
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
}
