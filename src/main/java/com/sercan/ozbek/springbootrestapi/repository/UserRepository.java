package com.sercan.ozbek.springbootrestapi.repository;

import com.sercan.ozbek.springbootrestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {
    User findByFirstName(String firstName);
}
