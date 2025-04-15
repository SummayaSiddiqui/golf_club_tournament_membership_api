package com.keyin.rest.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Autowired
    private MemberController memberController;

    @Autowired
    private ObjectMapper objectMapper;

    private Member member1;

    @BeforeEach
    void setUp() {
        // Create a sample member for testing
        member1 = new Member();
        member1.setId(1L);
        member1.setMemberName("John Doe");
        member1.setMemberAddress("123 Main St");
        member1.setMemberEmailAddress("john.doe@email.com");
        member1.setMemberPhoneNumber("123-456-7890");
        member1.setMemberStartDate(LocalDate.now());
    }

    @Test
    void testGetAllMembers() throws Exception {
        List<Member> members = Arrays.asList(member1);
        when(memberService.getAllMembers()).thenReturn(members);

        mockMvc.perform(get("/api/members/allMembers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].memberName").value("John Doe"))
                .andExpect(jsonPath("$[0].memberAddress").value("123 Main St"))
                .andExpect(jsonPath("$[0].memberEmailAddress").value("john.doe@email.com"));
    }

    @Test
    void testGetMemberById() throws Exception {
        when(memberService.getMemberById(1L)).thenReturn(member1);

        mockMvc.perform(get("/api/members/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberName").value("John Doe"))
                .andExpect(jsonPath("$.memberAddress").value("123 Main St"))
                .andExpect(jsonPath("$.memberEmailAddress").value("john.doe@email.com"));
    }

    @Test
    void testGetMemberByEmailAddress() throws Exception {
        when(memberService.getMemberByEmailAddress("john.doe@email.com")).thenReturn(member1);

        mockMvc.perform(get("/api/members/getMemberByEmailAddress/{emailAddress}", "john.doe@email.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberName").value("John Doe"))
                .andExpect(jsonPath("$.memberEmailAddress").value("john.doe@email.com"));
    }

    @Test
    void testGetMemberByStartDate() throws Exception {
        List<Member> members = Arrays.asList(member1);
        when(memberService.getMemberByStartDate(LocalDate.now())).thenReturn(members);

        mockMvc.perform(get("/api/members/getMemberByStartDate")
                        .param("startDate", LocalDate.now().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].memberName").value("John Doe"));
    }

    @Test
    void testGetMemberByPhoneNumber() throws Exception {
        when(memberService.getMemberByPhoneNumber("123-456-7890")).thenReturn(member1);

        mockMvc.perform(get("/api/members/getMemberByPhoneNumber/{phoneNumber}", "123-456-7890"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberName").value("John Doe"));
    }

    @Test
    void testGetMemberByName() throws Exception {
        List<Member> members = Arrays.asList(member1);
        when(memberService.getMemberByName("John Doe")).thenReturn(members);

        mockMvc.perform(get("/api/members/name/{name}", "John Doe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].memberName").value("John Doe"));
    }
}
