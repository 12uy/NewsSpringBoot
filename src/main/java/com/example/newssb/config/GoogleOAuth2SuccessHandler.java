package com.example.newssb.config;

import com.example.newssb.converter.UserConverter;
import com.example.newssb.dto.UserDTO;
import com.example.newssb.entity.RoleEntity;
import com.example.newssb.entity.UserEntity;
import com.example.newssb.repository.RoleRepository;
import com.example.newssb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String username = token.getPrincipal().getAttributes().get("email").toString();
        if (userRepository.findOneByUserName(username).isPresent()) { //neu da co user trong db
//            redirectStrategy.sendRedirect(request, response, "/");
        } else {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName(username);
            userEntity.setFullName(token.getPrincipal().getAttributes().get("given_name").toString() + " " + token.getPrincipal().getAttributes().get("family_name").toString());
            userEntity.setPassword(bCryptPasswordEncoder.encode("1"));
            userEntity.setStatus(1);
            List<RoleEntity> roles = new ArrayList<>();
            roles.add(roleRepository.findById(2L).get());
            userEntity.setRoles(roles);
            userRepository.save(userEntity);
        }
        redirectStrategy.sendRedirect(request, response, "/");
    }
}
