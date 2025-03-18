package com.keyin.rest.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByName(String memberName);

    List<Member> findMemberByAddress(String memberAddress);

    Optional<Member> findMemberByEmailAddress(String memberEmailAddress);

    Optional<Member> findMemberByPhoneNumber(String memberPhoneNumber);

    List<Member> findMemberByStartDate(LocalDate startDate);

}
