
package rs.ac.uns.sw.xml.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for obrazlozenje element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="obrazlozenje">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="razlog">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}ref" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="objasnjene_predlozenog_rjesenja">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}ref" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="cilj">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}ref" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="uticaj_na_budzetska_sredstva">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}ref" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/sequence>
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
    "razlog",
    "objasnjenePredlozenogRjesenja",
    "cilj",
    "uticajNaBudzetskaSredstva"
})
@XmlRootElement(name = "obrazlozenje", namespace = "http://www.parlament.gov.rs/schema/amandman")
public class Explanation {

    @XmlElement(namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
    protected Explanation.Razlog razlog;
    @XmlElement(name = "objasnjene_predlozenog_rjesenja", namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
    protected Explanation.ObjasnjenePredlozenogRjesenja objasnjenePredlozenogRjesenja;
    @XmlElement(namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
    protected Explanation.Cilj cilj;
    @XmlElement(name = "uticaj_na_budzetska_sredstva", namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
    protected Explanation.UticajNaBudzetskaSredstva uticajNaBudzetskaSredstva;

    /**
     * Gets the value of the razlog property.
     * 
     * @return
     *     possible object is
     *     {@link Explanation.Razlog }
     *     
     */
    public Explanation.Razlog getRazlog() {
        return razlog;
    }

    /**
     * Sets the value of the razlog property.
     * 
     * @param value
     *     allowed object is
     *     {@link Explanation.Razlog }
     *     
     */
    public void setRazlog(Explanation.Razlog value) {
        this.razlog = value;
    }

    /**
     * Gets the value of the objasnjenePredlozenogRjesenja property.
     * 
     * @return
     *     possible object is
     *     {@link Explanation.ObjasnjenePredlozenogRjesenja }
     *     
     */
    public Explanation.ObjasnjenePredlozenogRjesenja getObjasnjenePredlozenogRjesenja() {
        return objasnjenePredlozenogRjesenja;
    }

    /**
     * Sets the value of the objasnjenePredlozenogRjesenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Explanation.ObjasnjenePredlozenogRjesenja }
     *     
     */
    public void setObjasnjenePredlozenogRjesenja(Explanation.ObjasnjenePredlozenogRjesenja value) {
        this.objasnjenePredlozenogRjesenja = value;
    }

    /**
     * Gets the value of the cilj property.
     * 
     * @return
     *     possible object is
     *     {@link Explanation.Cilj }
     *     
     */
    public Explanation.Cilj getCilj() {
        return cilj;
    }

    /**
     * Sets the value of the cilj property.
     * 
     * @param value
     *     allowed object is
     *     {@link Explanation.Cilj }
     *     
     */
    public void setCilj(Explanation.Cilj value) {
        this.cilj = value;
    }

    /**
     * Gets the value of the uticajNaBudzetskaSredstva property.
     * 
     * @return
     *     possible object is
     *     {@link Explanation.UticajNaBudzetskaSredstva }
     *     
     */
    public Explanation.UticajNaBudzetskaSredstva getUticajNaBudzetskaSredstva() {
        return uticajNaBudzetskaSredstva;
    }

    /**
     * Sets the value of the uticajNaBudzetskaSredstva property.
     * 
     * @param value
     *     allowed object is
     *     {@link Explanation.UticajNaBudzetskaSredstva }
     *     
     */
    public void setUticajNaBudzetskaSredstva(Explanation.UticajNaBudzetskaSredstva value) {
        this.uticajNaBudzetskaSredstva = value;
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
     *         &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}ref" maxOccurs="unbounded" minOccurs="0"/>
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
        "content"
    })
    public static class Cilj {

        @XmlElementRef(name = "ref", namespace = "http://www.parlament.gov.rs/schema/elementi", type = Ref.class, required = false)
        @XmlMixed
        protected List<Object> content;

        /**
         * Gets the value of the content property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the content property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * {@link Ref }
         * 
         * 
         */
        public List<Object> getContent() {
            if (content == null) {
                content = new ArrayList<Object>();
            }
            return this.content;
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
     *         &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}ref" maxOccurs="unbounded" minOccurs="0"/>
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
        "content"
    })
    public static class ObjasnjenePredlozenogRjesenja {

        @XmlElementRef(name = "ref", namespace = "http://www.parlament.gov.rs/schema/elementi", type = Ref.class, required = false)
        @XmlMixed
        protected List<Object> content;

        /**
         * Gets the value of the content property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the content property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * {@link Ref }
         * 
         * 
         */
        public List<Object> getContent() {
            if (content == null) {
                content = new ArrayList<Object>();
            }
            return this.content;
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
     *         &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}ref" maxOccurs="unbounded" minOccurs="0"/>
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
        "content"
    })
    public static class Razlog {

        @XmlElementRef(name = "ref", namespace = "http://www.parlament.gov.rs/schema/elementi", type = Ref.class, required = false)
        @XmlMixed
        protected List<Object> content;

        /**
         * Gets the value of the content property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the content property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * {@link Ref }
         * 
         * 
         */
        public List<Object> getContent() {
            if (content == null) {
                content = new ArrayList<Object>();
            }
            return this.content;
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
     *         &lt;element ref="{http://www.parlament.gov.rs/schema/elementi}ref" maxOccurs="unbounded" minOccurs="0"/>
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
        "content"
    })
    public static class UticajNaBudzetskaSredstva {

        @XmlElementRef(name = "ref", namespace = "http://www.parlament.gov.rs/schema/elementi", type = Ref.class, required = false)
        @XmlMixed
        protected List<Object> content;

        /**
         * Gets the value of the content property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the content property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * {@link Ref }
         * 
         * 
         */
        public List<Object> getContent() {
            if (content == null) {
                content = new ArrayList<Object>();
            }
            return this.content;
        }

    }

}
