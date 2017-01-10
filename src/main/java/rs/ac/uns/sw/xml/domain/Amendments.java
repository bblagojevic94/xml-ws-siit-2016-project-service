
package rs.ac.uns.sw.xml.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * <p>Java class for amandmani element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="amandmani">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="head">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="datum_predloga">
 *                       &lt;complexType>
 *                         &lt;simpleContent>
 *                           &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>date">
 *                             &lt;anyAttribute processContents='lax'/>
 *                           &lt;/extension>
 *                         &lt;/simpleContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="datum_izglasavanja">
 *                       &lt;complexType>
 *                         &lt;simpleContent>
 *                           &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>date">
 *                             &lt;anyAttribute processContents='lax'/>
 *                           &lt;/extension>
 *                         &lt;/simpleContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="status">
 *                       &lt;complexType>
 *                         &lt;simpleContent>
 *                           &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                             &lt;anyAttribute processContents='lax'/>
 *                           &lt;/extension>
 *                         &lt;/simpleContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="mjesto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="glasova_za">
 *                       &lt;complexType>
 *                         &lt;simpleContent>
 *                           &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>int">
 *                             &lt;anyAttribute processContents='lax'/>
 *                           &lt;/extension>
 *                         &lt;/simpleContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="glasova_protiv">
 *                       &lt;complexType>
 *                         &lt;simpleContent>
 *                           &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>int">
 *                             &lt;anyAttribute processContents='lax'/>
 *                           &lt;/extension>
 *                         &lt;/simpleContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="glasova_suzdrzani">
 *                       &lt;complexType>
 *                         &lt;simpleContent>
 *                           &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>int">
 *                             &lt;anyAttribute processContents='lax'/>
 *                           &lt;/extension>
 *                         &lt;/simpleContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="podnosilac">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;attribute name="korisnikId" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="propis">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;attribute name="propisId" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                   &lt;/sequence>
 *                   &lt;anyAttribute processContents='lax'/>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="body">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="pravni_osnov">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;sequence>
 *                               &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}clan"/>
 *                             &lt;/sequence>
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element ref="{http://www.parlament.gov.rs/schema/amandman}amandman" maxOccurs="unbounded"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/sequence>
 *         &lt;attGroup ref="{http://www.parlament.gov.rs/schema/elementi}standard_attrs"/>
 *         &lt;anyAttribute processContents='lax'/>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "head",
    "body"
})
@XmlRootElement(name = "amandmani", namespace = "http://www.parlament.gov.rs/schema/amandman")
public class Amendments {

