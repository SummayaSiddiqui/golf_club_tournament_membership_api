package com.keyin.rest.member;

import com.keyin.rest.exception.MemberNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        member = new Member("John Doe", "123 Main St", "john.doe@example.com", "123-456-7890", LocalDate.of(2020, 1, 1));
    }

    @Test
    void testGetAllMembers_ShouldThrowMemberNotFoundException_WhenNoMembersFound() {
        when(memberRepository.findAll()).thenReturn(Arrays.asList());

        assertThrows(MemberNotFoundException.class, () -> memberService.getAllMembers());
    }

    @Test
    void testGetAllMembers_ShouldReturnMembers_WhenMembersExist() {
        when(memberRepository.findAll()).thenReturn(Arrays.asList(member));

        var members = memberService.getAllMembers();

        assertNotNull(members);
        assertEquals(1, members.size());
        assertEquals("John Doe", members.get(0).getMemberName());
    }

    @Test
    void testGetMemberById_ShouldThrowMemberNotFoundException_WhenMemberNotFound() {
        when(memberRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(MemberNotFoundException.class, () -> memberService.getMemberById(1L));
    }

    @Test
    void testGetMemberById_ShouldReturnMember_WhenMemberFound() {
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        Member foundMember = memberService.getMemberById(1L);

        assertNotNull(foundMember);
        assertEquals("John Doe", foundMember.getMemberName());
    }

    @Test
    void testGetMemberByName_ShouldThrowMemberNotFoundException_WhenNoMembersFound() {
        when(memberRepository.findByMemberName("John Doe")).thenReturn(Arrays.asList());

        assertThrows(MemberNotFoundException.class, () -> memberService.getMemberByName("John Doe"));
    }

    @Test
    void testGetMemberByName_ShouldReturnMembers_WhenMembersExist() {
        when(memberRepository.findByMemberName("John Doe")).thenReturn(Arrays.asList(member));

        var members = memberService.getMemberByName("John Doe");

        assertNotNull(members);
        assertEquals(1, members.size());
    }

    @Test
    void testGetMemberByPhoneNumber_ShouldThrowMemberNotFoundException_WhenMemberNotFound() {
        when(memberRepository.findByMemberPhoneNumber("123-456-7890")).thenReturn(Optional.empty());

        assertThrows(MemberNotFoundException.class, () -> memberService.getMemberByPhoneNumber("123-456-7890"));
    }

    @Test
    void testGetMemberByPhoneNumber_ShouldReturnMember_WhenMemberFound() {
        when(memberRepository.findByMemberPhoneNumber("123-456-7890")).thenReturn(Optional.of(member));

        Member foundMember = memberService.getMemberByPhoneNumber("123-456-7890");

        assertNotNull(foundMember);
        assertEquals("John Doe", foundMember.getMemberName());
    }

    @Test
    void testCreateMember_ShouldReturnSavedMember() {
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        Member savedMember = memberService.createMember(member);

        assertNotNull(savedMember);
        assertEquals("John Doe", savedMember.getMemberName());
    }
}
