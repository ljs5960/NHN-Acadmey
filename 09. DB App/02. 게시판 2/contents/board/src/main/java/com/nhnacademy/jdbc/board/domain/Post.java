package com.nhnacademy.jdbc.board.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Post {
    private Long id;
    private String title;
    private String content;
    private Long fileId;
    private String userName;
    private String modifierName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Boolean isDeleted;
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
