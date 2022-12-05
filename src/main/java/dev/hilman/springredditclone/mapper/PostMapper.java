package dev.hilman.springredditclone.mapper;

import dev.hilman.springredditclone.dto.PostRequest;
import dev.hilman.springredditclone.dto.PostResponse;
import dev.hilman.springredditclone.model.Post;
import dev.hilman.springredditclone.model.Subreddit;
import dev.hilman.springredditclone.model.User;
import dev.hilman.springredditclone.repository.CommentRepository;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.marlonlom.utilities.timeago.TimeAgo;



@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;

    
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source ="user")
    public abstract Post map(PostRequest postRequest, Subreddit subreddit, User user);

   
    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    // @Mapping(target = "duration", expression = "java(getDuration(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    // String getDuration(Post post) {
    //     return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    // }
}
