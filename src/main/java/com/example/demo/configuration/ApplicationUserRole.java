package com.example.demo.configuration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.configuration.ApplicationUserPermission.*;

public enum ApplicationUserRole {
   STUDENT(Set.of()),
   ADMIN(Set.of(COURSE_WRITE,COURSE_READ,STUDENT_WRITE,STUDENT_READ)),
   ADMINTRAINEE(Set.of(COURSE_READ,STUDENT_READ));

   private final Set permissions;

   ApplicationUserRole(Set permissions) {
      this.permissions = permissions;
   }

   private Set<ApplicationUserPermission> getPermissions() {
      return permissions;
   }


   public Set<GrantedAuthority> getGrantedAuthorities() {

      Set<GrantedAuthority> grantedAuthorities = getPermissions()
                                                     .stream()
                                                     .map(permission->new SimpleGrantedAuthority(permission.name()))
                                                     .collect(Collectors.toSet());
      grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));

      return grantedAuthorities;

   }


}
