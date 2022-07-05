package com.ocrms.ocrmsbca.components;

import com.ocrms.ocrmsbca.Enum.ERole;
import com.ocrms.ocrmsbca.entity.admin.Admin;
import com.ocrms.ocrmsbca.entity.user.User;

/**
 * @author CHHATRA SAUD
 * @product IntelliJ IDEA
 * @project ocrms
 * @Date 2022-04-06
 */
public class AuthorizeUser {
    private static User user;
    private static Admin admin;
    private static ERole eRole;
    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        AuthorizeUser.user = user;
        AuthorizeUser.eRole=eRole.ROLE_USER;

    }

    public static Admin getAdmin() {
        return admin;
    }

    public static void setAdmin(Admin admin) {
        AuthorizeUser.admin = admin;
        AuthorizeUser.eRole=eRole.ROLE_ADMIN;
    }

    public static ERole getUserStatus() {
        return eRole;
    }

    public static void setUserStatus(ERole eRole) {
        AuthorizeUser.eRole = eRole;
    }

}
