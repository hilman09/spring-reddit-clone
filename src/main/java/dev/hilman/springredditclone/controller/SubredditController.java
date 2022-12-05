package dev.hilman.springredditclone.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.hilman.springredditclone.dto.SubredditDto;
import dev.hilman.springredditclone.services.SubredditService;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
public class SubredditController {

    private final SubredditService subredditService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubredditDto createSubreddit(@RequestBody SubredditDto subredditDto) {
        return subredditService.save(subredditDto);
    }

    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAllSubreddits() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditService.getAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubredditDto getSubreddit(@PathVariable Long id){
        return subredditService.getSubreddit(id);
    }
}
