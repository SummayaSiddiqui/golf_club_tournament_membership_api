package com.keyin.rest.member;

import com.keyin.rest.exception.MemberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        return members;
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with ID: " + id));
    }

    public List<Member> getMemberByName(String memberName) {
        List<Member> members = memberRepository.findByMemberName(memberName);
        if (members.isEmpty()) {
            throw new MemberNotFoundException("No members found with name: " + memberName);
        }
        return members;
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
        // Save the member without calculating the duration
        return memberRepository.save(newMember);
    }
}
