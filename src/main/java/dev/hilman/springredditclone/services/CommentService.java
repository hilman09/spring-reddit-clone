package dev.hilman.springredditclone.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.hilman.springredditclone.dto.CommentsDto;
import dev.hilman.springredditclone.exceptions.PostNotFoundException;
import dev.hilman.springredditclone.exceptions.SpringRedditException;
import dev.hilman.springredditclone.mapper.CommentMapper;
import dev.hilman.springredditclone.model.Comment;
import dev.hilman.springredditclone.model.NotificationEmail;
import dev.hilman.springredditclone.model.Post;
import dev.hilman.springredditclone.model.User;
import dev.hilman.springredditclone.repository.CommentRepository;
import dev.hilman.springredditclone.repository.PostRepository;
import dev.hilman.springredditclone.repository.UserRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {
    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(post.getUser().getUsername() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }

    public List<CommentsDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }

    public boolean containsSwearWords(String comment) {
        if (comment.contains("shit")) {
            throw new SpringRedditException("Comments contains unacceptable language");
        }
        return false;
    }
}
