package com.keyin.rest.tournament;

import com.keyin.rest.member.Member;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tournament_start_date", nullable = false)
    private LocalDate tournamentStartDate;

    @Column(name = "tournament_end_date", nullable = false)
    private LocalDate tournamentEndDate;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "entry_fee", nullable = false)
    private double entryFee;

    @Column(name = "cash_prize_amount", nullable = false)
    private double cashPrizeAmount;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "tournament_members",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private List<Member> participatingMembers;

    public Tournament() {

    }

    public Tournament(long id) {
        this.id = id;
    }

    public Tournament(LocalDate tournamentStartDate, LocalDate tournamentEndDate, String location, double entryFee, double cashPrizeAmount, List<Member> participatingMembers) {
        this.tournamentStartDate = tournamentStartDate;
        this.tournamentEndDate = tournamentEndDate;
        this.location = location;
        this.entryFee = entryFee;
        this.cashPrizeAmount = cashPrizeAmount;
        this.participatingMembers = participatingMembers;
    }

    public long getId() {
        return id;
    }

    public LocalDate getTournamentStartDate() {
        return tournamentStartDate;
    }

    public LocalDate getTournamentEndDate() {
        return tournamentEndDate;
    }

    public String getLocation() {
        return location;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public List<Member> getParticipatingMembers() {
        return participatingMembers;
    }

    public double getCashPrizeAmount() {
        return cashPrizeAmount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTournamentStartDate(LocalDate tournamentStartDate) {
        this.tournamentStartDate = tournamentStartDate;
    }

    public void setTournamentEndDate(LocalDate tournamentEndDate) {
        this.tournamentEndDate = tournamentEndDate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEntryFee(double entryFee) {
        this.entryFee = entryFee;
    }

    public void setCashPrizeAmount(double cashPrizeAmount) {
        this.cashPrizeAmount = cashPrizeAmount;
    }

    public void setParticipatingMembers(List<Member> participatingMembers) {
        this.participatingMembers = participatingMembers;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", tournamentStartDate=" + tournamentStartDate +
                ", tournamentEndDate=" + tournamentEndDate +
                ", location='" + location + '\'' +
                ", entryFee=" + entryFee +
                ", cashPrizeAmount=" + cashPrizeAmount +
                ", participatingMembers=" + participatingMembers.stream().map(Member::getId).toList() +
                '}';
    }

}
