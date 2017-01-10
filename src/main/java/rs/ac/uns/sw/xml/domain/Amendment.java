
package rs.ac.uns.sw.xml.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for amandman element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="amandman">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="head">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="rjesenje">
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                           &lt;enumeration value="brisanje"/>
 *                           &lt;enumeration value="izmjena"/>
 *                           &lt;enumeration value="dopuna"/>
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/element>
 *                     &lt;element name="predmet">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;attribute name="predmetId" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="body">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="odredba" minOccurs="0">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;choice>
 *                               &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}clan"/>
 *                               &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}stav"/>
 *                               &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}tacka"/>
 *                               &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}podtacka"/>
 *                               &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}alineja"/>
 *                             &lt;/choice>
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element ref="{http://www.parlament.gov.rs/schema/amandman}obrazlozenje"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/sequence>
 *         &lt;attGroup ref="{http://www.parlament.gov.rs/schema/elementi}standard_attrs"/>
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
@XmlRootElement(name = "amandman", namespace = "http://www.parlament.gov.rs/schema/amandman")
public class Amendment {

    @XmlElement(namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
    protected Amendment.Head head;
    @XmlElement(namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
    protected Amendment.Body body;
    @XmlAttribute(name = "id", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String id;
    @XmlAttribute(name = "name")
    protected String name;

    /**
     * Gets the value of the head property.
     * 
     * @return
     *     possible object is
     *     {@link Amendment.Head }
     *     
     */
    public Amendment.Head getHead() {
        return head;
    }

    /**
     * Sets the value of the head property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amendment.Head }
     *     
     */
    public void setHead(Amendment.Head value) {
        this.head = value;
    }

    /**
     * Gets the value of the body property.
     * 
     * @return
     *     possible object is
     *     {@link Amendment.Body }
     *     
     */
    public Amendment.Body getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *     allowed object is
     *     {@link Amendment.Body }
     *     
     */
    public void setBody(Amendment.Body value) {
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="odredba" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;choice>
     *                   &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}clan"/>
     *                   &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}stav"/>
     *                   &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}tacka"/>
     *                   &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}podtacka"/>
     *                   &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}alineja"/>
     *                 &lt;/choice>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element ref="{http://www.parlament.gov.rs/schema/amandman}obrazlozenje"/>
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
        "odredba",
        "obrazlozenje"
    })
    public static class Body {

        @XmlElement(namespace = "http://www.parlament.gov.rs/schema/amandman")
        protected Amendment.Body.Odredba odredba;
        @XmlElement(namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
        protected Explanation obrazlozenje;

        /**
         * Gets the value of the odredba property.
         * 
         * @return
         *     possible object is
         *     {@link Amendment.Body.Odredba }
         *     
         */
        public Amendment.Body.Odredba getOdredba() {
            return odredba;
        }

        /**
         * Sets the value of the odredba property.
         * 
         * @param value
         *     allowed object is
         *     {@link Amendment.Body.Odredba }
         *     
         */
        public void setOdredba(Amendment.Body.Odredba value) {
            this.odredba = value;
        }

        /**
         * Gets the value of the obrazlozenje property.
         * 
         * @return
         *     possible object is
         *     {@link Explanation }
         *     
         */
        public Explanation getObrazlozenje() {
            return obrazlozenje;
        }

        /**
         * Sets the value of the obrazlozenje property.
         * 
         * @param value
         *     allowed object is
         *     {@link Explanation }
         *     
         */
        public void setObrazlozenje(Explanation value) {
            this.obrazlozenje = value;
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
         *       &lt;choice>
         *         &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}clan"/>
         *         &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}stav"/>
         *         &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}tacka"/>
         *         &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}podtacka"/>
         *         &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}alineja"/>
         *       &lt;/choice>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "clan",
            "stav",
            "tacka",
            "podtacka",
            "alineja"
        })
        public static class Odredba {

            @XmlElement(namespace = "http://www.parlament.gov.rs/schema/elementi")
            protected Article clan;
            @XmlElement(namespace = "http://www.parlament.gov.rs/schema/elementi")
            protected Paragraph stav;
            @XmlElement(namespace = "http://www.parlament.gov.rs/schema/elementi")
            protected Clause tacka;
            @XmlElement(namespace = "http://www.parlament.gov.rs/schema/elementi")
            protected Subclause podtacka;
            @XmlElement(namespace = "http://www.parlament.gov.rs/schema/elementi")
            protected Item alineja;

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

            /**
             * Gets the value of the stav property.
             * 
             * @return
             *     possible object is
             *     {@link Paragraph }
             *     
             */
            public Paragraph getStav() {
                return stav;
            }

            /**
             * Sets the value of the stav property.
             * 
             * @param value
             *     allowed object is
             *     {@link Paragraph }
             *     
             */
            public void setStav(Paragraph value) {
                this.stav = value;
            }

            /**
             * Gets the value of the tacka property.
             * 
             * @return
             *     possible object is
             *     {@link Clause }
             *     
             */
            public Clause getTacka() {
                return tacka;
            }

            /**
             * Sets the value of the tacka property.
             * 
             * @param value
             *     allowed object is
             *     {@link Clause }
             *     
             */
            public void setTacka(Clause value) {
                this.tacka = value;
            }

            /**
             * Gets the value of the podtacka property.
             * 
             * @return
             *     possible object is
             *     {@link Subclause }
             *     
             */
            public Subclause getPodtacka() {
                return podtacka;
            }

            /**
             * Sets the value of the podtacka property.
             * 
             * @param value
             *     allowed object is
             *     {@link Subclause }
             *     
             */
            public void setPodtacka(Subclause value) {
                this.podtacka = value;
            }

            /**
             * Gets the value of the alineja property.
             * 
             * @return
             *     possible object is
             *     {@link Item }
             *     
             */
            public Item getAlineja() {
                return alineja;
            }

            /**
             * Sets the value of the alineja property.
             * 
             * @param value
             *     allowed object is
             *     {@link Item }
             *     
             */
            public void setAlineja(Item value) {
                this.alineja = value;
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
     *         &lt;element name="rjesenje">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;enumeration value="brisanje"/>
     *               &lt;enumeration value="izmjena"/>
     *               &lt;enumeration value="dopuna"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="predmet">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="predmetId" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "rjesenje",
        "predmet"
    })
    public static class Head {

        @XmlElement(namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
        protected String rjesenje;
        @XmlElement(namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
        protected Amendment.Head.Predmet predmet;

        /**
         * Gets the value of the rjesenje property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRjesenje() {
            return rjesenje;
        }

        /**
         * Sets the value of the rjesenje property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRjesenje(String value) {
            this.rjesenje = value;
        }

        /**
         * Gets the value of the predmet property.
         * 
         * @return
         *     possible object is
         *     {@link Amendment.Head.Predmet }
         *     
         */
        public Amendment.Head.Predmet getPredmet() {
            return predmet;
        }

        /**
         * Sets the value of the predmet property.
         * 
         * @param value
         *     allowed object is
         *     {@link Amendment.Head.Predmet }
         *     
         */
        public void setPredmet(Amendment.Head.Predmet value) {
            this.predmet = value;
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
         *       &lt;attribute name="predmetId" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Predmet {

            @XmlAttribute(name = "predmetId", namespace = "http://www.parlament.gov.rs/schema/amandman")
            @XmlIDREF
            @XmlSchemaType(name = "IDREF")
            protected Object predmetId;

            /**
             * Gets the value of the predmetId property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getPredmetId() {
                return predmetId;
            }

            /**
             * Sets the value of the predmetId property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setPredmetId(Object value) {
                this.predmetId = value;
            }

        }

    }

}
