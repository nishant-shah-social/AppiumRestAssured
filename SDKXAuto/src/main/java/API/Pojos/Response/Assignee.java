package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Assignee {
    @JsonProperty("name")
    @Expose
    public String name;
    @JsonProperty("avatar")
    @Expose
    public String avatar;
    @JsonProperty("id")
    @Expose
    public String id;
    @JsonProperty("timezone")
    @Expose
    public String timezone;
    @JsonProperty("email")
    @Expose
    public String email;
    @JsonProperty("is_deleted")
    @Expose
    public Boolean isDeleted;
    @JsonProperty("is_bot")
    @Expose
    public Boolean isBot;
    @JsonProperty("selected")
    @Expose
    public Boolean selected;
}
