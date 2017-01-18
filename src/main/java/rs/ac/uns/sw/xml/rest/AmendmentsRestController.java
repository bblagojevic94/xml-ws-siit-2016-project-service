package rs.ac.uns.sw.xml.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.xml.sax.SAXException;
import rs.ac.uns.sw.xml.domain.Amendments;
import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.domain.Parliament;
import rs.ac.uns.sw.xml.service.AmendmentsServiceXML;
import rs.ac.uns.sw.xml.service.ParliamentServiceXML;
import rs.ac.uns.sw.xml.states.StateContext;
import rs.ac.uns.sw.xml.util.Constants;
import rs.ac.uns.sw.xml.util.HeaderUtil;
import rs.ac.uns.sw.xml.util.RepositoryUtil;
import rs.ac.uns.sw.xml.util.Transformers;
import rs.ac.uns.sw.xml.util.voting_wrapper.VotingObject;

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
    ParliamentServiceXML parliamentServiceXML;

    @Autowired
    Transformers transformer;

    @Autowired
    StateContext stateContext;

    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<Amendments> addAmendment(@RequestBody Amendments amendments) throws URISyntaxException {
        if (stateContext.getState() == null) {
            return ResponseEntity
                    .badRequest()
                    .headers(HeaderUtil.failure(
                            Constants.EntityNames.PARLIAMENTS,
                            HeaderUtil.ERROR_CODE_NO_ACTIVE_PARLIAMENT,
                            HeaderUtil.ERROR_MSG_NO_ACTIVE_PARLIAMENT
                    ))
                    .body(null);
        }

        return (ResponseEntity<Amendments>) stateContext.getState().suggestAmendments(amendments, stateContext.getParliament());
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
            value = "/{id}",
            method = RequestMethod.GET,
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_OCTET_STREAM_VALUE,
                    MediaType.APPLICATION_XHTML_XML_VALUE
            }
    )
    public ResponseEntity<?> AmendmentsById(@RequestHeader("Accept") String mediaType, @PathVariable String id)
            throws JAXBException, TransformerException, IOException, SAXException, ParserConfigurationException {

        final Amendments result = service.getOneById(id);

        HttpHeaders headers = new HttpHeaders();
        switch (mediaType) {
            case MediaType.APPLICATION_XML_VALUE: {
                headers.setContentType(MediaType.APPLICATION_XML);
                return new ResponseEntity<>(result, headers, HttpStatus.OK);
            }

            case MediaType.APPLICATION_OCTET_STREAM_VALUE: {
                transformer.setName(NAME);

                headers.set("Content-Disposition", "attachment; filename=amendments.pdf");
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                return new ResponseEntity<>(transformer.toPdf(RepositoryUtil.toXmlString(result, Amendments.class)), headers, HttpStatus.OK);
            }

            case MediaType.APPLICATION_XHTML_XML_VALUE: {
                transformer.setName(NAME);

                headers.setContentType(MediaType.APPLICATION_XHTML_XML);
                return new ResponseEntity<>(transformer.toHtml(RepositoryUtil.toXmlString(result, Amendments.class)), headers, HttpStatus.OK);
            }

            default:
                return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (!service.amendmentsExists(id)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

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
        if (!service.amendmentsExists(id)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return (ResponseEntity<Amendments>) stateContext.getState().updateAmendmentStatus(id, status, stateContext.getParliament());
    }

    @RequestMapping(
            value = "/voting/{id}/",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<Law> updateVotes(@PathVariable("id") String id, @RequestBody VotingObject votes) {
        if (!service.amendmentsExists(id)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return (ResponseEntity<Law>) stateContext.getState().updateAmendmentVoting(id, votes);
    }
}
