package dev.hilman.springredditclone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hilman.springredditclone.model.Post;
import dev.hilman.springredditclone.model.Subreddit;
import dev.hilman.springredditclone.model.User;

public interface PostRepository extends JpaRepository<Post, Long> {
   List<Post> findAllBySubreddit(Subreddit subreddit);
   List<Post> findByUser(User user);
}
