package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BuiltinView {
    @JsonProperty("publish_id")
    @Expose
    public String publishId;
    @JsonProperty("tracking_deleted_user")
    @Expose
    public Boolean trackingDeletedUser;
    @JsonProperty("is-builtin")
    @Expose
    public Boolean isBuiltin;
    @JsonProperty("name")
    @Expose
    public String name;
    @JsonProperty("is-active")
    @Expose
    public Boolean isActive;
    @JsonProperty("id")
    @Expose
    public String id;
    @JsonProperty("count")
    @Expose
    public Count count;
    @JsonProperty("is-shared")
    @Expose
    public Boolean isShared;
    @JsonProperty("owner")
    @Expose
    public String owner;
    @JsonProperty("view_type")
    @Expose
    public String viewType;
}
