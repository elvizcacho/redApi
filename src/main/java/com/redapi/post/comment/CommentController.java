package com.redapi.post.comment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.redapi.post.PostRepository;
import com.redapi.post.Post;

import java.util.List;

@Controller
@RequestMapping(path = "/posts/{postId}/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(path = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Comment createComment(@PathVariable("postId") int postId, @RequestBody Comment comment) {
        Post post = postRepository.findById(postId).get();
        comment.setPost(post);
        commentRepository.save(comment);
        return comment;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public @ResponseBody List<Comment> getCommentsByPostId(@PathVariable("postId") int postId) {
        return commentRepository.findByPostIdAndParentCommentNull(postId);
    }

    @RequestMapping(path = "/{commentId}/comments", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Comment getCommentsByPostId(@PathVariable("postId") int postId, @PathVariable("commentId") int commentId, @RequestBody Comment comment) {
        Post post = postRepository.findById(postId).get();
        Comment parentComment = commentRepository.findById(commentId).get();
        comment.setParentComment(parentComment);
        comment.setPost(post);
        commentRepository.save(comment);
        return comment;
    }

    @RequestMapping(path = "/{commentId}/comments", method = RequestMethod.GET)
    public @ResponseBody List<Comment> getCommentsByPostId(@PathVariable("postId") int postId, @PathVariable("commentId") int commentId) {
        return commentRepository.findByParentCommentId(commentId);
    }
}
