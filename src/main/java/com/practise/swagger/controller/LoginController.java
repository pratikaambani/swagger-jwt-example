package com.practise.swagger.controller;

import com.practise.swagger.model.Subject;
import com.practise.swagger.model.UserDetail;
import com.practise.swagger.util.CookieUtil;
import com.practise.swagger.util.JwtUtil;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by Pratik Ambani on 3/5/2017.
 */
@RestController(value = "loginController")
public class LoginController {

    //I won't share this key with anybody,
    private static final String signingKey = "secretKey";

    //Would prefer to store such values in props file
    private static final String jwtTokenCookieName = "encodedToken";

    //Will act as database
    private static final Map<String, String> userData = new HashMap<>();
    
    @Autowired
    Subject subject;

    public LoginController() {
        userData.put("pratik", "randomPass");
        userData.put("admin", "pass1");
    }

    //Here comes out real hero, with lot much customizations
    @ApiOperation(
            value = "Go Go Go!!",
            notes = "{\n \"name\": \"pratik\",\n \"password\": \"randomPass\"\n }",
            consumes = APPLICATION_JSON_VALUE,
            nickname = "My Login Service",
            response = Void.class,
            position = 0,
            hidden = false,
            tags = "userLogin"
    )
    @PostMapping(value = "/api/sample/login",
            produces = APPLICATION_JSON_VALUE)
    public void login(HttpServletResponse response, @RequestBody UserDetail userDetail) throws Exception {
        if (userDetail.getName() == null || !userData.containsKey(userDetail.getName()) || !userData.get(userDetail.getName()).equals(userDetail.getPassword())) {
            System.out.println("Exception spotted");
            throw new Exception("Sorry mate, you are not authorized to use this");
        }
        String token = JwtUtil.generateToken(userDetail.getName(), signingKey);
        CookieUtil.create(response, jwtTokenCookieName, token, false, -1, "localhost");
    }

    @ApiOperation(value = "Retrieving subject from encoded token", notes = "Lets try!")
    @PostMapping(value = "/api/sample/getSubject", produces = APPLICATION_JSON_VALUE)
    public Subject getSubject(HttpServletRequest request) {
        return () -> JwtUtil.getSubject(request, jwtTokenCookieName, signingKey);
    }
}