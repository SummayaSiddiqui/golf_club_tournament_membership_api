package com.keyin.rest.tournament;

import com.keyin.rest.exception.TournamentNotFoundException; // Changed to a more specific exception
import com.keyin.rest.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TournamentService {
    private final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public List<Tournament> getAllTournaments() {
        List<Tournament> tournaments = tournamentRepository.findAll();
        if (tournaments.isEmpty()) {
            throw new TournamentNotFoundException("No tournaments found in the system.");
        }
        return tournaments;
    }

    public Tournament getTournamentByStartDate(LocalDate startDate) {
        return tournamentRepository.findTournamentByStartDate(startDate)
                .orElseThrow(() -> new TournamentNotFoundException("Tournament starting from " + startDate + " not found"));
    }

    public Tournament getTournamentByEndDate(LocalDate endDate) {
        return tournamentRepository.findTournamentByEndDate(endDate)
                .orElseThrow(() -> new TournamentNotFoundException("Tournament ending on " + endDate + " not found"));
    }

    public List<Tournament> getTournamentByLocation(String location) {
        List<Tournament> tournaments = tournamentRepository.findTournamentByLocation(location);
        if (tournaments.isEmpty()) {
            throw new TournamentNotFoundException("No tournaments found at location: " + location);
        }
        return tournaments;
    }

    public List<Tournament> getTournamentByCashPrizeAmount(double cashPrizeAmount) {
        List<Tournament> tournaments = tournamentRepository.findTournamentByCashPrizeAmount(cashPrizeAmount);
        if (tournaments.isEmpty()) {
            throw new TournamentNotFoundException("No tournaments found with cash prize amount: " + cashPrizeAmount);
        }
        return tournaments;
    }

    public List<Tournament> getTournamentsByParticipatingMembers(List<Member> participatingMembers) {
        List<Tournament> tournaments = tournamentRepository.findTournamentByParticipatingMembers(participatingMembers);
        if (tournaments.isEmpty()) {
            throw new TournamentNotFoundException("No tournaments found with participating members: " + participatingMembers);
        }
        return tournaments;
    }
}
