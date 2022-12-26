package com.nhnacademy.jdbc.board.service;

import com.nhnacademy.jdbc.board.domain.Comment;
import com.nhnacademy.jdbc.board.domain.Post;
import java.util.List;

public interface BoardService {
    List<Post> getPosts();

    Post getPostById(Long postId);

    void insertPost(Post post);

    void updatePost(Post post);

    void deletePost(Long postId);

    void insertComment(Comment comment);

    List<Comment> getCommentByPostId(Long postId);

    List<Post> getDeletedPosts();

    void restorePost(Long postId);
}
