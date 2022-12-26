package com.nhnacademy.jdbc.board.repository;

import com.nhnacademy.jdbc.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}
