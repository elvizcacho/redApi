package com.redapi.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;


@Controller
@RequestMapping(path="/posts")
public class PostController {
    @Autowired

    private PostRepository postRepository;

    @GetMapping(path="/")
    public @ResponseBody Iterable<Post> getPosts() {
        return postRepository.findAll();
    }

    @RequestMapping(path="/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Post createPost(@RequestBody Post post) {
        postRepository.save(post);
        return post;
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Post updatePost(@PathVariable Integer id) {
        return postRepository.findById(id).get();
    }


    @RequestMapping(path="/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Post updatePost(@RequestBody Post post, @PathVariable Integer id) {
        System.out.println("Are you here baby??");
        System.out.println(id);
        Post oldPost = postRepository.findById(id).get();
        System.out.println(oldPost);
        oldPost.setTitle(post.getTitle());
        oldPost.setUpvotes(post.getUpvotes());
        oldPost.setAuthor(post.getAuthor());
        oldPost.setSubreddit(post.getSubreddit());
        oldPost.setDate(post.getDate());
        oldPost.setCategory(post.getCategory());
        oldPost.setContent(post.getContent());
        postRepository.save(oldPost);
        return oldPost;
    }



}
