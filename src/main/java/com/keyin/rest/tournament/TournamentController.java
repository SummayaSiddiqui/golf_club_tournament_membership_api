package com.keyin.rest.tournament;

import com.keyin.rest.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
@CrossOrigin(origins = "*")
public class TournamentController {

    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/allTournaments")
    public List<Tournament> getAllMTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/getTournamentByStartDate/{tournamentStartDate}")
    public Tournament getTournamentByStartDate(@PathVariable LocalDate tournamentStartDate) {
        return tournamentService.getTournamentByStartDate(tournamentStartDate);
    }

    @GetMapping("/getTournamentByEndDate/{tournamentEndDate}")
    public Tournament getTournamentByEndDate(@PathVariable LocalDate tournamentEndDate) {
        return tournamentService.getTournamentByEndDate(tournamentEndDate);
    }

    @GetMapping("/getTournamentByLocation/{location}")
    public List<Tournament> getTournamentByLocation(@PathVariable String location) {
        return tournamentService.getTournamentByLocation(location);
    }

    @GetMapping("/getMembersInTournament/{id}")
    public List<Member> getMembersInTournament(@PathVariable long id) {
        Tournament tournament = tournamentService.getTournamentById(id);
        return tournament.getParticipatingMembers();
    }

    @PostMapping("")
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentService.createTournament(tournament);
    }
}
