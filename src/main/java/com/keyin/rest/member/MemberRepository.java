package com.keyin.rest.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberName(String memberName);

    List<Member> findByMemberAddress(String memberAddress);

    Optional<Member> findByMemberEmailAddress(String memberEmailAddress);

    Optional<Member> findByMemberPhoneNumber(String memberPhoneNumber);

    List<Member> findByMemberStartDate(LocalDate startDate);

}
