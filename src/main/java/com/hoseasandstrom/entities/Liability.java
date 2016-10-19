package com.hoseasandstrom.entities;

import javax.persistence.*;

/**
 * Created by hoseasandstrom on 10/19/16.
 */
@Entity
@Table(name = "liabilities")
public class Liability {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String identifier;

    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String lastName;

    @Column(nullable = false)
    boolean isAccountedFor;

    @ManyToOne
    User users;

    public Liability() {
    }

    public Liability(String identifier, String firstName, String lastName) {
        this.identifier = identifier;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Liability(String identifier, String firstName, String lastName, boolean isAccountedFor) {
        this.identifier = identifier;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAccountedFor = isAccountedFor;
    }


    public Liability(String identifier, String firstName, String lastName, boolean isAccountedFor, User users) {
        this.identifier = identifier;
        this.firstName = firstName;
        this.lastName = lastName;

        this.isAccountedFor = isAccountedFor;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAccountedFor() {
        return isAccountedFor;
    }

    public void setAccountedFor(boolean accountedFor) {
        isAccountedFor = accountedFor;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
