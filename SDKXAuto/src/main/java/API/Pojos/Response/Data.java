package API.Pojos.Response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
	@JsonProperty("group_by_queues")
	public Boolean groupByQueues;
	@JsonProperty("assignee_list")
	public List<Assignee> assigneeList = null;
	@JsonProperty("chats_on_top")
	@Expose
	public Boolean chatsOnTop;
	@JsonProperty("issues")
	@Expose
	public List<Issue> issues = null;
	@JsonProperty("reorder_ssv")
	@Expose
	public Boolean reorderSsv;
	@JsonProperty("ssv_folders")
	@Expose
	public List<Object> ssvFolders = null;
	@JsonProperty("builtin_views")
	public List<BuiltinView> builtinViews = null;
	@JsonProperty("ticket-forwarding")
	@Expose
	public Object ticketForwarding;
	@JsonProperty("is_admin")
	@Expose
	public Boolean isAdmin;
	@JsonProperty("reorder_sv")
	@Expose
	public Boolean reorderSv;
	@JsonProperty("empty")
	@Expose
	public Boolean empty;
	@JsonProperty("msv_folders")
	@Expose
	public List<Object> msvFolders = null;
	@JsonProperty("mtq_enabled")
	@Expose
	public Boolean mtqEnabled;
	@JsonProperty("is_agent")
	@Expose
	public Boolean isAgent;
	@JsonProperty("assign_issues_to_me_enabled")
	@Expose
	public Boolean assignIssuesToMeEnabled;
	@JsonProperty("is_supervisor")
	@Expose
	public Boolean isSupervisor;
	@JsonProperty("bulk_actions_enabled")
	@Expose
	public Boolean bulkActionsEnabled;
	@JsonProperty("queues_enabled")
	@Expose
	public Boolean queuesEnabled;
	@JsonProperty("issue_sort_orders")
	@Expose
	public List<IssueSortOrder> issueSortOrders = null;
	@JsonProperty("count")
	@Expose
	public Count__1 count;
	@JsonProperty("is_view_unavailable")
	@Expose
	public Boolean isViewUnavailable;
	@JsonProperty("total-count")
	@Expose
	public Integer totalCount;
	@JsonProperty("no_shared_views")
	@Expose
	public Boolean noSharedViews;
	@JsonProperty("more")
	@Expose
	public Boolean more;
	@JsonProperty("issue_sort_order")
	@Expose
	public String issueSortOrder;
	@JsonProperty("queues")
	@Expose
	public List<Queue> queues = null;
	@JsonProperty("chatbots")
	@Expose
	public List<Chatbot> chatbots = null;
}
