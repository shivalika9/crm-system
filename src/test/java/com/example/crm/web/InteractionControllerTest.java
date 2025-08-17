package com.example.crm.web;

import static org.junit.jupiter.api.Assertions.*;


import com.example.crm.domain.Interaction;
import com.example.crm.service.InteractionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class InteractionControllerTest {

    @Mock
    private InteractionService interactionService;

    @InjectMocks
    private InteractionController interactionController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Interaction interaction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(interactionController).build();
        objectMapper = new ObjectMapper();

        interaction = new Interaction();
        interaction.setId(1L);
        interaction.setType("Email");
        interaction.setNotes("Welcome mail");
        interaction.setOwner("Agent2");
    }

    @Test
    void testGetAllInteractions() throws Exception {
        when(interactionService.listAll()).thenReturn(Arrays.asList(interaction));

        mockMvc.perform(get("/api/interactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("Email"));
    }

    @Test
    void testCreateInteraction() throws Exception {
        when(interactionService.create(any(Long.class), any(Interaction.class))).thenReturn(interaction);

        InteractionController.InteractionRequest req = new InteractionController.InteractionRequest();
        req.setCustomerId(1L);
        req.setType("Meeting");
        req.setNotes("Project discussion");
        req.setOwner("Agent3");

        mockMvc.perform(post("/api/interactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Email"));
    }
}
