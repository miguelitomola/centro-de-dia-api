package com.centrodedia.servicio;

import com.centrodedia.model.JournalPage;
import com.centrodedia.model.User;
import com.centrodedia.repository.JournalPageRepository;
import com.centrodedia.repository.UserRepository;
import com.centrodedia.service.JournalPageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JournalPageServiceTest {

    @Mock
    private JournalPageRepository journalPageRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JournalPageService journalPageService;

    private JournalPage journalPage;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        journalPage = new JournalPage();
        journalPage.setId(1L);
        journalPage.setDate(LocalDate.now());
        journalPage.setUser(user);
    }

    @Test
    void testFindById() {
        when(journalPageRepository.findById(1L)).thenReturn(Optional.of(journalPage));

        Optional<JournalPage> foundJournalPage = journalPageService.findById(1L);

        assertTrue(foundJournalPage.isPresent());
        assertEquals(1L, foundJournalPage.get().getId());
        verify(journalPageRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateJournalPage() {
        when(journalPageRepository.save(any(JournalPage.class))).thenReturn(journalPage);

        JournalPage createdJournalPage = journalPageService.create(journalPage);

        assertNotNull(createdJournalPage);
        assertEquals(1L, createdJournalPage.getId());
        verify(journalPageRepository, times(1)).save(journalPage);
    }

    @Test
    void testDeleteJournalPage() {
        when(journalPageRepository.existsById(1L)).thenReturn(true);
        doNothing().when(journalPageRepository).deleteById(1L);

        journalPageService.delete(1L);

        verify(journalPageRepository, times(1)).deleteById(1L);
    }
}
