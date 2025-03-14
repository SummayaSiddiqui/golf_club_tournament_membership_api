package com.keyin.rest.member;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "member_name", nullable = false)
    private String memberName;

    @Column(name = "member_address", nullable = false)
    private String memberAddress;

    @Column(name = "member_email", unique = true, nullable = false)
    private String memberEmailAddress;

    @Column(name = "member_phone", unique = true, nullable = false)
    private String memberPhoneNumber;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "duration", nullable = false)
    private String duration;

    public Member() {

    }

    public Member(long id) {
        this.id = id;
    }

    public Member(String memberName, String memberAddress, String memberEmailAddress, String memberPhoneNumber, LocalDate startDate, String duration) {
        this.memberName = memberName;
        this.memberAddress = memberAddress;
        this. memberEmailAddress = memberEmailAddress;
        this.memberPhoneNumber = memberPhoneNumber;
        this.startDate = startDate;
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public String getMemberEmailAddress() {
        return memberEmailAddress;
    }

    public String getMemberPhoneNumber() {
        return memberPhoneNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public void setMemberEmailAddress(String memberEmailAddress) {
        this.memberEmailAddress = memberEmailAddress;
    }

    public void setMemberPhoneNumber(String memberPhoneNumber) {
        this.memberPhoneNumber = memberPhoneNumber;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", memberName='" + memberName + '\'' +
                ", memberAddress='" + memberAddress + '\'' +
                ", memberEmailAddress='" + memberEmailAddress + '\'' +
                ", memberPhoneNumber='" + memberPhoneNumber + '\'' +
                ", startDate='" + startDate + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}

