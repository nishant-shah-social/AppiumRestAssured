package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ASMRB_Author {
    @JsonProperty("name")
    @Expose
    public String name;
    @JsonProperty("avatar")
    @Expose
    public String avatar;
    @JsonProperty("id")
    @Expose
    public String id;
}
