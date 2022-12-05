package dev.hilman.springredditclone.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hilman.springredditclone.exceptions.SpringRedditException;
import dev.hilman.springredditclone.model.RefreshToken;
import dev.hilman.springredditclone.repository.RefreshTokenRepository;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken() {
        var refreshToken = RefreshToken.builder()
                            .token(UUID.randomUUID().toString())
                            .createdDate(Instant.now())
                            .build();
        // RefreshToken refreshToken = new RefreshToken();
        // refreshToken.setToken(UUID.randomUUID().toString());
        // refreshToken.setCreatedDate(Instant.now());

        return refreshTokenRepository.save(refreshToken);
    }

    void validateRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new SpringRedditException("Invalid refresh Token"));
    }

    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}