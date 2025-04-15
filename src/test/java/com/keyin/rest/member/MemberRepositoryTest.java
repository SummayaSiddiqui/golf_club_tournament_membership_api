package com.keyin.rest.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void setup() {
        memberRepository.deleteAll();
    }

    @Test
    void testFindMemberById() {
        Member member = new Member();
        member.setMemberName("Find Member");
        member.setMemberAddress("Test Address");
        member.setMemberEmailAddress("find@email.com");
        member.setMemberPhoneNumber("1234567890");
        member.setMemberStartDate(LocalDate.now());

        memberRepository.save(member);
        Long memberId = member.getId();

        Optional<Member> foundMember = memberRepository.findById(memberId);
        assertTrue(foundMember.isPresent());
        assertEquals(memberId, foundMember.get().getId());
    }

    @Test
    void testFindAllMembers() {
        Member member1 = new Member();
        member1.setMemberName("Member 1");
        member1.setMemberAddress("Address 1");
        member1.setMemberEmailAddress("member1@email.com");
        member1.setMemberPhoneNumber("1234567890");
        member1.setMemberStartDate(LocalDate.now());

        Member member2 = new Member();
        member2.setMemberName("Member 2");
        member2.setMemberAddress("Address 2");
        member2.setMemberEmailAddress("member2@email.com");
        member2.setMemberPhoneNumber("0987654321");
        member2.setMemberStartDate(LocalDate.now());

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> members = memberRepository.findAll();
        assertNotNull(members);
        assertEquals(2, members.size());
    }

    @Test
    void testFindMemberByEmail() {
        Member member = new Member();
        member.setMemberName("Email Member");
        member.setMemberAddress("Email Address");
        member.setMemberEmailAddress("emailmember@email.com");
        member.setMemberPhoneNumber("1234567890");
        member.setMemberStartDate(LocalDate.now());

        memberRepository.save(member);

        Optional<Member> foundMember = memberRepository.findByMemberEmailAddress("emailmember@email.com");

        // Assert that the member is present
        assertTrue(foundMember.isPresent(), "Member should be found by email");

        // Assert that the found member's email matches the expected email
        assertEquals("emailmember@email.com", foundMember.get().getMemberEmailAddress());
    }
}

