package com.example.demo.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

import static com.example.demo.configuration.ApplicationUserRole.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements  ApplicationUserDao{

    private final PasswordEncoder encoder;

    public FakeApplicationUserDaoService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUserName(String userName) {

        for(ApplicationUser applicationUser: getApplicationUsers()){

            String name = applicationUser.getUsername();

            if (name.equals(userName))
                return Optional.of(applicationUser);
        }
        return  null;

    }

    private Set<ApplicationUser> getApplicationUsers() {
        Set<ApplicationUser> applicationUsers = Set.of(
                new ApplicationUser(STUDENT.getGrantedAuthorities()
                        ,encoder.encode("password")
                        ,"uday",
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser(ADMIN.getGrantedAuthorities()
                        ,encoder.encode("password")
                        ,"hima",
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser(ADMINTRAINEE.getGrantedAuthorities()
                        ,encoder.encode("password")
                        ,"tom",
                        true,
                        true,
                        true,
                        true));

        return applicationUsers;

    }
}
