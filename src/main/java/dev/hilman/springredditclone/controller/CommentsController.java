package dev.hilman.springredditclone.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import dev.hilman.springredditclone.dto.CommentsDto;
import dev.hilman.springredditclone.services.CommentService;

import java.util.List;


@RestController
@RequestMapping("/api/comments/")
@AllArgsConstructor
public class CommentsController {
    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@RequestBody CommentsDto commentsDto) {
        commentService.save(commentsDto);
    }

    @GetMapping("/by-post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentsDto> getAllCommentsForPost(@PathVariable Long postId) {
        return commentService.getAllCommentsForPost(postId);
    }

    @GetMapping("/by-user/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentsDto> getAllCommentsForUser(@PathVariable String userName){
        return commentService.getAllCommentsForUser(userName);
    }

}