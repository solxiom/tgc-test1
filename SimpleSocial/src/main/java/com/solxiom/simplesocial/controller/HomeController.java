package com.solxiom.simplesocial.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}
        //     FacebookConnectionFactory connectionFactory =
//                new FacebookConnectionFactory("162886103757745", "fa8a9825f555a7a8949ec48fb93bda58");
    @RequestMapping(value = "/test-auth", method = RequestMethod.GET)
    public @ResponseBody
    String authTest() {
        String authurl = testAuth();
        System.out.println(" authurl: " + authurl);
        return " url: " + authurl;
    }

    private String testAuth() {
        FacebookConnectionFactory connectionFactory =
                new FacebookConnectionFactory("162886103757745", "fa8a9825f555a7a8949ec48fb93bda58");

        OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();

        OAuth2Parameters params = new OAuth2Parameters();

        params.setRedirectUri("/all-ok");

        String authorizeUrl = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, params);

//        response.sendRedirect(authorizeUrl);
        return authorizeUrl;
    }
}
