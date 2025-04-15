package com.keyin.rest.tournament;

import com.keyin.rest.exception.TournamentNotFoundException;
import com.keyin.rest.member.Member;
import com.keyin.rest.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TournamentServiceTest {

    @InjectMocks
    private TournamentService tournamentService;

    @Mock
    private TournamentRepository tournamentRepository;

    @Mock
    private MemberRepository memberRepository;

    private Tournament tournament;
    private Member member;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        member = new Member("John Doe", "123 Main St", "john.doe@example.com", "123-456-7890", LocalDate.of(2020, 1, 1));

        tournament = new Tournament();
        tournament.setStartDate(LocalDate.of(2024, 8, 1));
        tournament.setEndDate(LocalDate.of(2024, 8, 5));
        tournament.setLocation("Toronto");
        tournament.setEntryFee(50.00);
        tournament.setCashPrizeAmount(1000.00);
        tournament.setParticipatingMembers(Arrays.asList(member));
    }

    @Test
    void testGetAllTournaments_ShouldThrowTournamentNotFoundException_WhenNoTournamentsFound() {
        when(tournamentRepository.findAll()).thenReturn(Arrays.asList());

        assertThrows(TournamentNotFoundException.class, () -> tournamentService.getAllTournaments());
    }

    @Test
    void testGetAllTournaments_ShouldReturnTournaments_WhenTournamentsExist() {
        when(tournamentRepository.findAll()).thenReturn(Arrays.asList(tournament));

        List<Tournament> tournaments = tournamentService.getAllTournaments();

        assertNotNull(tournaments);
        assertEquals(1, tournaments.size());
        assertEquals("Toronto", tournaments.get(0).getLocation());
    }

    @Test
    void testGetTournamentByStartDate_ShouldThrowTournamentNotFoundException_WhenTournamentNotFound() {
        when(tournamentRepository.findTournamentByStartDate(LocalDate.of(2024, 8, 1))).thenReturn(Optional.empty());

        assertThrows(TournamentNotFoundException.class, () -> tournamentService.getTournamentByStartDate(LocalDate.of(2024, 8, 1)));
    }

    @Test
    void testGetTournamentByStartDate_ShouldReturnTournament_WhenTournamentFound() {
        when(tournamentRepository.findTournamentByStartDate(LocalDate.of(2024, 8, 1))).thenReturn(Optional.of(tournament));

        Tournament foundTournament = tournamentService.getTournamentByStartDate(LocalDate.of(2024, 8, 1));

        assertNotNull(foundTournament);
        assertEquals("Toronto", foundTournament.getLocation());
    }

    @Test
    void testGetTournamentByLocation_ShouldThrowTournamentNotFoundException_WhenNoTournamentsFound() {
        when(tournamentRepository.findTournamentByLocation("Toronto")).thenReturn(Arrays.asList());

        assertThrows(TournamentNotFoundException.class, () -> tournamentService.getTournamentByLocation("Toronto"));
    }

    @Test
    void testGetTournamentByLocation_ShouldReturnTournaments_WhenTournamentsExist() {
        when(tournamentRepository.findTournamentByLocation("Toronto")).thenReturn(Arrays.asList(tournament));

        List<Tournament> tournaments = tournamentService.getTournamentByLocation("Toronto");

        assertNotNull(tournaments);
        assertEquals(1, tournaments.size());
        assertEquals("Toronto", tournaments.get(0).getLocation());
    }

    @Test
    void testGetTournamentById_ShouldThrowTournamentNotFoundException_WhenTournamentNotFound() {
        when(tournamentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TournamentNotFoundException.class, () -> tournamentService.getTournamentById(1L));
    }

    @Test
    void testGetTournamentById_ShouldReturnTournament_WhenTournamentFound() {
        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));

        Tournament foundTournament = tournamentService.getTournamentById(1L);

        assertNotNull(foundTournament);
        assertEquals("Toronto", foundTournament.getLocation());
    }
}
