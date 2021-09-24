package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author Zimi Li
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_username", unique = true, nullable = false)
    private String username;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_profileImage")
    private String profileImage;

    @Column(name = "user_last_notification")
    private Long lastNotification;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Notification> notifications;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Feed> feeds;

    public User(Integer id, String username, String password, String name, String email, String profileImage) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
    }
}
