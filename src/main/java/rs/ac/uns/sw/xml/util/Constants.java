package rs.ac.uns.sw.xml.util;

public class Constants {

    public final class EntityNames {
        public static final String LAWS = "laws";
        public static final String PARLIAMENTS = "parliaments";
        public static final String AMENDMENTS = "amendments";
        public static final String USERS = "users";
    }

    public final class AmendmentsStates {
        public static final String SUGGESTED = "predložen";
        public static final String WITHDRAWN = "povučen";
        public static final String REJECTED = "odbijen";
        public static final String ACCEPTED = "prihvaćen";
    }

    public final class LawsStates {
        public static final String SUGGESTED = "predložen";
        public static final String WITHDRAWN = "povučen";
        public static final String REJECTED = "odbijen";
        public static final String ACCEPTED = "prihvaćen";
    }

    public static final String ACTIVE_PARLIAMENT = "active_parliament";

    public final class Resources {
        public static final String BASE_RESOURCE_URI = "http://www.ftn.uns.ac.rs/rdf/examples/";
        public static final String LAWS = BASE_RESOURCE_URI + "laws/";
        public static final String AMENDMENTS = BASE_RESOURCE_URI + "amendments/";
        public static final String USERS = "";
    }

}


