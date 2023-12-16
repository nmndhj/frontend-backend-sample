package com.example.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.boot.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{
	
	Member findByEmailAndPwd(String email, String password);

}
