package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Count__1 {
    @JsonProperty("issue")
    @Expose
    public Integer issue;
    @JsonProperty("chat")
    @Expose
    public Integer chat;
}
