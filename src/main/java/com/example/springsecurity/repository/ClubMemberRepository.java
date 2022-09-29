package com.example.springsecurity.repository;

import com.example.springsecurity.entity.ClubMember;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClubMemberRepository extends JpaRepository<ClubMember, String> {

    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select clubmember from ClubMember clubmember where clubmember.fromSocial = :social and clubmember.email =:email")
    Optional<ClubMember> findByEmail(@Param("email") String email, @Param("social") boolean social);

}
