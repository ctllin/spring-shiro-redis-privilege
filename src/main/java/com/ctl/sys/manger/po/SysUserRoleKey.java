package com.ctl.sys.manger.po;

import java.io.Serializable;

public class SysUserRoleKey implements Serializable {
    private Integer user_id;

    private Integer role_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }
}