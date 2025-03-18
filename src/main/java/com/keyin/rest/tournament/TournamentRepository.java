package com.keyin.rest.tournament;

import com.keyin.rest.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    Optional<Tournament> findTournamentByStartDate (LocalDate startDate);

    Optional<Tournament> findTournamentByEndDate (LocalDate endDate);

    List<Tournament> findTournamentByLocation(String location);

    public Optional<Tournament> findById(long id);

    List<Tournament> findTournamentByParticipatingMembers(List<Member> participatingMembers);

}
