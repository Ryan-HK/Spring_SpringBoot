package io.security.springsecuritymaster;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final HttpSessionSecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();


    @PostMapping("/login")
    public Authentication login(@RequestBody LoginResult login, HttpServletRequest request, HttpServletResponse response) {
        
        //Step.1 : Token 생성
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken
                .unauthenticated(login.getUsername(), login.getPassword());

        //Step.2 : Authentication 객체 생성
        Authentication authentication = authenticationManager.authenticate(token);
        
        //Step.3 : SecurityContext 객체 생성 (빈객체)
        SecurityContext securityContext = SecurityContextHolder.getContextHolderStrategy().createEmptyContext();
        
        //Step.4 : SecurityContext에 Authentication 저장
        securityContext.setAuthentication(authentication);
        
        //Step.5 : SecurityContextHolder에 SecurityContext 저장
        SecurityContextHolder.getContextHolderStrategy().setContext(securityContext);

        //Step.6 : 영속성을 위해 SecurityContextRepository에 SecurityContext 저장
        securityContextRepository.saveContext(securityContext, request, response);

        return authentication;
    }

}
