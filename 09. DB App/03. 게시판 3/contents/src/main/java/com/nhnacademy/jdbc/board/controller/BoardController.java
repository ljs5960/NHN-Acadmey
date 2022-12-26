package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.service.BoardServiceImpl;
import com.nhnacademy.jdbc.board.entity.Comment;
import com.nhnacademy.jdbc.board.entity.Post;
import com.nhnacademy.jdbc.board.service.BoardService;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardServiceImpl boardService) {
        this.boardService = boardService;
    }

    String LOGIN_SESSION_KEY = "LOGIN_SESSION";

    /*
    게시판 리스트
     */
    @GetMapping()
    public String getPosts(HttpSession session, ModelMap modelMap) {
        List<Post> posts = boardService.getPosts();
        modelMap.put("posts", posts);

        return "post/postList";
    }

    /*
    게시글 상세보기
     */
    @GetMapping("/{postId}")
    public String getPost(@PathVariable("postId") Long postId, HttpSession session, ModelMap modelMap) {
        Post post = boardService.getPostById(postId);
        List<Comment> comments = boardService.getCommentByPostId(postId);

        modelMap.addAttribute("post", post);
        modelMap.addAttribute("comments", comments);

        return "post/postView";
    }

    /*
    글 작성하기 Form
     */
    @GetMapping("/write")
    public String insertPost(HttpSession session) {
        if (Objects.nonNull(session.getAttribute(LOGIN_SESSION_KEY))) {
            return "post/postForm";
        }
        return "redirect:/login";
    }

    /*
    글 작성하기
     */
    @PostMapping("/write")
    public String insertPost(@RequestParam("title") String title,
                             @RequestParam("content") String content,
                             HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute(LOGIN_SESSION_KEY);

        Post post = new Post(title, content, userName);
        boardService.insertPost(post);

        return "redirect:/board";
    }

    /*
    게시글 수정하기 Form
     */
    @GetMapping("/update/{postId}")
    public String updatePost(@PathVariable("postId") Long postId, HttpSession session, ModelMap modelMap) {
        Post post = boardService.getPostById(postId);
        String writerName = post.getUserName();
        String userName = (String) session.getAttribute(LOGIN_SESSION_KEY);

        modelMap.addAttribute("post", post);

        // 관리자 혹은 작성자만 updateForm 반환
        if ((Objects.equals(writerName, userName)) || (Objects.equals("admin", userName))) {
            return "post/postUpdateForm";
        } else {
            return "redirect:/board/{postId}";
        }
    }

    /*
    게시글 수정하기
     */
    @PostMapping("/update/{postId}")
    public String updatePost(@RequestParam("title") String title,
                             @RequestParam("content") String content,
                             @PathVariable("postId") Long postId,
                             HttpSession session) {
        String userName = (String) session.getAttribute(LOGIN_SESSION_KEY);

        Post post = new Post(postId, title, content, userName);

        boardService.updatePost(post);

        return "redirect:/board/{postId}";
    }

    /*
    게시글 삭제하기
     */
    @GetMapping("/delete/{postId}")
    public String deletePost(@PathVariable("postId") Long postId, HttpSession session) {
        String writerName = boardService.getPostById(postId).getUserName();
        String userName = (String) session.getAttribute(LOGIN_SESSION_KEY);

        // 관리자 혹은 작성자만 deletePost() 가능
        if ((Objects.equals(writerName, userName)) || (Objects.equals("admin", userName))) {
            boardService.deletePost(postId);
            return "redirect:/board";
        } else {
            return "redirect:/board/{postId}";
        }
    }

    /*
    댓글 입력하기
     */
    @PostMapping("/comment/{postId}")
    public String insertComment(@RequestParam("content")String content,
                                @PathVariable("postId")Long postId,
                                HttpSession session) {
        String userName = (String) session.getAttribute(LOGIN_SESSION_KEY);

        if (Objects.nonNull(userName)) { // 로그인 한 경우에만 insertComment() 호출 가능
            Comment comment = new Comment(content, userName, postId);
            boardService.insertComment(comment);
        }

        return "redirect:/board/{postId}";
    }

    /*
    삭제 게시판(관리자 전용)
     */
    @GetMapping("/deleted")
    public String getDeletedPosts(HttpSession session, ModelMap modelMap) {
        String userName = (String) session.getAttribute(LOGIN_SESSION_KEY);

        if (Objects.equals(userName, "admin")) {    // 관리자일시 접속가능
            List<Post> posts = boardService.getDeletedPosts();
            modelMap.addAttribute("posts", posts);

            return "post/deletedPostList";
        }

        return "redirect:/board";
    }

    /*
    삭제된 게시글 복구하기
     */
    @GetMapping("/deleted/restore/{postId}")
    public String restoreDeletedPost(@PathVariable("postId") Long postId,
                                     HttpSession session) {
        String userName = (String) session.getAttribute(LOGIN_SESSION_KEY);

        if (Objects.equals(userName, "admin")) {
            boardService.restorePost(postId);
        }

        return "redirect:/board/deleted";
    }
}
