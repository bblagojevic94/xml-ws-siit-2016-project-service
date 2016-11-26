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
import rs.ac.uns.sw.xml.domain.Glava;
import rs.ac.uns.sw.xml.domain.GlavaRepositoryXML;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class GlavaRestController {

    @Autowired
    GlavaRepositoryXML repositoryXML;

    @RequestMapping(
            value    = "/glave",
            method   = RequestMethod.POST,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Glava> createGlava(@RequestBody Glava glava, UriComponentsBuilder builder) throws URISyntaxException {
        System.out.println("dsa das das dsdsa ");

        System.out.println(glava.getOdjeljak());
        System.out.println(glava.getName());

        Glava result = repositoryXML.save(glava);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/glave/{id}.xml")
                        .buildAndExpand(glava.getId()).toUri());

        return new ResponseEntity<>(glava, headers, HttpStatus.CREATED);
    }

    @RequestMapping(
            value    = "/glave",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String fsd() {
        return "{\"ime\": \"Test\"}";
    }
}
