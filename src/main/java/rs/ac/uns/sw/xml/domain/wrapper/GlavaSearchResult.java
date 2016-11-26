package rs.ac.uns.sw.xml.domain.wrapper;

import rs.ac.uns.sw.xml.domain.Glava;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.NONE)
public class GlavaSearchResult {

    private List<Glava> glave;

    public GlavaSearchResult() {
        super();
    }

    public GlavaSearchResult(List<Glava> glave) {
        this.glave = glave;
    }

    @XmlElementWrapper(name = "glave")
    @XmlElement(name = "glava")
    public List<Glava> getGlave() {
        return glave;
    }
}
