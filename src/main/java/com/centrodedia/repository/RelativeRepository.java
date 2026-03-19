package com.centrodedia.repository;

import com.centrodedia.model.Relative;
import com.centrodedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RelativeRepository extends JpaRepository<Relative, Long> {
    @Query("SELECT r FROM User u JOIN u.relatives r WHERE u = :user")
    List<Relative> findByUser(@Param("user") User user);

    List<Relative> findByFirstNameAndLastName(String firstname, String lastname);

    Optional<Relative> findByIdNumber(String idNumber);
}
