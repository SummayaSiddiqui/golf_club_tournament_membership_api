package com.keyin.rest.member;

import com.keyin.rest.exception.MemberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        if (members.isEmpty()) {
            throw new MemberNotFoundException("No members found in the system.");
        }
        // Calculate duration for each member
        for (Member member : members) {
            String duration = calculateDuration(member.getMemberStartDate());
            member.setMemberDuration(duration);
        }
        return members;
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with ID: " + id));
    }

    public Member getMemberByName(String memberName) {
        return memberRepository.findByMemberName(memberName)
                .orElseThrow(() -> new MemberNotFoundException("Member with name " + memberName + " not found"));
    }

    public List<Member> getMemberByAddress(String memberAddress) {
        List<Member> members = memberRepository.findByMemberAddress(memberAddress);
        if (members.isEmpty()) {
            throw new MemberNotFoundException("No members found with address: " + memberAddress);
        }
        return members;
    }

    public Member getMemberByPhoneNumber(String phoneNumber) {
        return memberRepository.findByMemberPhoneNumber(phoneNumber)
                .orElseThrow(() -> new MemberNotFoundException("Member with phone number " + phoneNumber + " not found"));
    }

    public Member getMemberByEmailAddress(String emailAddress) {
        return memberRepository.findByMemberEmailAddress(emailAddress)
                .orElseThrow(() -> new MemberNotFoundException("Member with email address " + emailAddress + " not found"));
    }

    public List<Member> getMemberByStartDate(LocalDate startDate) {
        List<Member> members = memberRepository.findByMemberStartDate(startDate);
        if (members.isEmpty()) {
            throw new MemberNotFoundException("No members found with start date: " + startDate);
        }
        return members;
    }

    public Member createMember(Member newMember) {
        // Calculate the duration automatically based on the start date
        String duration = calculateDuration(newMember.getMemberStartDate());

        // Set the calculated duration to the new member
        newMember.setMemberDuration(duration);

        // Save the member with the calculated duration
        return memberRepository.save(newMember);
    }

    // Method to calculate the duration in days
    private String calculateDuration(LocalDate startDate) {
        LocalDate currentDate = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(startDate, currentDate);

        // Return the duration as a string (can also be an integer or other format if needed)
        return String.valueOf(daysBetween); // Return duration as string or you can return it as a different format
    }
}