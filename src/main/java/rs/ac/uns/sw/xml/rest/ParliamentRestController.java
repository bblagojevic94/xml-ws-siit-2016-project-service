package rs.ac.uns.sw.xml.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.uns.sw.xml.domain.Parliament;
import rs.ac.uns.sw.xml.service.ParliamentServiceXML;
import rs.ac.uns.sw.xml.states.InProgressState;
import rs.ac.uns.sw.xml.states.State;
import rs.ac.uns.sw.xml.states.StateContext;
import rs.ac.uns.sw.xml.util.Constants;
import rs.ac.uns.sw.xml.util.HeaderUtil;

import java.net.URISyntaxException;

import static rs.ac.uns.sw.xml.util.Constants.ACTIVE_PARLIAMENT;
import static rs.ac.uns.sw.xml.util.StatesUtil.stateToString;

@RestController
@RequestMapping("/api/parliaments")
public class ParliamentRestController {

    @Autowired
    ParliamentServiceXML service;

    @Autowired
    StateContext stateContext;


    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<Parliament> addParliament(@RequestBody Parliament parliament, UriComponentsBuilder builder) throws URISyntaxException {
        State state = stateContext.setState(stateContext.parliamentStatusToState(parliament.getHead().getStatus()));

        if (state == null) {
            return ResponseEntity
                    .badRequest()
                    .headers(HeaderUtil.failure(
                            Constants.EntityNames.PARLIAMENTS,
                            HeaderUtil.ERROR_CODE_WRONG_STATE_TRANSITION,
                            "Wrong transition from " + stateToString(stateContext.getState()) + " to " + parliament.getHead().getStatus()))
                    .body(null);
        }

        parliament.setId(ACTIVE_PARLIAMENT);
        final Parliament result = service.create(parliament);

        stateContext.setParliament(parliament);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/parliaments/{id}.xml")
                        .buildAndExpand(parliament.getId())
                        .toUri());

        return new ResponseEntity<>(result, headers, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/active/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<Parliament> getActiveParliament() throws URISyntaxException {

        if (!(stateContext.getState() instanceof InProgressState)) {
            return ResponseEntity
                    .badRequest()
                    .headers(HeaderUtil.failure(
                            Constants.EntityNames.PARLIAMENTS,
                            HeaderUtil.ERROR_CODE_NO_ACTIVE_PARLIAMENT,
                            HeaderUtil.ERROR_MSG_NO_ACTIVE_PARLIAMENT))
                    .body(null);
        }

        Parliament result = stateContext.getParliament();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/change-state/{state}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<Parliament> changeStateOfParliament(@PathVariable String state) throws URISyntaxException {
        State newState = stateContext.parliamentStatusToState(state);
        if (newState == null) {
            return ResponseEntity
                    .badRequest()
                    .headers(HeaderUtil.failure(
                            Constants.EntityNames.PARLIAMENTS,
                            HeaderUtil.ERROR_CODE_WRONG_STATE,
                            HeaderUtil.ERROR_MSG_WRONG_STATE))
                    .body(null);
        }

        newState = stateContext.setState(newState);
        if (newState == null) {
            return ResponseEntity
                    .badRequest()
                    .headers(HeaderUtil.failure(
                            Constants.EntityNames.PARLIAMENTS,
                            HeaderUtil.ERROR_CODE_WRONG_STATE_TRANSITION,
                            "Wrong transition from " + stateToString(stateContext.getState()) + " to " + state))
                    .body(null);
        }

        Parliament result = stateContext.getParliament();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}