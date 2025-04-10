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
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @GetMapping("/name/{name}")
    public List<Member> getMemberByName(@PathVariable String name) {
        return memberService.getMemberByAddress(name);
    }

    @GetMapping("/getMemberByAddress/{address}")
    public List<Member> getMemberByAddress(@PathVariable String address) {
        return memberService.getMemberByAddress(address);
    }

    @GetMapping("/getMemberByPhoneNumber/{phoneNumber}")
    public Member getMemberByPhoneNumber(@PathVariable String phoneNumber) {
        return memberService.getMemberByPhoneNumber(phoneNumber);
    }

    @GetMapping("/getMemberByEmailAddress/{emailAddress}")
    public Member getMemberByEmailAddress(@PathVariable String emailAddress) {
        return memberService.getMemberByEmailAddress(emailAddress);
    }

    @GetMapping("/getMemberByStartDate")
    public List<Member> getMemberByStartDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        return memberService.getMemberByStartDate(startDate);
    }

    @PostMapping("")
    public Member createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }
}