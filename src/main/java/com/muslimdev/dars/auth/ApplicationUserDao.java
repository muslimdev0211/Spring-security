package com.muslimdev.dars.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationUserDao extends JpaRepository {

      Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
