package com.centrodedia.servicio;

import com.centrodedia.model.User;
import com.centrodedia.repository.RelativeRepository;
import com.centrodedia.repository.UserRepository;
import com.centrodedia.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RelativeRepository relativeRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
    }

    @Test
    void testFindById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findById(1L);

        assertTrue(foundUser.isPresent());
        assertEquals("John", foundUser.get().getFirstName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.create(user);

        assertNotNull(createdUser);
        assertEquals(1L, createdUser.getId());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDeleteUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        userService.delete(1L);

        verify(userRepository, times(1)).delete(user);
    }
}
