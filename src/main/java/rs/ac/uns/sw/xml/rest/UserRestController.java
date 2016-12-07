package rs.ac.uns.sw.xml.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.uns.sw.xml.domain.AppUser;
import rs.ac.uns.sw.xml.domain.User;
import rs.ac.uns.sw.xml.security.TokenUtils;
import rs.ac.uns.sw.xml.service.UserServiceXML;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    UserServiceXML service;


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenUtils tokenUtils;


    @Autowired
    UserDetailsService userDetailsService;


    @RequestMapping(
            value = "/users/auth",
            method = RequestMethod.POST
    )
    public ResponseEntity<AuthResponse> authenticate(@RequestParam(value = "email") String username, @RequestParam(value = "password") String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = tokenUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }


    @RequestMapping(
            value    = "/users",
            method   = RequestMethod.POST,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<AppUser> registerUser(@RequestBody AppUser user, UriComponentsBuilder builder) throws URISyntaxException {
        AppUser result = service.create(user);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(
                builder.path("/users/{id}.xml")
                        .buildAndExpand(user.getId()).toUri());

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @RequestMapping(
            value    = "/users/{id}",
            method   = RequestMethod.GET,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id, UriComponentsBuilder builder) throws URISyntaxException {
        AppUser result = service.findById(id);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(
                builder.path("/users/{id}.xml")
                        .buildAndExpand(id).toUri());

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    /**
     * Authentication response
     */
    private static class AuthResponse {
        private String token;

        public AuthResponse(final String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
