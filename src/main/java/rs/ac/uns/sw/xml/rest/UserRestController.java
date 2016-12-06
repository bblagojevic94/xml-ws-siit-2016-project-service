package rs.ac.uns.sw.xml.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.uns.sw.xml.domain.AppUser;
import rs.ac.uns.sw.xml.domain.User;
import rs.ac.uns.sw.xml.service.UserServiceXML;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    UserServiceXML service;

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
}
