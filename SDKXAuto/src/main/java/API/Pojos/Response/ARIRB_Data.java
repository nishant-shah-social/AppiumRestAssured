package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ARIRB_Data {
    @JsonProperty("queue_backlog_start_ts")
    @Expose
    public Object queueBacklogStartTs;
    @JsonProperty("meta")
    @Expose
    public ARIRB_Meta meta;
    @JsonProperty("changed_at")
    @Expose
    public String changedAt;
    @JsonProperty("queue_interaction_start_ts")
    @Expose
    public Object queueInteractionStartTs;
    @JsonProperty("csat_expiry_at")
    @Expose
    public Long csatExpiryAt;
    @JsonProperty("updated_at")
    @Expose
    public String updatedAt;
    @JsonProperty("status")
    @Expose
    public Integer status;
    @JsonProperty("status_updated_at")
    @Expose
    public String statusUpdatedAt;
    @JsonProperty("state_text")
    @Expose
    public String stateText;
    @JsonProperty("state_data")
    @Expose
    public ARIRB_StateData stateData;
    @JsonProperty("resolution_question_expiry_at")
    @Expose
    public Long resolutionQuestionExpiryAt;
}
