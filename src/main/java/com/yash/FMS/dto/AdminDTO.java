package com.yash.FMS.dto;

/**
 * Data Transfer Object for Admin.
 */
public class AdminDTO {
    private int adminId;
    private String username;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
