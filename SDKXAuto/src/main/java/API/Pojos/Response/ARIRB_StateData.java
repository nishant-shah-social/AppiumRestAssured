package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ARIRB_StateData {
    @JsonProperty("state")
    @Expose
    public Integer state;
    @JsonProperty("state_updated_at")
    @Expose
    public String stateUpdatedAt;
    @JsonProperty("changed_at")
    @Expose
    public String changedAt;
    @JsonProperty("score")
    @Expose
    public Long score;
    @JsonProperty("agent")
    @Expose
    public String agent;
    @JsonProperty("agent_name")
    @Expose
    public String agentName;
    @JsonProperty("old_data")
    @Expose
    public ARIRB_OldData oldData;
    @JsonProperty("new_data")
    @Expose
    public ARIRB_NewData newData;
    @JsonProperty("message_origin")
    @Expose
    public Object messageOrigin;
}
