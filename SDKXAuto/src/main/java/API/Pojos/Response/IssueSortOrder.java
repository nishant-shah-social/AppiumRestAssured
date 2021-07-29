package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueSortOrder {
    @JsonProperty("val")
    @Expose
    public String val;
    @JsonProperty("text")
    @Expose
    public String text;
    @JsonProperty("is-active")
    @Expose
    public Boolean isActive;
}
