package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author Zimi Li
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "feeds")
public class Feed {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Integer id;

    @Column(name = "feed_content", nullable = false)
    private String content;

    @Column(name = "feed_image_url")
    private String imageUrl;

    @Column(name = "feed_video_url")
    private String videoUrl;

    @OneToMany(mappedBy = "belongTo")
    private List<Comment> comments;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<User> likes;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User author;

    @Transient
    private Integer likesResponse;

    @Transient
    private UserResponse authorResponse;
}
