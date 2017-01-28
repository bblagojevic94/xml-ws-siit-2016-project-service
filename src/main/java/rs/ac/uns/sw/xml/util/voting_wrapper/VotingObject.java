package rs.ac.uns.sw.xml.util.voting_wrapper;

import rs.ac.uns.sw.xml.domain.Law;
import rs.ac.uns.sw.xml.util.search_wrapper.Metadata;

import javax.xml.bind.annotation.*;
import java.util.List;

@SuppressWarnings("unused")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "voting_object")
public class VotingObject {

    @XmlElement(name = "votes_for")
    private Integer votesFor;

    @XmlElement(name = "votes_against")
    private Integer votesAgainst;

    @XmlElement(name = "votes_neutral")
    private Integer votesNeutral;

    public VotingObject() {
    }

    public VotingObject(Integer votesFor, Integer votesAgainst, Integer votesNeutral) {
        this.votesFor = votesFor;
        this.votesAgainst = votesAgainst;
        this.votesNeutral = votesNeutral;
    }

    public Integer getVotesFor() {
        return votesFor;
    }

    public void setVotesFor(Integer votesFor) {
        this.votesFor = votesFor;
    }

    public Integer getVotesAgainst() {
        return votesAgainst;
    }

    public void setVotesAgainst(Integer votesAgainst) {
        this.votesAgainst = votesAgainst;
    }

    public Integer getVotesNeutral() {
        return votesNeutral;
    }

    public void setVotesNeutral(Integer votesNeutral) {
        this.votesNeutral = votesNeutral;
    }
}

