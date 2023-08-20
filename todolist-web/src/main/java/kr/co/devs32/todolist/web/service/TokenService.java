package kr.co.devs32.todolist.web.service;

import kr.co.devs32.todolist.web.config.jwt.TokenProvider;
import kr.co.devs32.todolist.web.dto.TokenResponse;
import kr.co.devs32.todolist.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        //토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.vaildToken(refreshToken)){
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }

    public TokenResponse createAllToken(User userInfo) {
        //accessToken, refreshToken 생성
        String accessToken = tokenProvider.generateToken(userInfo, Duration.ofHours(1)); //1시간
        String refreshToken = tokenProvider.generateToken(userInfo, Duration.ofDays(7)); //7일

        return new TokenResponse(accessToken, refreshToken);
    }

}