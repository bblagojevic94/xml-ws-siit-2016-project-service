package rs.ac.uns.sw.xml.domain.wrapper;

import rs.ac.uns.sw.xml.domain.Law;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Law.class})
public class SearchResult {

    private List<Object> set;

    @SuppressWarnings("unused")
    public SearchResult() {
        super();
    }

    public SearchResult(List<Object> set) {
        this.set = set;
    }

    @XmlMixed
    @XmlAnyElement(lax = true)
    public List<Object> getSet() {
        return set;
    }
}
