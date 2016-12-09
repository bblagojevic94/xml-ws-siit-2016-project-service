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
import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.domain.wrapper.SearchResult;
import rs.ac.uns.sw.xml.service.LawServiceXML;
import rs.ac.uns.sw.xml.util.Transformers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/laws")
public class LawRestController {

    @Autowired
    LawServiceXML service;

    @RequestMapping(
            value    = "/",
            method   = RequestMethod.POST,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<Law> addLaw(@RequestBody Law glava, UriComponentsBuilder builder) throws URISyntaxException {
        final Law result = service.create(glava);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/laws/{id}.xml")
                        .buildAndExpand(glava.getId()).toUri());

        return new ResponseEntity<>(result, headers, HttpStatus.CREATED);
    }

    @RequestMapping(
            value    = "/",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<SearchResult> getLaws() {
        final SearchResult result = service.getAll();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(
            value    = "/{name}",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<Law> getLawByName(@PathVariable String name) {
        final Law result = service.getOneByName(name);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/pdf/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE

    )
    // FIXME Do refactoring...
    public ResponseEntity<InputStreamResource> downloadLawPDF(@PathVariable String name)
            throws IOException, JAXBException, TransformerException, SAXException {

        final Law result = service.getOneByName(name);

        JAXBContext context = JAXBContext.newInstance(Law.class);
        Marshaller marshaller = context.createMarshaller();

        StringWriter sw = new StringWriter();
        marshaller.marshal(result, sw);
        String xmlString = sw.toString();

        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=law.pdf")
                .body(Transformers.toPdf(xmlString));
    }

    @RequestMapping(
            value = "/html/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE

    )
    // FIXME Do refactoring...
    public ResponseEntity<Law> downloadLawHtml(@PathVariable String name)
            throws IOException, JAXBException, TransformerException, SAXException, ParserConfigurationException {

        final Law result = service.getOneByName(name);

        JAXBContext context = JAXBContext.newInstance(Law.class);
        Marshaller marshaller = context.createMarshaller();

        StringWriter sw = new StringWriter();
        marshaller.marshal(result, sw);
        String xmlString = sw.toString();

        Transformers.toHtml(xmlString, "propis");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
