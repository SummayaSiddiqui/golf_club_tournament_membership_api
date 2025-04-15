package com.keyin.rest.tournament;

import com.keyin.rest.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TournamentTest {

    private Tournament tournament;
    private List<Member> mockMembers;

    @BeforeEach
    void setUp() {
        Member member1 = new Member();
        member1.setId(1L);
        Member member2 = new Member();
        member2.setId(2L);

        mockMembers = Arrays.asList(member1, member2);

        tournament = new Tournament(
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 10),
                "Toronto",
                150.0,
                1000.0,
                mockMembers
        );
    }

    @Test
    void testGetters() {
        assertEquals(LocalDate.of(2025, 5, 1), tournament.getStartDate());
        assertEquals(LocalDate.of(2025, 5, 10), tournament.getEndDate());
        assertEquals("Toronto", tournament.getLocation());
        assertEquals(150.0, tournament.getEntryFee());
        assertEquals(1000.0, tournament.getCashPrizeAmount());
        assertEquals(2, tournament.getParticipatingMembers().size());
    }

    @Test
    void testSetters() {
        tournament.setLocation("Montreal");
        assertEquals("Montreal", tournament.getLocation());

        tournament.setEntryFee(200.0);
        assertEquals(200.0, tournament.getEntryFee());
    }

    @Test
    void testToStringContainsInfo() {
        String result = tournament.toString();
        assertTrue(result.contains("Toronto"));
        assertTrue(result.contains("1000.0"));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("2"));
    }
}
