package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Zimi Li
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer id;

    @Column(name = "comment_content")
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User author;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Feed belongTo;

    @Transient
    private UserResponse authorResponse;
}
