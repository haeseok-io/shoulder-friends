package kr.co.shoulf.web.security.auth;

import jakarta.servlet.http.HttpSession;
import kr.co.shoulf.web.entity.Users;
import kr.co.shoulf.web.repository.UserRepository;
import kr.co.shoulf.web.security.auth.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 부모 클래스인 DefaultOAuth2UserService 의 loadUser() 메서드를 호출
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 로그인 진행중인 서비스를 구분하는 코드 (naver, google, kakao)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response = null;

        if(registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }else if(registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }else if(registrationId.equals("kakao")) {
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }else {
            return null;
        }

        String nickName = oAuth2Response.getProvider() + "_" + oAuth2Response.getProviderId();
        Users user = userRepository.findByNickname(nickName);
        String role = "ROLE_USER";
        
        // 비밀번호 10자리 랜덤 생성
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int PASSWORD_LENGTH = 10;

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        
        //  DB에 user 정보가 없으면 저장
        if(user == null) {
            Users user2 = new Users();
            user2.setNickname(nickName);
            user2.setEmail(oAuth2Response.getEmail());
            user2.setRole(role);
            user2.setPass(sb.toString());
            user2.setOauth2Where(oAuth2Response.getProvider());

            userRepository.save(user2);
        }

        return new CustomOAuth2User(oAuth2Response, role);
    }
}
