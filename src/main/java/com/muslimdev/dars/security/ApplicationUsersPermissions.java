package com.muslimdev.dars.security;

public enum ApplicationUsersPermissions {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSES_READ("courses:read"),
    COURSES_WRITE("courses_write");



    private final String permission;

    ApplicationUsersPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
