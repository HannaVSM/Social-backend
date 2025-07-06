package com.social.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "AMIGO")
public class Amigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "consecuser", nullable = false, length = 5)
    private String consecUser;

    @Column(name = "consecuser2", nullable = false, length = 5)
    private String consecUser2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsecUser() {
        return consecUser;
    }

    public void setConsecUser(String consecUser) {
        this.consecUser = consecUser;
    }

    public String getConsecUser2() {
        return consecUser2;
    }

    public void setConsecUser2(String consecUser2) {
        this.consecUser2 = consecUser2;
    }
}
