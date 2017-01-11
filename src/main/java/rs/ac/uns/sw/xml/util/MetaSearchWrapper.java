package rs.ac.uns.sw.xml.util;

import java.util.Date;

public class MetaSearchWrapper {

    private Integer startVotesFor;
    private Integer endVotesFor;
    private Integer startVotesAgainst;
    private Integer endVotesAgainst;
    private Integer startVotesNeutral;
    private Integer endVotesNeutral;
    private Date startDateOfProposal;
    private Date endDateOfProposal;
    private Date startDateOfVoting;
    private Date endDateOfVoting;
    private String status;


    public MetaSearchWrapper startVotesFor(Integer startVotesFor) {
        this.startVotesFor = startVotesFor;
        return this;
    }

    public MetaSearchWrapper endVotesFor(Integer endVotesFor) {
        this.endVotesFor = endVotesFor;
        return this;
    }

    public MetaSearchWrapper startVotesAgainst(Integer startVotesAgainst) {
        this.startVotesAgainst = startVotesAgainst;
        return this;
    }

    public MetaSearchWrapper endVotesAgainst(Integer endVotesAgainst) {
        this.endVotesAgainst = endVotesAgainst;
        return this;
    }

    public MetaSearchWrapper startVotesNeutral(Integer startVotesNeutral) {
        this.startVotesNeutral = startVotesNeutral;
        return this;
    }

    public MetaSearchWrapper endVotesNeutral(Integer endVotesNeutral) {
        this.endVotesNeutral = endVotesNeutral;
        return this;
    }

    public MetaSearchWrapper startDateOfProposal(Date startDateOfProposal) {
        this.startDateOfProposal = startDateOfProposal;
        return this;
    }

    public MetaSearchWrapper endDateOfProposal(Date endDateOfProposal) {
        this.endDateOfProposal = endDateOfProposal;
        return this;
    }

    public MetaSearchWrapper startDateOfVoting(Date startDateOfVoting) {
        this.startDateOfVoting = startDateOfVoting;
        return this;
    }

    public MetaSearchWrapper endDateOfVoting(Date endDateOfVoting) {
        this.endDateOfVoting = endDateOfVoting;
        return this;
    }

    public MetaSearchWrapper status(String status) {
        this.status = status;
        return this;
    }

    public Integer getStartVotesFor() {
        return startVotesFor;
    }

    public void setStartVotesFor(Integer startVotesFor) {
        this.startVotesFor = startVotesFor;
    }

    public Integer getEndVotesFor() {
        return endVotesFor;
    }

    public void setEndVotesFor(Integer endVotesFor) {
        this.endVotesFor = endVotesFor;
    }

    public Integer getStartVotesAgainst() {
        return startVotesAgainst;
    }

    public void setStartVotesAgainst(Integer startVotesAgainst) {
        this.startVotesAgainst = startVotesAgainst;
    }

    public Integer getEndVotesAgainst() {
        return endVotesAgainst;
    }

    public void setEndVotesAgainst(Integer endVotesAgainst) {
        this.endVotesAgainst = endVotesAgainst;
    }

    public Integer getStartVotesNeutral() {
        return startVotesNeutral;
    }

    public void setStartVotesNeutral(Integer startVotesNeutral) {
        this.startVotesNeutral = startVotesNeutral;
    }

    public Integer getEndVotesNeutral() {
        return endVotesNeutral;
    }

    public void setEndVotesNeutral(Integer endVotesNeutral) {
        this.endVotesNeutral = endVotesNeutral;
    }

    public Date getStartDateOfProposal() {
        return startDateOfProposal;
    }

    public void setStartDateOfProposal(Date startDateOfProposal) {
        this.startDateOfProposal = startDateOfProposal;
    }

    public Date getEndDateOfProposal() {
        return endDateOfProposal;
    }

    public void setEndDateOfProposal(Date endDateOfProposal) {
        this.endDateOfProposal = endDateOfProposal;
    }

    public Date getStartDateOfVoting() {
        return startDateOfVoting;
    }

    public void setStartDateOfVoting(Date startDateOfVoting) {
        this.startDateOfVoting = startDateOfVoting;
    }

    public Date getEndDateOfVoting() {
        return endDateOfVoting;
    }

    public void setEndDateOfVoting(Date endDateOfVoting) {
        this.endDateOfVoting = endDateOfVoting;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
