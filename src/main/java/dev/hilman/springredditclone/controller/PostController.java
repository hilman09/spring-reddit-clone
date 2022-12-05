package dev.hilman.springredditclone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hilman.springredditclone.dto.PostRequest;
import dev.hilman.springredditclone.dto.PostResponse;
import dev.hilman.springredditclone.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
    }

    @GetMapping("/get-post")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @GetMapping("/by-subreddit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getPostsBySubreddit(Long id) {
        return postService.getPostsBySubreddit(id);
    }

    @GetMapping("/by-user/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getPostsByUsername(String username) {
        return postService.getPostsByUsername(username);
    }
}