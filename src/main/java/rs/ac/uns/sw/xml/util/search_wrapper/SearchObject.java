package rs.ac.uns.sw.xml.util.search_wrapper;

import rs.ac.uns.sw.xml.domain.Law;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlSeeAlso({Law.class})
public class SearchObject {

    @XmlElement
    private Double confidence;

    @XmlElement
    private String path;

    @XmlElementWrapper(name = "preview")
    @XmlMixed
    private List<Object> preview;

    @XmlAnyElement(lax = true)
    private Object element;

    public SearchObject() {
        super();
    }

    public SearchObject(Double confidence, String path, List<Object> preview, Object element) {
        this.confidence = confidence;
        this.path = path;
        this.preview = preview;
        this.element = element;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Object> getPreview() {
        return preview;
    }

    public void setPreviews(List<Object> preview) {
        this.preview = preview;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }
}
