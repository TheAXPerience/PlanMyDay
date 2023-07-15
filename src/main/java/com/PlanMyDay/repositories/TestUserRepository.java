package com.PlanMyDay.repositories;

import com.PlanMyDay.models.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestUserRepository extends JpaRepository<TestUser, Long> {
	TestUser findByUsername(String username);
	TestUser findByEmail(String email);
}
