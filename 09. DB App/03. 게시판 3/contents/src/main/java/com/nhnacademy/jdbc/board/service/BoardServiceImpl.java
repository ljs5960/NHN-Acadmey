package com.nhnacademy.jdbc.board.service;

import com.nhnacademy.jdbc.board.entity.Comment;
import com.nhnacademy.jdbc.board.entity.Post;
import com.nhnacademy.jdbc.board.mapper.BoardMapper;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardMapper boardMapper;

    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public List<Post> getPosts() {
        return boardMapper.selectPosts();
    }

    @Override
    public Post getPostById(Long postId) {
        return boardMapper.selectPost(postId);
    }

    @Override
    public void insertPost(Post post) {
        boardMapper.insertPost(post);
    }

    @Override
    public void updatePost(Post post) {
        boardMapper.updatePost(post);
    }

    @Override
    public void deletePost(Long postId) {
        boardMapper.deletePost(postId);
    }

    @Override
    public void insertComment(Comment comment) {
        boardMapper.insertComment(comment);
    }

    @Override
    public List<Comment> getCommentByPostId(Long postId) {
        return boardMapper.selectComment(postId);
    }

    @Override
    public List<Post> getDeletedPosts() {
        return boardMapper.selectedDeletedPosts();
    }

    @Override
    public void restorePost(Long postId) {
        boardMapper.restorePost(postId);
    }
}
