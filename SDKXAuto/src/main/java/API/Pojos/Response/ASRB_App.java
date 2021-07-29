package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ASRB_App {
    @JsonProperty("title")
    @Expose
    public String title;
    @JsonProperty("slug")
    @Expose
    public String slug;
    @JsonProperty("id")
    @Expose
    public String id;
    @JsonProperty("logo")
    @Expose
    public Object logo;
}
