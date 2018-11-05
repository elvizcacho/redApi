package com.redapi.post;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void gettingEmptyArray() throws Exception {
        Post[] posts = new Post[] {};
        Iterable<Post> expected = Arrays.asList(posts);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/posts/", Iterable.class)).isEqualTo(expected);
    }

    @Test
    public void insertOneEntry() throws Exception {
        Post post = new Post();
        post.setUpvotes(1000);
        post.setAuthor("Dummy Author");
        post.setCategory("Dummy category");
        post.setSubreddit("Dummy subreddit");
        post.setDate(new Date());
        post.setTitle("Dummy title");
        Post createdPost = this.restTemplate.postForObject("http://localhost:" + port + "/posts/", post, Post.class);
        assertThat(createdPost.getUpvotes()).isEqualTo(post.getUpvotes());
        assertThat(createdPost.getAuthor()).isEqualTo(post.getAuthor());
        assertThat(createdPost.getCategory()).isEqualTo(post.getCategory());
        assertThat(createdPost.getSubreddit()).isEqualTo(post.getSubreddit());
        assertThat(createdPost.getTitle()).isEqualTo(post.getTitle());
        assertThat(createdPost.getDate()).isEqualTo(post.getDate());
    }

    @Test
    public void getPostById() throws Exception {
        Post post = new Post();
        post.setUpvotes(1000);
        post.setAuthor("Dummy Author");
        post.setCategory("Dummy category");
        post.setSubreddit("Dummy subreddit");
        post.setDate(new Date());
        post.setTitle("Dummy title");
        Post createdPost = this.restTemplate.postForObject("http://localhost:" + port + "/posts/", post, Post.class);
        Post requestedPost = this.restTemplate.getForObject("http://localhost:" + port + "/posts/" + createdPost.getId(), Post.class);
        assertThat(requestedPost.getTitle()).isEqualTo(createdPost.getTitle());
    }


    @Test
    public void updateOneEntry() throws Exception {
        Post post = new Post();
        post.setUpvotes(1000);
        post.setAuthor("Dummy Author");
        post.setCategory("Dummy category");
        post.setSubreddit("Dummy subreddit");
        post.setDate(new Date());
        post.setTitle("Dummy title");
        Post createdPost = this.restTemplate.postForObject("http://localhost:" + port + "/posts/", post, Post.class);
        createdPost.setTitle("New dummy title");
        this.restTemplate.put("http://localhost:" + port + "/posts/" + createdPost.getId(), post, Post.class);
        Post updatedPost = this.restTemplate.getForObject("http://localhost:" + port + "/posts/" + createdPost.getId(), Post.class);
        assertThat(updatedPost.getTitle()).isEqualTo("New dummy title");
    }


}