    @XmlElement(namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
    protected Amendments.Head head;
    @XmlElement(namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
    protected Amendments.Body body;
    @XmlAttribute(name = "id", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String id;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the head property.
     * 
     * @return
     *     possible object is
     *     {@link Amendments.Head }
     *     
     */
    public Amendments.Head getHead() {
        return head;
    }

    /**
     * Sets the value of the head property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amendments.Head }
     *     
     */
    public void setHead(Amendments.Head value) {
        this.head = value;
    }

    /**
     * Gets the value of the body property.
     * 
     * @return
     *     possible object is
     *     {@link Amendments.Body }
     *     
     */
    public Amendments.Body getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amendments.Body }
     *     
     */
    public void setBody(Amendments.Body value) {
        this.body = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="pravni_osnov">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}clan"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element ref="{http://www.parlament.gov.rs/schema/amandman}amandman" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "pravniOsnov",
        "amandman"
    })
    public static class Body {

        @XmlElement(name = "pravni_osnov", namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
        protected Amendments.Body.PravniOsnov pravniOsnov;
        @XmlElement(namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
        protected List<Amendment> amandman;

        /**
         * Gets the value of the pravniOsnov property.
         * 
         * @return
         *     possible object is
         *     {@link Amendments.Body.PravniOsnov }
         *     
         */
        public Amendments.Body.PravniOsnov getPravniOsnov() {
            return pravniOsnov;
        }

        /**
         * Sets the value of the pravniOsnov property.
         * 
         * @param value
         *     allowed object is
         *     {@link Amendments.Body.PravniOsnov }
         *     
         */
        public void setPravniOsnov(Amendments.Body.PravniOsnov value) {
            this.pravniOsnov = value;
        }

        /**
         * Gets the value of the amandman property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the amandman property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAmandman().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Amendment }
         * 
         * 
         */
        public List<Amendment> getAmandman() {
            if (amandman == null) {
                amandman = new ArrayList<Amendment>();
            }
            return this.amandman;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}clan"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "clan"
        })
        public static class PravniOsnov {

            @XmlElement(namespace = "http://www.parlament.gov.rs/schema/elementi", required = true)
            protected Article clan;

            /**
             * Gets the value of the clan property.
             * 
             * @return
             *     possible object is
             *     {@link Article }
             *     
             */
            public Article getClan() {
                return clan;
            }

            /**
             * Sets the value of the clan property.
             * 
             * @param value
             *     allowed object is
             *     {@link Article }
             *     
             */
            public void setClan(Article value) {
                this.clan = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="datum_predloga">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>date">
     *                 &lt;anyAttribute processContents='lax'/>
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="datum_izglasavanja">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>date">
     *                 &lt;anyAttribute processContents='lax'/>
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="status">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                 &lt;anyAttribute processContents='lax'/>
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="mjesto" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="glasova_za">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>int">
     *                 &lt;anyAttribute processContents='lax'/>
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="glasova_protiv">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>int">
     *                 &lt;anyAttribute processContents='lax'/>
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="glasova_suzdrzani">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>int">
     *                 &lt;anyAttribute processContents='lax'/>
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="podnosilac">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="korisnikId" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="propis">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="propisId" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;anyAttribute processContents='lax'/>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "datumPredloga",
        "datumIzglasavanja",
        "status",
        "mjesto",
        "glasovaZa",
        "glasovaProtiv",
        "glasovaSuzdrzani",
        "podnosilac",
        "propis"
    })
    public static class Head {

        @XmlElement(name = "datum_predloga", namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
        protected Amendments.Head.DatumPredloga datumPredloga;
        @XmlElement(name = "datum_izglasavanja", namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
        protected Amendments.Head.DatumIzglasavanja datumIzglasavanja;
        @XmlElement(namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
        protected Amendments.Head.Status status;
        @XmlElement(namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
        protected String mjesto;
        @XmlElement(name = "glasova_za", namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
        protected Amendments.Head.GlasovaZa glasovaZa;
        @XmlElement(name = "glasova_protiv", namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
        protected Amendments.Head.GlasovaProtiv glasovaProtiv;
        @XmlElement(name = "glasova_suzdrzani", namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
        protected Amendments.Head.GlasovaSuzdrzani glasovaSuzdrzani;
        @XmlElement(namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
        protected Amendments.Head.Podnosilac podnosilac;
        @XmlElement(namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
        protected Amendments.Head.Propis propis;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<QName, String>();

        /**
         * Gets the value of the datumPredloga property.
         * 
         * @return
         *     possible object is
         *     {@link Amendments.Head.DatumPredloga }
         *     
         */
        public Amendments.Head.DatumPredloga getDatumPredloga() {
            return datumPredloga;
        }

        /**
         * Sets the value of the datumPredloga property.
         * 
         * @param value
         *     allowed object is
         *     {@link Amendments.Head.DatumPredloga }
         *     
         */
        public void setDatumPredloga(Amendments.Head.DatumPredloga value) {
            this.datumPredloga = value;
        }

        /**
         * Gets the value of the datumIzglasavanja property.
         * 
         * @return
         *     possible object is
         *     {@link Amendments.Head.DatumIzglasavanja }
         *     
         */
        public Amendments.Head.DatumIzglasavanja getDatumIzglasavanja() {
            return datumIzglasavanja;
        }

        /**
         * Sets the value of the datumIzglasavanja property.
         * 
         * @param value
         *     allowed object is
         *     {@link Amendments.Head.DatumIzglasavanja }
         *     
         */
        public void setDatumIzglasavanja(Amendments.Head.DatumIzglasavanja value) {
            this.datumIzglasavanja = value;
        }

        /**
         * Gets the value of the status property.
         * 
         * @return
         *     possible object is
         *     {@link Amendments.Head.Status }
         *     
         */
        public Amendments.Head.Status getStatus() {
            return status;
        }

        /**
         * Sets the value of the status property.
         * 
         * @param value
         *     allowed object is
         *     {@link Amendments.Head.Status }
         *     
         */
        public void setStatus(Amendments.Head.Status value) {
            this.status = value;
        }

        /**
         * Gets the value of the mjesto property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMjesto() {
            return mjesto;
        }

        /**
         * Sets the value of the mjesto property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMjesto(String value) {
            this.mjesto = value;
        }

        /**
         * Gets the value of the glasovaZa property.
         * 
         * @return
         *     possible object is
         *     {@link Amendments.Head.GlasovaZa }
         *     
         */
        public Amendments.Head.GlasovaZa getGlasovaZa() {
            return glasovaZa;
        }

        /**
         * Sets the value of the glasovaZa property.
         * 
         * @param value
         *     allowed object is
         *     {@link Amendments.Head.GlasovaZa }
         *     
         */
        public void setGlasovaZa(Amendments.Head.GlasovaZa value) {
            this.glasovaZa = value;
        }

        /**
         * Gets the value of the glasovaProtiv property.
         * 
         * @return
         *     possible object is
         *     {@link Amendments.Head.GlasovaProtiv }
         *     
         */
        public Amendments.Head.GlasovaProtiv getGlasovaProtiv() {
            return glasovaProtiv;
        }

        /**
         * Sets the value of the glasovaProtiv property.
         * 
         * @param value
         *     allowed object is
         *     {@link Amendments.Head.GlasovaProtiv }
         *     
         */
        public void setGlasovaProtiv(Amendments.Head.GlasovaProtiv value) {
            this.glasovaProtiv = value;
        }

        /**
         * Gets the value of the glasovaSuzdrzani property.
         * 
         * @return
         *     possible object is
         *     {@link Amendments.Head.GlasovaSuzdrzani }
         *     
         */
        public Amendments.Head.GlasovaSuzdrzani getGlasovaSuzdrzani() {
            return glasovaSuzdrzani;
        }

        /**
         * Sets the value of the glasovaSuzdrzani property.
         * 
         * @param value
         *     allowed object is
         *     {@link Amendments.Head.GlasovaSuzdrzani }
         *     
         */
        public void setGlasovaSuzdrzani(Amendments.Head.GlasovaSuzdrzani value) {
            this.glasovaSuzdrzani = value;
        }

        /**
         * Gets the value of the podnosilac property.
         * 
         * @return
         *     possible object is
         *     {@link Amendments.Head.Podnosilac }
         *     
         */
        public Amendments.Head.Podnosilac getPodnosilac() {
            return podnosilac;
        }

        /**
         * Sets the value of the podnosilac property.
         * 
         * @param value
         *     allowed object is
         *     {@link Amendments.Head.Podnosilac }
         *     
         */
        public void setPodnosilac(Amendments.Head.Podnosilac value) {
            this.podnosilac = value;
        }

        /**
         * Gets the value of the propis property.
         * 
         * @return
         *     possible object is
         *     {@link Amendments.Head.Propis }
         *     
         */
        public Amendments.Head.Propis getPropis() {
            return propis;
        }

        /**
         * Sets the value of the propis property.
         * 
         * @param value
         *     allowed object is
         *     {@link Amendments.Head.Propis }
         *     
         */
        public void setPropis(Amendments.Head.Propis value) {
            this.propis = value;
        }

        /**
         * Gets a map that contains attributes that aren't bound to any typed property on this class.
         * 
         * <p>
         * the map is keyed by the name of the attribute and 
         * the value is the string value of the attribute.
         * 
         * the map returned by this method is live, and you can add new attribute
         * by updating the map directly. Because of this design, there's no setter.
         * 
         * 
         * @return
         *     always non-null
         */
        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>date">
         *       &lt;anyAttribute processContents='lax'/>
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class DatumIzglasavanja {

            @XmlValue
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar value;
            @XmlAnyAttribute
            private Map<QName, String> otherAttributes = new HashMap<QName, String>();

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setValue(XMLGregorianCalendar value) {
                this.value = value;
            }

            /**
             * Gets a map that contains attributes that aren't bound to any typed property on this class.
             * 
             * <p>
             * the map is keyed by the name of the attribute and 
             * the value is the string value of the attribute.
             * 
             * the map returned by this method is live, and you can add new attribute
             * by updating the map directly. Because of this design, there's no setter.
             * 
             * 
             * @return
             *     always non-null
             */
            public Map<QName, String> getOtherAttributes() {
                return otherAttributes;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>date">
         *       &lt;anyAttribute processContents='lax'/>
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class DatumPredloga {

            @XmlValue
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar value;
            @XmlAnyAttribute
            private Map<QName, String> otherAttributes = new HashMap<QName, String>();

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setValue(XMLGregorianCalendar value) {
                this.value = value;
            }

            /**
             * Gets a map that contains attributes that aren't bound to any typed property on this class.
             * 
             * <p>
             * the map is keyed by the name of the attribute and 
             * the value is the string value of the attribute.
             * 
             * the map returned by this method is live, and you can add new attribute
             * by updating the map directly. Because of this design, there's no setter.
             * 
             * 
             * @return
             *     always non-null
             */
            public Map<QName, String> getOtherAttributes() {
                return otherAttributes;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>int">
         *       &lt;anyAttribute processContents='lax'/>
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class GlasovaProtiv {

            @XmlValue
            protected int value;
            @XmlAnyAttribute
            private Map<QName, String> otherAttributes = new HashMap<QName, String>();

            /**
             * Gets the value of the value property.
             * 
             */
            public int getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             */
            public void setValue(int value) {
                this.value = value;
            }

            /**
             * Gets a map that contains attributes that aren't bound to any typed property on this class.
             * 
             * <p>
             * the map is keyed by the name of the attribute and 
             * the value is the string value of the attribute.
             * 
             * the map returned by this method is live, and you can add new attribute
             * by updating the map directly. Because of this design, there's no setter.
             * 
             * 
             * @return
             *     always non-null
             */
            public Map<QName, String> getOtherAttributes() {
                return otherAttributes;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>int">
         *       &lt;anyAttribute processContents='lax'/>
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class GlasovaSuzdrzani {

            @XmlValue
            protected int value;
            @XmlAnyAttribute
            private Map<QName, String> otherAttributes = new HashMap<QName, String>();

            /**
             * Gets the value of the value property.
             * 
             */
            public int getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             */
            public void setValue(int value) {
                this.value = value;
            }

            /**
             * Gets a map that contains attributes that aren't bound to any typed property on this class.
             * 
             * <p>
             * the map is keyed by the name of the attribute and 
             * the value is the string value of the attribute.
             * 
             * the map returned by this method is live, and you can add new attribute
             * by updating the map directly. Because of this design, there's no setter.
             * 
             * 
             * @return
             *     always non-null
             */
            public Map<QName, String> getOtherAttributes() {
                return otherAttributes;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>int">
         *       &lt;anyAttribute processContents='lax'/>
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class GlasovaZa {

            @XmlValue
            protected int value;
            @XmlAnyAttribute
            private Map<QName, String> otherAttributes = new HashMap<QName, String>();

            /**
             * Gets the value of the value property.
             * 
             */
            public int getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             */
            public void setValue(int value) {
                this.value = value;
            }

            /**
             * Gets a map that contains attributes that aren't bound to any typed property on this class.
             * 
             * <p>
             * the map is keyed by the name of the attribute and 
             * the value is the string value of the attribute.
             * 
             * the map returned by this method is live, and you can add new attribute
             * by updating the map directly. Because of this design, there's no setter.
             * 
             * 
             * @return
             *     always non-null
             */
            public Map<QName, String> getOtherAttributes() {
                return otherAttributes;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="korisnikId" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Podnosilac {

            @XmlAttribute(name = "korisnikId", namespace = "http://www.parlament.gov.rs/schema/amandman")
            @XmlIDREF
            @XmlSchemaType(name = "IDREF")
            protected Object korisnikId;

            /**
             * Gets the value of the korisnikId property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getKorisnikId() {
                return korisnikId;
            }

            /**
             * Sets the value of the korisnikId property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setKorisnikId(Object value) {
                this.korisnikId = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="propisId" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Propis {

            @XmlAttribute(name = "propisId", namespace = "http://www.parlament.gov.rs/schema/amandman")
            @XmlIDREF
            @XmlSchemaType(name = "IDREF")
            protected Object propisId;

            /**
             * Gets the value of the propisId property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getPropisId() {
                return propisId;
            }

            /**
             * Sets the value of the propisId property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setPropisId(Object value) {
                this.propisId = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *       &lt;anyAttribute processContents='lax'/>
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class Status {

            @XmlValue
            protected String value;
            @XmlAnyAttribute
            private Map<QName, String> otherAttributes = new HashMap<QName, String>();

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets a map that contains attributes that aren't bound to any typed property on this class.
             * 
             * <p>
             * the map is keyed by the name of the attribute and 
             * the value is the string value of the attribute.
             * 
             * the map returned by this method is live, and you can add new attribute
             * by updating the map directly. Because of this design, there's no setter.
             * 
             * 
             * @return
             *     always non-null
             */
            public Map<QName, String> getOtherAttributes() {
                return otherAttributes;
            }

        }

    }

}
