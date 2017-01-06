package com.acnebs.posts.functionaldao.functional8;
/**
 * Class User.
 * <p>
 * Created by andreas.czakaj on 04.03.2016
 *
 * @author andreas.czakaj
 */
class User {

    public User(final String firstName,
                final String lastName,
                final String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User() {
        // needed for jackson
    }


    private String firstName;

    public String getFirstName() {
        return firstName;
    }


    private String lastName;

    public String getLastName() {
        return lastName;
    }


    private String email;

    public String getEmail() {
        return email;
    }

}
