package com.nhnacademy.jdbc.board.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "user_id")
    private String userName;

    @Column(name = "modifier_id")
    private String modifierName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "parent_post_id")
    private Long parentPostId;
    private int commentCount;

    public Post(String title, String content, String userName) {
        this.title = title;
        this.content = content;
        this.userName = userName;
    }

    public Post(Long id, String title, String content, String modifierName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.modifierName = modifierName;
    }

    public Post(Long id, String title, String userName, LocalDateTime createdAt, int commentCount) {
        this.id = id;
        this.title = title;
        this.userName = userName;
        this.createdAt = createdAt;
        this.commentCount = commentCount;
    }

    public Post(Long id, String title, String content, Long fileId, String userName,
                String modifierName, LocalDateTime createdAt, LocalDateTime modifiedAt,
                Boolean isDeleted, Long parentPostId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.fileId = fileId;
        this.userName = userName;
        this.modifierName = modifierName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.isDeleted = isDeleted;
        this.parentPostId = parentPostId;
    }
}
