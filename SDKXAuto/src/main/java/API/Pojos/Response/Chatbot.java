package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Chatbot {
    @JsonProperty("name")
    @Expose
    public String name;
    @JsonProperty("id")
    @Expose
    public String id;
}
