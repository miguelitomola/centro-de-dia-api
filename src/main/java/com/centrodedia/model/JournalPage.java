package com.centrodedia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "journal_pages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JournalPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PastOrPresent
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private MealStatus breakfast;

    @Enumerated(EnumType.STRING)
    private MealStatus lunch;

    @Enumerated(EnumType.STRING)
    private MealStatus afternoonSnack;

    private Boolean urination;  // Boolean class used to work with lombok

    private Boolean defecation;  // Boolean class used to work with lombok

    @Size(max = 500)
    private String centreComment;

    @Size(max = 500)
    private String relativesComment;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "centre_responsible_id")
    private CentreResponsible centreResponsible;
}
