package dev.hilman.springredditclone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hilman.springredditclone.model.Post;
import dev.hilman.springredditclone.model.User;
import dev.hilman.springredditclone.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
