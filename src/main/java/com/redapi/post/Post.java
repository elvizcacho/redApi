package com.redapi.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.redapi.post.comment.Comment;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private Integer upvotes;
    private String title;
    private String category;
    private String author;
    private String subreddit;
    private String content;

    @Temporal(TemporalType.DATE)
    private Date date;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUpvotes() {
        return this.upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //public List<Comment> getComments() { return comments; }

    //public void setComments(List<Comment> comments) { this.comments = comments; }
}
