package kr.co.devs32.todolist.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class OauthResponseDto {

    private Long id;
    private String accessToken;
    private String refreshToken;
//    private Boolean isMember;

    public OauthResponseDto(Long id, String accessToken, String refreshToken) {
        this.id = id;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
