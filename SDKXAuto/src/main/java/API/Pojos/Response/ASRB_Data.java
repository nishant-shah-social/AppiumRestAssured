package API.Pojos.Response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ASRB_Data {
    @JsonProperty("group_by_queues")
    @Expose
    public Boolean groupByQueues;
    @JsonProperty("chats_on_top")
    @Expose
    public Boolean chatsOnTop;
    @JsonProperty("issues")
    @Expose
    public List<ASRB_Issue> issues = null;
    @JsonProperty("mtq_enabled")
    @Expose
    public Boolean mtqEnabled;
    @JsonProperty("queues_enabled")
    @Expose
    public Boolean queuesEnabled;
    @JsonProperty("count")
    @Expose
    public ASRB_Count count;
    @JsonProperty("total-count")
    @Expose
    public Integer totalCount;
    @JsonProperty("more")
    @Expose
    public Object more;
    @JsonProperty("issue_sort_order")
    @Expose
    public String issueSortOrder;
    @JsonProperty("is_read_status_query")
    @Expose
    public Boolean isReadStatusQuery;
    @JsonProperty("queues")
    @Expose
    public List<Queue> queues = null;
}
