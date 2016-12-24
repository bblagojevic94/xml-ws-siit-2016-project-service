
package rs.ac.uns.sw.xml.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *           &lt;element name="razlog" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *           &lt;element name="objasnjene_predlozenog_rjesenja" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *           &lt;element name="cilj" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *           &lt;element name="uticaj_na_budzetska_sredstva" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
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
    protected Object razlog;
    @XmlElement(name = "objasnjene_predlozenog_rjesenja", namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
    protected Object objasnjenePredlozenogRjesenja;
    @XmlElement(namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
    protected Object cilj;
    @XmlElement(name = "uticaj_na_budzetska_sredstva", namespace = "http://www.parlament.gov.rs/schema/amandman", required = true)
    protected Object uticajNaBudzetskaSredstva;

    /**
     * Gets the value of the razlog property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getRazlog() {
        return razlog;
    }

    /**
     * Sets the value of the razlog property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRazlog(Object value) {
        this.razlog = value;
    }

    /**
     * Gets the value of the objasnjenePredlozenogRjesenja property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getObjasnjenePredlozenogRjesenja() {
        return objasnjenePredlozenogRjesenja;
    }

    /**
     * Sets the value of the objasnjenePredlozenogRjesenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setObjasnjenePredlozenogRjesenja(Object value) {
        this.objasnjenePredlozenogRjesenja = value;
    }

    /**
     * Gets the value of the cilj property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getCilj() {
        return cilj;
    }

    /**
     * Sets the value of the cilj property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setCilj(Object value) {
        this.cilj = value;
    }

    /**
     * Gets the value of the uticajNaBudzetskaSredstva property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getUticajNaBudzetskaSredstva() {
        return uticajNaBudzetskaSredstva;
    }

    /**
     * Sets the value of the uticajNaBudzetskaSredstva property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setUticajNaBudzetskaSredstva(Object value) {
        this.uticajNaBudzetskaSredstva = value;
    }

}
