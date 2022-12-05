package dev.hilman.springredditclone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hilman.springredditclone.model.Comment;
import dev.hilman.springredditclone.model.Post;
import dev.hilman.springredditclone.model.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUser(User user);
    List<Comment> findByPost(Post post);
}
