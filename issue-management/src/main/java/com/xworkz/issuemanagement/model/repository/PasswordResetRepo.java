package com.xworkz.issuemanagement.model.repository;

public interface PasswordResetRepo {

    boolean emailExists(String email);

    boolean verifyOldPassword(String email, String oldPassword);

    void updatePassword(String email, String newPassword);
}
