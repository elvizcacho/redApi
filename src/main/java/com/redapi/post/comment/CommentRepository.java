package com.redapi.post.comment;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer>  {
    List<Comment> findByPostId(int postId);
    List<Comment> findByParentCommentId(int commentId);
    List<Comment> findByPostIdAndParentCommentNull(int postId);
}

