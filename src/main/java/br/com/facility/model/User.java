package br.com.facility.model;

import br.com.facility.json.UserJson;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    @NotNull
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "USER_NAME", unique = true)
    @NotNull
    private String userName;

    @Column(name = "PASSWORD")
    @NotNull
    private String password;

    @Column(name = "EMAIL")
    private String email;

    public User() {
    }

    public User(UserJson user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getName();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }

    public User(String name, String lastName, String userName, String password, String email) {
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
