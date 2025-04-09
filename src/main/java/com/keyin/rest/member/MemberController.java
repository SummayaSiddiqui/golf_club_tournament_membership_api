package com.keyin.rest.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/allMembers")
    public List<Member> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        // Calculate duration for each member
        members.forEach(member -> member.setDuration(calculateDuration(member.getMemberStartDate())));
        return members;
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id) {
        Member member = memberService.getMemberById(id);
        // Calculate duration for the member
        member.setDuration(calculateDuration(member.getMemberStartDate()));
        return member;
    }

    @GetMapping("/name/{name}")
    public Member getMemberByName(@PathVariable String name) {
        Member member = memberService.getMemberByName(name);
        // Calculate duration for the member
        member.setDuration(calculateDuration(member.getMemberStartDate()));
        return member;
    }

    @GetMapping("/getMemberByAddress/{address}")
    public List<Member> getMemberByAddress(@PathVariable String address) {
        List<Member> members = memberService.getMemberByAddress(address);
        // Calculate duration for each member
        members.forEach(member -> member.setDuration(calculateDuration(member.getMemberStartDate())));
        return members;
    }

    @GetMapping("/getMemberByPhoneNumber/{phoneNumber}")
    public Member getMemberByPhoneNumber(@PathVariable String phoneNumber) {
        Member member = memberService.getMemberByPhoneNumber(phoneNumber);
        // Calculate duration for the member
        member.setDuration(calculateDuration(member.getMemberStartDate()));
        return member;
    }

    @GetMapping("/getMemberByEmailAddress/{emailAddress}")
    public Member getMemberByEmailAddress(@PathVariable String emailAddress) {
        Member member = memberService.getMemberByEmailAddress(emailAddress);
        // Calculate duration for the member
        member.setDuration(calculateDuration(member.getMemberStartDate()));
        return member;
    }

    @GetMapping("/getMemberByStartDate")
    public List<Member> getMemberByStartDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        List<Member> members = memberService.getMemberByStartDate(startDate);
        // Calculate duration for each member
        members.forEach(member -> member.setDuration(calculateDuration(member.getMemberStartDate())));
        return members;
    }

    @PostMapping("")
    public Member createMember(@RequestBody Member member) {
        // Set duration during the creation of the member
        member.setDuration(calculateDuration(member.getMemberStartDate()));
        return memberService.createMember(member);
    }

    // Method to calculate the duration in days
    private String calculateDuration(LocalDate startDate) {
        LocalDate currentDate = LocalDate.now();
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(startDate, currentDate);
        return String.valueOf(daysBetween);
    }
}
