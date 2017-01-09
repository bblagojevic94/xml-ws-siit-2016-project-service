package rs.ac.uns.sw.xml.util;

public final class PredicatesConstants {

    private PredicatesConstants() {
    }

    public static final String BASE_PREDICATE_URI = "http://www.parlament.gov.rs/rdf_schema/skupstina#";

    public static final String SUGGESTED = BASE_PREDICATE_URI + "predlozenOd";
    public static final String VOTES_FOR = BASE_PREDICATE_URI + "brojGlasovaZa";
    public static final String VOTES_AGAINST = BASE_PREDICATE_URI + "brojGlasovaProtiv";
    public static final String VOTES_NEUTRAL = BASE_PREDICATE_URI + "brojGlasovaUzdrzanih";
    public static final String DATE_OF_SUGGESTION = BASE_PREDICATE_URI + "datumPredloga";
    public static final String DATE_OF_VOTING = BASE_PREDICATE_URI + "datumIzglasavanja";
    public static final String AMENDMENTS_STATUS = BASE_PREDICATE_URI + "statusAmandmaana";
    public static final String LAW_STATUS = BASE_PREDICATE_URI + "statusOdluke";
}
