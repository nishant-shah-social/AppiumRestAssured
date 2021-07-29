package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ARIRB_OldData {
    @JsonProperty("status")
    @Expose
    public Integer status;
    @JsonProperty("conv-initiated?")
    @Expose
    public Boolean convInitiated;
    @JsonProperty("fresh?")
    @Expose
    public Boolean fresh;
    @JsonProperty("queue_id")
    @Expose
    public String queueId;
    @JsonProperty("assignee")
    @Expose
    public String assignee;
    @JsonProperty("state")
    @Expose
    public Integer state;
}
