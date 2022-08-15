package kz.meiir.topjava;

import kz.meiir.topjava.model.Role;
import kz.meiir.topjava.model.User;

/**
 * @author Meiir Akhmetov on 15.08.2022
 */
public class UserTestData {
    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);
}
