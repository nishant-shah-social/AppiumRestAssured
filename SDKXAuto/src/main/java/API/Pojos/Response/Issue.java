package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Issue {
    @JsonProperty("tags")
    @Expose
    public Object tags;
    @JsonProperty("created-at-ts")
    @Expose
    public Integer createdAtTs;
    @JsonProperty("publish_id")
    @Expose
    public String publishId;
    @JsonProperty("state-text")
    @Expose
    public String stateText;
    @JsonProperty("language_code")
    @Expose
    public String languageCode;
    @JsonProperty("platform_id")
    @Expose
    public String platformId;
    @JsonProperty("platform_type")
    @Expose
    public String platformType;
    @JsonProperty("agent-replied")
    @Expose
    public Boolean agentReplied;
    @JsonProperty("is-fresh")
    @Expose
    public Boolean isFresh;
    @JsonProperty("app_id")
    @Expose
    public String appId;
    @JsonProperty("changed-at-basic")
    @Expose
    public String changedAtBasic;
    @JsonProperty("changed-at")
    @Expose
    public String changedAt;
    @JsonProperty("state")
    @Expose
    public Integer state;
    @JsonProperty("is_chat")
    @Expose
    public Boolean isChat;
    @JsonProperty("has_app_logo")
    @Expose
    public Boolean hasAppLogo;
    @JsonProperty("title")
    @Expose
    public String title;
    @JsonProperty("changed-at-ts")
    @Expose
    public Integer changedAtTs;
    @JsonProperty("id")
    @Expose
    public String id;
    @JsonProperty("created-at-basic")
    @Expose
    public String createdAtBasic;
    @JsonProperty("score")
    @Expose
    public Integer score;
    @JsonProperty("is_user_activity")
    @Expose
    public Object isUserActivity;
    @JsonProperty("response-required")
    @Expose
    public Integer responseRequired;
    @JsonProperty("queue_id")
    @Expose
    public String queueId;
    @JsonProperty("platform-label")
    @Expose
    public String platformLabel;
    @JsonProperty("app_logo")
    @Expose
    public Object appLogo;
    @JsonProperty("created_at")
    @Expose
    public String createdAt;
}
