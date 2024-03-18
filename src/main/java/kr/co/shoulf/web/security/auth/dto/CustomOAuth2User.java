package kr.co.shoulf.web.security.auth.dto;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {
    private final OAuth2Response oAuth2Response;
    private final String role;
    @Getter
    private final Long userNo;

    public CustomOAuth2User(OAuth2Response oAuth2Response, String role, Long userNo) {
        this.oAuth2Response = oAuth2Response;
        this.role = role;
        this.userNo = userNo;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role;
            }
        });

        return collection;
    }

    @Override
    public String getName() {
        return oAuth2Response.getName();
    }

    public String getUsername() {
        return oAuth2Response.getProvider() + "_" + oAuth2Response.getProviderId();
    }

    public String getEmail() {
        return oAuth2Response.getEmail();
    }

}
