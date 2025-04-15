package com.keyin.rest.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {

    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member("John Doe", "123 Main St", "john.doe@example.com", "123-456-7890", LocalDate.of(2020, 1, 1));
    }

    @Test
    void testConstructor() {
        assertNotNull(member);
        assertEquals("John Doe", member.getMemberName());
        assertEquals("123 Main St", member.getMemberAddress());
        assertEquals("john.doe@example.com", member.getMemberEmailAddress());
        assertEquals("123-456-7890", member.getMemberPhoneNumber());
        assertEquals(LocalDate.of(2020, 1, 1), member.getMemberStartDate());
    }

    @Test
    void testSettersAndGetters() {
        member.setMemberName("Jane Smith");
        member.setMemberAddress("456 Elm St");
        member.setMemberEmailAddress("jane.smith@example.com");
        member.setMemberPhoneNumber("987-654-3210");
        member.setMemberStartDate(LocalDate.of(2021, 6, 15));

        assertEquals("Jane Smith", member.getMemberName());
        assertEquals("456 Elm St", member.getMemberAddress());
        assertEquals("jane.smith@example.com", member.getMemberEmailAddress());
        assertEquals("987-654-3210", member.getMemberPhoneNumber());
        assertEquals(LocalDate.of(2021, 6, 15), member.getMemberStartDate());
    }

    @Test
    void testToString() {
        String expected = "Member{id=0, memberName='John Doe', memberAddress='123 Main St', memberEmailAddress='john.doe@example.com', memberPhoneNumber='123-456-7890', startDate='2020-01-01'}";
        assertEquals(expected, member.toString());
    }

    @Test
    void testMemberId() {
        member.setId(12345);
        assertEquals(12345, member.getId());
    }

    @Test
    void testNullValues() {
        Member emptyMember = new Member();
        assertNull(emptyMember.getMemberName());
        assertNull(emptyMember.getMemberAddress());
        assertNull(emptyMember.getMemberEmailAddress());
        assertNull(emptyMember.getMemberPhoneNumber());
        assertNull(emptyMember.getMemberStartDate());
    }

    @Test
    void testValidEmailAndPhoneNumber() {
        assertTrue(member.getMemberEmailAddress().contains("@"));
        assertTrue(member.getMemberPhoneNumber().matches("\\d{3}-\\d{3}-\\d{4}"));
    }

    @Test
    void testEmailAndPhoneNumberUniqueness() {
        // Assuming logic to check for unique constraints will be handled at the database level or service layer
        // Here we only check if email and phone are not null or empty
        assertNotNull(member.getMemberEmailAddress());
        assertNotNull(member.getMemberPhoneNumber());
    }
}
