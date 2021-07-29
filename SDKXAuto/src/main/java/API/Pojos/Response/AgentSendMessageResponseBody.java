package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentSendMessageResponseBody {
    @JsonProperty("data")
    @Expose
    public ASMRB_Data data;
}
