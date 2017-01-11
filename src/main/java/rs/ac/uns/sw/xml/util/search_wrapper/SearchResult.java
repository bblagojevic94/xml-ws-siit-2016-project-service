package rs.ac.uns.sw.xml.util.search_wrapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.NONE)
public class SearchResult {

    @XmlElement
    private List<SearchObject> set;

    @SuppressWarnings("unused")
    public SearchResult() {
        super();
    }

    @JsonCreator
    public SearchResult(@JsonProperty("set") List<SearchObject> set) {
        this.set = set;
    }

    public List<SearchObject> getSet() {
        return set;
    }
}
