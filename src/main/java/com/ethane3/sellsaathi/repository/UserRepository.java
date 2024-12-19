package com.ethane3.sellsaathi.repository;

import com.ethane3.sellsaathi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
     User findByEmail(String email);
}
