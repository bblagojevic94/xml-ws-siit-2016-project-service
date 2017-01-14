package rs.ac.uns.sw.xml.util;

import com.marklogic.client.util.EditableNamespaceContext;
import rs.ac.uns.sw.xml.domain.*;

import javax.xml.bind.JAXBException;

import static rs.ac.uns.sw.xml.config.MarkLogicConstants.Namespaces.*;
import static rs.ac.uns.sw.xml.config.MarkLogicConstants.Prefixes.*;

public class PartialUpdateUtil {

    /**
     * Find concrete child of regulation. Converts it in cleaned XML string for further usage
     * - patching complete element into database
     *
     * @param regulation
     * @return  Child of regulation parsed in string - it can be article, paragraph, clause, subclause or item
     * @throws JAXBException
     */
    public static String createXMLbyRegulation(Amendment.Body.Odredba regulation) throws JAXBException {

        // Try to get Article
        final Article article = regulation.getClan();
        if (article != null) {
            return cleanXML(RepositoryUtil.toXmlString(article, Article.class));
        }

        // Try to get Paragraph
        final Paragraph paragraph = regulation.getStav();
        if (paragraph != null) {
            return cleanXML(RepositoryUtil.toXmlString(paragraph, Paragraph.class));
        }

        // Try to get Clause
        final Clause clause = regulation.getTacka();
        if (clause != null) {
            return cleanXML(RepositoryUtil.toXmlString(clause, Clause.class));
        }

        // Try to get Subclause
        final Subclause subclause = regulation.getPodtacka();
        if (subclause != null) {
            return cleanXML(RepositoryUtil.toXmlString(subclause, Subclause.class));
        }

        // Try to get Item
        final Item item = regulation.getAlineja();
        if (item != null) {
            return cleanXML(RepositoryUtil.toXmlString(item, Item.class));
        }

        return null;
    }

    /**
     * Creates all needed namespaces on one place
     *
     * @return Grouped namespaces
     */
    public static EditableNamespaceContext createNamespaces() {
        EditableNamespaceContext namespaces = new EditableNamespaceContext();

        namespaces.put(AMENDMENTS_PREF, AMENDMENTS);
        namespaces.put(PREDICATE_PREF, PREDICATE);
        namespaces.put(ELEMENTS_PREF, ELEMENTS);
        namespaces.put(LAWS_PREF, LAWS);
        namespaces.put(XPATH_FUN_PREF, XPATH_FUNCTIONS);

        return namespaces;
    }

    /**
     * Converts our convention element id to XPath ID
     *
     * @param ref   String which represents OUR CONVENTION of ID
     * @return XPath expression for finding element by provided ID
     */
    public static String makeXPath(String ref) {

        StringBuilder builder = new StringBuilder();

        for (String s: ref.split("/")) {
            builder.append(String.format("%s[@id='%s']", "*", s));
            builder.append("//");
        }

        return builder.toString();
    }

    /**
     * Simple Util method which returns path of collection
     *
     * @param id
     * @return collection path
     */
    public static String makeCollectionPath(final String id) {
        return String.format("/laws/%s.xml", id);
    }

    /**
     * Function which removes all binded namespaces from root XML element.
     * Also, function removes first element which represents standard XML preprocessor
     * <?xml version="1.0" encoding="UTF-8"?>
     *
     * @param xml   XML with namespaces and first element
     * @return Cleaned XML String
     */
    private static String cleanXML(String xml) {
        return (xml.replaceAll("(xmlns:elem=\".*\")|(<\\?.*\\?>)", "")).replaceAll(" +", " ");
    }
}
