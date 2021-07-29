package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ARIRB_NewData {
    @JsonProperty("status")
    @Expose
    public Integer status;
    @JsonProperty("state")
    @Expose
    public Integer state;
}
