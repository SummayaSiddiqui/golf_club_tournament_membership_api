package com.keyin.rest.tournament;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.rest.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TournamentController.class)
public class TournamentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TournamentService tournamentService;

    @Autowired
    private ObjectMapper objectMapper;

    private Tournament tournament1;
    private Member member1;

    @BeforeEach
    void setUp() {
        // Create a sample member for testing
        member1 = new Member();
        member1.setId(1L);
        member1.setMemberName("John Doe");
        member1.setMemberAddress("123 Main St");
        member1.setMemberEmailAddress("john.doe@email.com");
        member1.setMemberPhoneNumber("123-456-7890");
        member1.setMemberStartDate(LocalDate.now());

        // Create a sample tournament for testing
        tournament1 = new Tournament();
        tournament1.setId(1L);
        tournament1.setStartDate(LocalDate.now());
        tournament1.setEndDate(LocalDate.now().plusDays(5));
        tournament1.setLocation("New York");
        tournament1.setParticipatingMembers(Arrays.asList(member1));
    }

    @Test
    void testGetAllTournaments() throws Exception {
        List<Tournament> tournaments = Arrays.asList(tournament1);
        when(tournamentService.getAllTournaments()).thenReturn(tournaments);

        mockMvc.perform(get("/api/tournaments/allTournaments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].location").value("New York"))
                .andExpect(jsonPath("$[0].startDate").value(tournament1.getStartDate().toString()))
                .andExpect(jsonPath("$[0].endDate").value(tournament1.getEndDate().toString()))
                .andExpect(jsonPath("$[0].participatingMembers[0].memberName").value("John Doe"));
    }

    @Test
    void testGetTournamentByStartDate() throws Exception {
        tournament1.setStartDate(LocalDate.of(2025, 4, 15)); // Set correct start date for testing
        when(tournamentService.getTournamentByStartDate(LocalDate.of(2025, 4, 15))).thenReturn(tournament1);

        mockMvc.perform(get("/api/tournaments/getTournamentByStartDate/{startDate}", "2025-04-15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.location").value("New York"))
                .andExpect(jsonPath("$.startDate").value("2025-04-15"))
                .andExpect(jsonPath("$.endDate").value("2025-04-20"))
                .andExpect(jsonPath("$.participatingMembers[0].memberName").value("John Doe"))
                .andExpect(jsonPath("$.participatingMembers[0].memberPhoneNumber").value("123-456-7890"));
    }

    @Test
    void testGetTournamentByEndDate() throws Exception {
        when(tournamentService.getTournamentByEndDate(LocalDate.now().plusDays(5))).thenReturn(tournament1);

        mockMvc.perform(get("/api/tournaments/getTournamentByEndDate/{tournamentEndDate}", LocalDate.now().plusDays(5)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.location").value("New York"))
                .andExpect(jsonPath("$.startDate").value("2025-04-15"))
                .andExpect(jsonPath("$.endDate").value("2025-04-20"))
                .andExpect(jsonPath("$.participatingMembers[0].memberName").value("John Doe"))
                .andExpect(jsonPath("$.participatingMembers[0].memberPhoneNumber").value("123-456-7890"));
    }

    @Test
    void testGetTournamentByLocation() throws Exception {
        List<Tournament> tournaments = Arrays.asList(tournament1);
        when(tournamentService.getTournamentByLocation("New York")).thenReturn(tournaments);

        mockMvc.perform(get("/api/tournaments/getTournamentByLocation/{location}", "New York"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].location").value("New York"))
                .andExpect(jsonPath("$[0].startDate").value("2025-04-15"))
                .andExpect(jsonPath("$[0].endDate").value("2025-04-20"))
                .andExpect(jsonPath("$[0].participatingMembers[0].memberName").value("John Doe"))
                .andExpect(jsonPath("$[0].participatingMembers[0].memberPhoneNumber").value("123-456-7890"));
    }

    @Test
    void testGetMembersInTournament() throws Exception {
        when(tournamentService.getTournamentById(1L)).thenReturn(tournament1);

        mockMvc.perform(get("/api/tournaments/getMembersInTournament/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].memberName").value("John Doe"))
                .andExpect(jsonPath("$[0].memberPhoneNumber").value("123-456-7890"));
    }

    @Test
    void testCreateTournament() throws Exception {
        when(tournamentService.createTournament(any(Tournament.class))).thenReturn(tournament1);

        mockMvc.perform(post("/api/tournaments")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(tournament1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value("New York"))
                .andExpect(jsonPath("$.startDate").value(tournament1.getStartDate().toString()))
                .andExpect(jsonPath("$.endDate").value(tournament1.getEndDate().toString()))
                .andExpect(jsonPath("$.participatingMembers[0].memberName").value("John Doe"));
    }
}
