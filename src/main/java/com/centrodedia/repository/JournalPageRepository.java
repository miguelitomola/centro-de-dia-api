package com.centrodedia.repository;

import com.centrodedia.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface JournalPageRepository extends JpaRepository<JournalPage, Long> {
    List<JournalPage> findByDate(LocalDate date);

    List<JournalPage> findByUser(User user);

    List<JournalPage> findByCentreResponsible(CentreResponsible centreResponsible);
}
