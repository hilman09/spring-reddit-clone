package dev.hilman.springredditclone.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.hilman.springredditclone.exceptions.SpringRedditException;
import dev.hilman.springredditclone.services.CommentService;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CommentServiceTest {
    
    @Test
    @DisplayName("Test Should Pass When Comment do not Contains Swear Words")
    void shouldNotContainSwearWordsInsideComment() {
        CommentService commentService = new CommentService(null, null, null, null, null, null, null);
       boolean b = commentService.containsSwearWords("this is a comment");
       Assertions.assertThat(b).isFalse();
        
    }

    @Test
    @DisplayName("Should Throw Exception when Exception Contains Swear Words")
    void shouldFailWhenCommentContainsSwearWords() {
        CommentService commentService = new CommentService(null, null, null, null, null, null, null);

        assertThatThrownBy(() -> {
            commentService.containsSwearWords("This is a shitty comment");
        }).isInstanceOf(SpringRedditException.class)
                .hasMessage("Comments contains unacceptable language");
    }
}
