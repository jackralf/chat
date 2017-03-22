package com.machenfei.chat.model;

/**
 * Created by machenfei on 2017/3/22.
 */

public class UserManager {

    private static UserManager manager;
    public static UserManager getInstance() {
        if (manager == null) {
            manager = new UserManager();
            manager.init();
        }
        return manager;
    }

    private void init() {

    }

    public User getUser(String uid) {
        User user = new User();
        user.name = "jack";
        user.level = 10;
        user.uid = uid;
        return user;
    }
}
