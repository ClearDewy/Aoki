package com.cleardewy.aoki.constant;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
public enum RoleConstans {

    ROOT("root"),
    TEACHER("teacher"),

    STUDENT("student");

    private final String role;

    RoleConstans(String role) {
        this.role = role;
    }
}
