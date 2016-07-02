package com.madiha.serverlogin;

/**
 * Created by mz shah on 07-May-16.
 */
public class Contact {
    String name,email,username,password;
    public Contact(String name,String username,String password,String email){
        this.name=name;
        this.email=email;
        this.password=password;
        this.username=username;
    }
    public Contact(String username,String password){
        this.username=username;
        this.password=password;
    }
}
