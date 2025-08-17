package com.example.crm.service;

import com.example.crm.domain.Customer;
import com.example.crm.domain.Interaction;
import com.example.crm.repo.CustomerRepository;
import com.example.crm.repo.InteractionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InteractionServiceTest {

    @Mock
    private InteractionRepository interactionRepo;

    @Mock
    private CustomerRepository customerRepo;

    @InjectMocks
    private InteractionService interactionService;

    private Customer customer;
    private Interaction interaction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer();
        customer.setId(1L);

        interaction = new Interaction();
        interaction.setId(1L);
        interaction.setType("Call");
        interaction.setNotes("Follow-up");
        interaction.setOwner("Agent1");
        interaction.setCustomer(customer);
    }

    @Test
    void testListAllInteractions() {
        when(interactionRepo.findAll()).thenReturn(Arrays.asList(interaction));

        List<Interaction> interactions = interactionService.listAll();

        assertEquals(1, interactions.size());
        assertEquals("Call", interactions.get(0).getType());
    }

    @Test
    void testCreateInteraction() {
        when(customerRepo.findById(1L)).thenReturn(Optional.of(customer));
        when(interactionRepo.save(any(Interaction.class))).thenReturn(interaction);

        Interaction saved = interactionService.create(1L, interaction);

        assertNotNull(saved);
        assertEquals("Agent1", saved.getOwner());
    }
}
