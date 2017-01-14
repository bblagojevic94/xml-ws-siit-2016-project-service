@javax.xml.bind.annotation.XmlSchema(
        xmlns = {
                @javax.xml.bind.annotation.XmlNs(prefix = PREDICATE_PREF, namespaceURI = PREDICATE),
                @javax.xml.bind.annotation.XmlNs(prefix = AMENDMENTS_PREF, namespaceURI = AMENDMENTS),
                @javax.xml.bind.annotation.XmlNs(prefix = LAWS_PREF, namespaceURI = LAWS),
                @javax.xml.bind.annotation.XmlNs(prefix = ELEMENTS_PREF, namespaceURI = ELEMENTS),
                @javax.xml.bind.annotation.XmlNs(prefix = USERS_PREF,  namespaceURI = USERS),
                @javax.xml.bind.annotation.XmlNs(prefix = PARLIAMENT_PREF,  namespaceURI = PARLIAMENTS),
                @javax.xml.bind.annotation.XmlNs(prefix = SCHEMA_PREF,  namespaceURI = SCHEMA)
        },
        elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED)
package rs.ac.uns.sw.xml.domain;

import static rs.ac.uns.sw.xml.config.MarkLogicConstants.Namespaces.*;
import static rs.ac.uns.sw.xml.config.MarkLogicConstants.Prefixes.*;