package API.Pojos.Response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ASMRB_Message {
    @JsonProperty("issue_id")
    @Expose
    public String issueId;
    @JsonProperty("is_redacted")
    @Expose
    public Boolean isRedacted;
    @JsonProperty("is-right")
    @Expose
    public Boolean isRight;
    @JsonProperty("created_at_humanize")
    @Expose
    public String createdAtHumanize;
    @JsonProperty("attachments")
    @Expose
    public List<ASMRB_Attachment> attachments = null;
    @JsonProperty("created_at_unix_ts")
    @Expose
    public Integer createdAtUnixTs;
    @JsonProperty("render_info")
    @Expose
    public ASMRB_RenderInfo renderInfo;
    @JsonProperty("type")
    @Expose
    public String type;
    @JsonProperty("created_at_ts")
    @Expose
    public String createdAtTs;
    @JsonProperty("created_at_epoch")
    @Expose
    public Long createdAtEpoch;
    @JsonProperty("author")
    @Expose
    public ASMRB_Author author;
    @JsonProperty("created_at_iso")
    @Expose
    public String createdAtIso;
    @JsonProperty("id")
    @Expose
    public String id;
    @JsonProperty("is_you")
    @Expose
    public Boolean isYou;
    @JsonProperty("by_admin")
    @Expose
    public Boolean byAdmin;
    @JsonProperty("has_attachments")
    @Expose
    public Boolean hasAttachments;
    @JsonProperty("origin")
    @Expose
    public String origin;
    @JsonProperty("body")
    @Expose
    public String body;
    @JsonProperty("review-info")
    @Expose
    public Object reviewInfo;
    @JsonProperty("created_at")
    @Expose
    public String createdAt;
}
