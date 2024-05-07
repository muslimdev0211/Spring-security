package com.muslimdev.dars.security;

import com.google.common.collect.Sets;

import java.util.Set;

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
}
