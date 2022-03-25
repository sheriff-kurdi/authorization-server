package com.kurdi.authorizationserver.services;


import com.kurdi.authorizationserver.entities.Authority;
import com.kurdi.authorizationserver.entities.IdentityUser;
import com.kurdi.authorizationserver.repositories.IdentityUsersRepository;
import com.kurdi.authorizationserver.requests.UserNameAndPasswordAuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UsersAuthService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    IdentityUsersRepository usersRepository;

    public IdentityUser register(UserNameAndPasswordAuthenticationRequest authenticationRequest) {
        Set<Authority> authorities = new HashSet<>();

        IdentityUser user = IdentityUser.builder()
                .userName(authenticationRequest.getUsername())
                .password(passwordEncoder.encode(authenticationRequest.getPassword()))
                .authorities(authorities)
                .build();



        if (usersRepository.findUserByUserName(user.getUserName()).isEmpty()) {
            usersRepository.save(user);
            //TODO when creating user make an app event to create his cart instead of making it here.
            usersRepository.save(user);

        }

        return user;
    }
}