package com.example.demo.configuration;

import java.util.Set;

import static com.example.demo.configuration.ApplicationUserPermission.*;

public enum ApplicationUserRole {
   STUDENT(Set.of()),
   ADMIN(Set.of(COURSE_WRITE,COURSE_READ,STUDENT_WRITE,STUDENT_READ)),
   ADMINTRAINEE(Set.of(COURSE_READ,STUDENT_READ));

   private final Set permissions;

   ApplicationUserRole(Set permissions) {
      this.permissions = permissions;
   }
}
