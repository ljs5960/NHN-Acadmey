package com.nhnacademy.jdbc.board.mapper;

import com.nhnacademy.jdbc.board.entity.Comment;
import com.nhnacademy.jdbc.board.entity.Post;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    List<Post> selectPosts();

    Post selectPost(Long postId);

    void insertPost(Post post);

    void updatePost(Post post);

    void deletePost(Long postId);

    void insertComment(Comment comment);

    List<Comment> selectComment(Long postId);

    List<Post> selectedDeletedPosts();

    void restorePost(Long postId);
}
