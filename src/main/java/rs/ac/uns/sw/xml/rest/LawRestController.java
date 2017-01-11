package rs.ac.uns.sw.xml.rest;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.xml.sax.SAXException;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.service.AmendmentsServiceXML;
import rs.ac.uns.sw.xml.service.LawServiceXML;
import rs.ac.uns.sw.xml.util.MetaSearchWrapper;
import rs.ac.uns.sw.xml.util.RepositoryUtil;
import rs.ac.uns.sw.xml.util.Transformers;
import rs.ac.uns.sw.xml.util.search_wrapper.SearchResult;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

@RestController
@RequestMapping("/api/laws")
public class LawRestController {

    private static final String NAME = "propis";

    @Autowired
    LawServiceXML service;

    @Autowired
    AmendmentsServiceXML amendmentsService;

    @Autowired
    Transformers transformer;

    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<Law> addLaw(@RequestBody Law law, UriComponentsBuilder builder) throws URISyntaxException {
        final Law result = service.create(law);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/laws/{id}.xml")
                        .buildAndExpand(law.getId()).toUri());

        return new ResponseEntity<>(result, headers, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<SearchResult> getLaws() {
        final SearchResult result = service.getAll();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<Law> getLawByName(@PathVariable String name) {
        final Law result = service.getOneById(name);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/pdf/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE

    )
    public ResponseEntity<InputStreamResource> downloadLawPDF(@PathVariable String name)
            throws IOException, JAXBException, TransformerException, SAXException {

        final Law result = service.getOneById(name);

        transformer.setName(NAME);

        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=law.pdf")
                .body(transformer.toPdf(RepositoryUtil.toXmlString(result, Law.class)));
    }

    @RequestMapping(
            value = "/html/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_XHTML_XML_VALUE

    )
    public ResponseEntity<String> downloadLawHtml(@PathVariable String name)
            throws IOException, JAXBException, TransformerException, SAXException, ParserConfigurationException {

        final Law result = service.getOneById(name);

        transformer.setName(NAME);

        return ResponseEntity
                .ok()
                .body(transformer.toHtml(RepositoryUtil.toXmlString(result, Law.class)));
    }

    @RequestMapping(
            value = "/search/metadata",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<JsonNode> searchLawsByVotes(
            @RequestParam(value = "startVotesFor", required = false) Integer startVotesFor,
            @RequestParam(value = "endVotesFor", required = false) Integer endVotesFor,
            @RequestParam(value = "startVotesAgainst", required = false) Integer startVotesAgainst,
            @RequestParam(value = "endVotesAgainst", required = false) Integer endVotesAgainst,
            @RequestParam(value = "startVotesNeutral", required = false) Integer startVotesNeutral,
            @RequestParam(value = "endVotesNeutral", required = false) Integer endVotesNeutral,
            @RequestParam(value = "startDateOfProposal", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDateOfProposal,
            @RequestParam(value = "endDateOfProposal", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDateOfProposal,
            @RequestParam(value = "startDateOfVoting", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDateOfVoting,
            @RequestParam(value = "endDateOfVoting", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDateOfVoting,
            @RequestParam(value = "status", required = false) String status
    ) throws URISyntaxException {
        MetaSearchWrapper wrapper = new MetaSearchWrapper()
                .startVotesFor(startVotesFor)
                .endVotesFor(endVotesFor)
                .startVotesAgainst(startVotesAgainst)
                .endVotesAgainst(endVotesAgainst)
                .startVotesNeutral(startVotesNeutral)
                .endVotesNeutral(endVotesNeutral)
                .startDateOfProposal(startDateOfProposal)
                .endDateOfProposal(endDateOfProposal)
                .startDateOfVoting(startDateOfVoting)
                .endDateOfVoting(endDateOfVoting)
                .status(status);

        final JsonNode result = service.searchLaws(wrapper);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/metadata/",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}

    )
    public ResponseEntity<?> getMetadataJSON(@RequestHeader("Accept") String mediaType) {
        System.out.println(mediaType);
        HttpHeaders headers = new HttpHeaders();
        switch (mediaType) {
            case MediaType.APPLICATION_JSON_VALUE: {
                JsonNode result = service.getMetadataJSON();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return new ResponseEntity<>(result, headers, HttpStatus.OK);
            }
            case MediaType.APPLICATION_XML_VALUE: {
                // TODO : change handling xml result
                StreamResult result = service.getMetadataTriples();
                headers.setContentType(MediaType.APPLICATION_XML);
                return new ResponseEntity<>(result, headers, HttpStatus.OK);
            }
            default:
                return new ResponseEntity<>("Need Accept header", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/search",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<SearchResult> searchLaws(@RequestParam("query") String query) {

        final SearchResult result = service.getAllByQuery(query);

        return ResponseEntity
                .ok()
                .body(result);
    }

    @RequestMapping(
            value = "/update/{amendmentsId}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<Law> update(@PathVariable("amendmentsId") String amendmentsId) {

        Amendments amendments = amendmentsService.getOneById(amendmentsId);

        Law result = service.updateWithAmendments(amendments);

        return ResponseEntity
                .ok()
                .body(result);
    }
}
