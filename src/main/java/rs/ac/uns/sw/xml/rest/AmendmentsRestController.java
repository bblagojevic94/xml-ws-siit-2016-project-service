package rs.ac.uns.sw.xml.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.xml.sax.SAXException;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.service.AmendmentsServiceXML;
import rs.ac.uns.sw.xml.util.RepositoryUtil;
import rs.ac.uns.sw.xml.util.Transformers;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/amendments")
public class AmendmentsRestController {

    private static final String NAME = "amandman";

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

        return new ResponseEntity<>(result, headers, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/proposer/{proposerId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<String>> searchAmendmentsByProposer(@PathVariable String proposerId) throws URISyntaxException {
        final List<String> result = service.findByProposer("http://www.ftn.uns.ac.rs/rdf/examples/person/" + proposerId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/pdf/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE

    )
    public ResponseEntity<InputStreamResource> downloadAmendmentPDF(@PathVariable String name)
            throws IOException, JAXBException, TransformerException, SAXException {

        final Amendments result = service.getOneById(name);

        transformer.setName(NAME);

        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=amendment.pdf")
                .body(transformer.toPdf(RepositoryUtil.toXmlString(result, Amendments.class)));
    }

    @RequestMapping(
            value = "/html/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_XHTML_XML_VALUE

    )
    public ResponseEntity<String> downloadAmendmentHtml(@PathVariable String name)
            throws IOException, JAXBException, TransformerException, SAXException, ParserConfigurationException {

        final Amendments result = service.getOneById(name);

        transformer.setName(NAME);

        return ResponseEntity
                .ok()
                .body(transformer.toHtml(RepositoryUtil.toXmlString(result, Amendments.class)));
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {

        service.deleteAmendmentsById(id);

        return ResponseEntity
                .ok()
                .build();
    }

    @RequestMapping(
            value = "/{id}/{status}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<Amendments> updateByStatus(@PathVariable("id") String id, @PathVariable("status") String status) {
        final Amendments result = service.updateAmendmentsStatus(id, status);

        return ResponseEntity
                .ok()
                .body(result);
    }
}
