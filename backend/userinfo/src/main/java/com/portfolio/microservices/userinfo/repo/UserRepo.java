package com.portfolio.microservices.userinfo.repo;

import com.portfolio.microservices.userinfo.dto.UserDto;
import com.portfolio.microservices.userinfo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
}
