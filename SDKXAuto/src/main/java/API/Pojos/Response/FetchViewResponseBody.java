package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FetchViewResponseBody {
    @JsonProperty("templates")
    @Expose
    public Templates templates;
    @JsonProperty("template-name")
    public String templateName;
    @JsonProperty("data")
    @Expose
    public Data data;
}
