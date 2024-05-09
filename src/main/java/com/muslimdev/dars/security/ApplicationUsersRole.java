package com.muslimdev.dars.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUsersRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(ApplicationUsersPermissions.COURSES_READ, ApplicationUsersPermissions.STUDENT_WRITE,
            ApplicationUsersPermissions.STUDENT_READ, ApplicationUsersPermissions.COURSES_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(ApplicationUsersPermissions.COURSES_READ,
            ApplicationUsersPermissions.STUDENT_READ));




    private final Set<ApplicationUsersPermissions> permissions;

    ApplicationUsersRole(Set<ApplicationUsersPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUsersPermissions> getPermissions() {
        return permissions;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" +  this.name()));

        return permissions;
    }
}
