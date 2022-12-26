package com.nhnacademy.jdbc.board.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comment {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String userName;
    private Long postId;

    public Comment(String content, String userName, Long postId) {
        this.content = content;
        this.userName = userName;
        this.postId = postId;
    }
}

