package com.PlanMyDay.repositories;

import com.PlanMyDay.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {
	Account findByUsername(String username);
	Account findByEmail(String email);
}
