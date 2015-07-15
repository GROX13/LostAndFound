package com.steps.lostfound.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * TODO
 * <p/>
 * Created by Giorgi on 7/15/2015.
 */
@ParseClassName("User")
public class User extends ParseObject {


    public void setEmail(String email) {
        put("email", email);
    }

    public String getEmail() {
        return getString("email");
    }

    public void setPhone(String phone) {
        put("phone", phone);
    }

    public String getPhone() {
        return getString("phone");
    }

    public void setName(String name) {
        put("name", name);
    }

    public String getName() {
        return getString("name");
    }

    public static ParseQuery<User> getQuery() {
        return ParseQuery.getQuery(User.class);
    }

}
