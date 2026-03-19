package com.centrodedia.repository;

import com.centrodedia.model.CentreResponsible;
import com.centrodedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CentreResponsibleRepository extends JpaRepository<CentreResponsible, Long> {
    List<CentreResponsible> findByFirstNameAndLastName(String firstName, String lastName);
    Optional<CentreResponsible> findByIdNumber(String idNumber);
}
