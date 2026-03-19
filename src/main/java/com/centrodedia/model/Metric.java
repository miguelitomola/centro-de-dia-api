package com.centrodedia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "metrics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PastOrPresent
    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MetricCategory metricCategory;

    @NotNull
    @Column(name = "metric_value")
    double value;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "centre_responsible_id")
    private CentreResponsible centre_responsible;
}
