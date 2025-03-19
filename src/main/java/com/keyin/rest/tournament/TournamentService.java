package com.keyin.rest.tournament;

import com.keyin.rest.exception.TournamentNotFoundException; // Changed to a more specific exception
import com.keyin.rest.member.Member;
import com.keyin.rest.member.MemberRepository;
import com.keyin.rest.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentService {
    private final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Autowired
    private MemberRepository memberRepository;

    public List<Tournament> getAllTournaments() {
        List<Tournament> tournaments = tournamentRepository.findAll();
        if (tournaments.isEmpty()) {
            throw new TournamentNotFoundException("No tournaments found in the system.");
        }
        return tournaments;
    }

    public Tournament getTournamentByStartDate(LocalDate tournamentStartDate) {
        return tournamentRepository.findTournamentByStartDate(tournamentStartDate)
                .orElseThrow(() -> new TournamentNotFoundException("Tournament starting from " + tournamentStartDate + " not found"));
    }

    public Tournament getTournamentByEndDate(LocalDate tournamentEndDate) {
        return tournamentRepository.findTournamentByEndDate(tournamentEndDate)
                .orElseThrow(() -> new TournamentNotFoundException("Tournament ending on " + tournamentEndDate + " not found"));
    }

    public List<Tournament> getTournamentByLocation(String location) {
        List<Tournament> tournaments = tournamentRepository.findTournamentByLocation(location);
        if (tournaments.isEmpty()) {
            throw new TournamentNotFoundException("No tournaments found at location: " + location);
        }
        return tournaments;
    }

    public Tournament getTournamentById(long id) {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new TournamentNotFoundException("Tournament with ID " + id + " not found"));
    }

    public List<Tournament> getTournamentByParticipatingMembers(List<Member> participatingMembers) {
        List<Tournament> tournaments = tournamentRepository.findTournamentByParticipatingMembers(participatingMembers);
        if (tournaments.isEmpty()) {
            throw new TournamentNotFoundException("No tournaments found with participating members: " + participatingMembers);
        }
        return tournaments;
    }

    public Tournament createTournament(Tournament tournament) {
        List<Member> members = memberRepository.findAllById(
                tournament.getParticipatingMembers().stream()
                        .map(Member::getId)
                        .collect(Collectors.toList())
        );

        tournament.setParticipatingMembers(members);

        return tournamentRepository.save(tournament);
    }
}
