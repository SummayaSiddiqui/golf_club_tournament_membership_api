package com.keyin.rest.tournament;

import com.keyin.rest.member.Member;
import com.keyin.rest.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class TournamentRepositoryTest {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void setup() {
        tournamentRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    void testFindTournamentByStartDate() {
        Tournament tournament = new Tournament();
        tournament.setStartDate(LocalDate.of(2024, 8, 1));
        tournament.setEndDate(LocalDate.of(2024, 8, 5));
        tournament.setLocation("Toronto");
        tournament.setEntryFee(50.00);
        tournament.setCashPrizeAmount(1000.00);

        tournamentRepository.save(tournament);

        Optional<Tournament> found = tournamentRepository.findTournamentByStartDate(LocalDate.of(2024, 8, 1));
        assertTrue(found.isPresent());
        assertEquals("Toronto", found.get().getLocation());
    }

    @Test
    void testFindTournamentByLocation() {
        Tournament tournament = new Tournament();
        tournament.setStartDate(LocalDate.of(2024, 9, 10));
        tournament.setEndDate(LocalDate.of(2024, 9, 15));
        tournament.setLocation("Vancouver");
        tournament.setEntryFee(75.00);
        tournament.setCashPrizeAmount(1500.00);

        tournamentRepository.save(tournament);

        List<Tournament> found = tournamentRepository.findTournamentByLocation("Vancouver");
        assertEquals(1, found.size());
        assertEquals("Vancouver", found.get(0).getLocation());
    }

    @Test
    void testFindById() {
        Tournament tournament = new Tournament();
        tournament.setStartDate(LocalDate.of(2024, 10, 5));
        tournament.setEndDate(LocalDate.of(2024, 10, 10));
        tournament.setLocation("Calgary");
        tournament.setEntryFee(60.00);
        tournament.setCashPrizeAmount(1200.00);

        tournamentRepository.save(tournament);
        Long id = tournament.getId();

        Optional<Tournament> found = tournamentRepository.findById(id);
        assertTrue(found.isPresent());
        assertEquals("Calgary", found.get().getLocation());
    }

    @Test
    void testFindTournamentByParticipatingMembers() {
        // Create member
        Member member = new Member();
        member.setMemberName("Sarah Johnson");
        member.setMemberAddress("101 Birch Lane, Montreal");
        member.setMemberEmailAddress("sarahjohnson@email.com");
        member.setMemberPhoneNumber("514-555-1234");
        member.setMemberStartDate(LocalDate.of(2023, 11, 25));

        memberRepository.save(member);

        // Create tournament and assign member
        Tournament tournament = new Tournament();
        tournament.setStartDate(LocalDate.of(2024, 11, 20));
        tournament.setEndDate(LocalDate.of(2024, 11, 25));
        tournament.setLocation("Montreal");
        tournament.setEntryFee(80.00);
        tournament.setCashPrizeAmount(2000.00);
        tournament.setParticipatingMembers(List.of(member));

        tournamentRepository.save(tournament);

        List<Tournament> found = tournamentRepository.findTournamentByParticipatingMembers(List.of(member));
        assertFalse(found.isEmpty());
        assertEquals("Montreal", found.get(0).getLocation());
    }
}
