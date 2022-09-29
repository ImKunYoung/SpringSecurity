package com.example.springsecurity.repository;

import com.example.springsecurity.entity.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubMemberRepository extends JpaRepository<ClubMember, String> {

}
