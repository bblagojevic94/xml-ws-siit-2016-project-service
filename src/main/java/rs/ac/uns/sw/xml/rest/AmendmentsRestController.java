package rs.ac.uns.sw.xml.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.service.AmendmentsServiceXML;
import rs.ac.uns.sw.xml.util.Transformers;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/amendments")
public class AmendmentsRestController {


    @Autowired
    AmendmentsServiceXML service;

    @Autowired
    Transformers transformer;

    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<Amendments> addAmendment(@RequestBody Amendments amendments, UriComponentsBuilder builder) throws URISyntaxException {
        final Amendments result = service.create(amendments);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/amendments/{id}.xml")
                        .buildAndExpand(amendments.getId()).toUri());

        return new ResponseEntity<>(result, headers ,HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/proposer/{proposerId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<String>> searchAmendmentsByProposer(@PathVariable String proposerId) throws URISyntaxException {
        final List<String> result = service.findByProposer( "http://www.ftn.uns.ac.rs/rdf/examples/person/" +  proposerId);

        return new ResponseEntity<>(result ,HttpStatus.OK);
    }
}
