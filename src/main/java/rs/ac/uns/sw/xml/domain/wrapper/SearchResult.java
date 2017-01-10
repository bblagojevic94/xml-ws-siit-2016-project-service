package rs.ac.uns.sw.xml.domain.wrapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import rs.ac.uns.sw.xml.domain.Law;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Law.class})
public class SearchResult extends ResourceSupport {

    private List<Object> set;

    @SuppressWarnings("unused")
    public SearchResult() {
        super();
    }

    @JsonCreator
    public SearchResult(@JsonProperty("set") List<Object> set) {
        this.set = set;
    }

    @XmlMixed
    @XmlAnyElement(lax = true)
    public List<Object> getSet() {
        return set;
    }
}
