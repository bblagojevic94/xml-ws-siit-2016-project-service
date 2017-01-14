package rs.ac.uns.sw.xml.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.uns.sw.xml.domain.Parliament;
import rs.ac.uns.sw.xml.service.ParliamentServiceXML;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/parliaments")
public class ParliamentRestController {

    @Autowired
    ParliamentServiceXML service;

    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<Parliament> addParliament(@RequestBody Parliament parliament, UriComponentsBuilder builder) throws URISyntaxException {
        final Parliament result = service.create(parliament);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/parliaments/{id}.xml")
                        .buildAndExpand(parliament.getId()).toUri());

        return new ResponseEntity<>(result, headers, HttpStatus.CREATED);
    }
}
