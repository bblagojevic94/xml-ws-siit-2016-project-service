package rs.ac.uns.sw.xml.util.search_wrapper;

import rs.ac.uns.sw.xml.domain.Law;

import javax.xml.bind.annotation.*;
import java.util.List;

@SuppressWarnings("unused")
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

    @XmlElement
    private Metadata metadata;

    public SearchObject() {
        super();
    }

    public SearchObject(Double confidence, String path, List<Object> preview, Metadata metadata) {
        this.setConfidence(confidence);
        this.setPath(path);
        this.setPreviews(preview);
        this.setMetadata(metadata);

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {

        try {
            this.path = "/api" + (path.split("\"")[1]).split("\\.")[0];
        }
        catch (Exception e) {
            this.path = path;
        }
    }

    public List<Object> getPreview() {
        return preview;
    }

    public void setPreviews(List<Object> preview) {
        this.preview = preview;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}
