package br.com.facility.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "USER")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "USER_NAME", unique = true)
    @JsonIgnore
    private String userName;

    @Column(name = "PASSWORD")
    @JsonIgnore
    private String password;

    @Column(name = "EMAIL")
    private String email;

    public User() {
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
