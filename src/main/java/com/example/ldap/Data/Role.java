package com.example.ldap.Data;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name="role_name")
    private String role_name;

    public int getId() { return id;}

    public String getRole_name() {
        return role_name;
    }
    public void setRole_name(String email) {
        this.role_name = role_name;
    }
}
