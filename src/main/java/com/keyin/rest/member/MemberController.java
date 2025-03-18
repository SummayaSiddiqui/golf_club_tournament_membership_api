package com.keyin.rest.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/allMembers")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/name/{name}")
    public Member getMemberByName(@PathVariable String name) {
        return memberService.getMemberByName(name);
    }

    @GetMapping("/getMemberByAddress/{address}")
    public Member getMemberByAddress(@PathVariable String address) {
        return (Member) memberService.getMemberByAddress(address);
    }

    @GetMapping("/getMemberByPhoneNumber/{phoneNumber}")
    public Member getMemberByPhoneNumber(@PathVariable String phoneNumber) {
        return memberService.getMemberByPhoneNumber(phoneNumber);
    }

    @GetMapping("/getMemberByEmailAddress/{emailAddress}")
    public Member getMemberByEmailAddress(@PathVariable String emailAddress) {
        return memberService.getMemberByEmailAddress(emailAddress);
    }

    @GetMapping("/getMemberByStartDate/{startDate}")
    public Member getMemberByStartDate(@PathVariable LocalDate startDate) {
        return (Member) memberService.getMemberByStartDate(startDate);
    }

    @PostMapping("")
    public Member createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }
}
