package rs.ac.uns.sw.xml.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.uns.sw.xml.domain.Glava;
import rs.ac.uns.sw.xml.domain.wrapper.SearchResult;
import rs.ac.uns.sw.xml.service.GlavaServiceXML;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class GlavaRestController {

    @Autowired
    GlavaServiceXML service;

    @RequestMapping(
            value    = "/glave",
            method   = RequestMethod.POST,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<Glava> createGlava(@RequestBody Glava glava, UriComponentsBuilder builder) throws URISyntaxException {
        Glava result = service.create(glava);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/glave/{id}.xml")
                        .buildAndExpand(glava.getId()).toUri());

        return new ResponseEntity<>(result, headers, HttpStatus.CREATED);
    }

    @RequestMapping(
            value    = "/glave",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<SearchResult> getGlave() {
        SearchResult result = service.getAll();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(
            value    = "/glave-xquery",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<SearchResult> searchGlaveByOdjeljak(@RequestParam(name = "query") String query) {
        SearchResult result = service.findByOdjeljakContains(query);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
