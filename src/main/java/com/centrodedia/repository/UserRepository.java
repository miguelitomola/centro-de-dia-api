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
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u JOIN u.relatives r WHERE r = :relative")
    List<User> findByRelative(@Param("relative") Relative relative);

    List<User> findByFirstNameAndLastName(String firstname, String lastname);

    Optional<User> findByIdNumber(String idNumber);
}
