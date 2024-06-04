package com.muslimdev.dars.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public interface ApplicationUserDao{

     Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
