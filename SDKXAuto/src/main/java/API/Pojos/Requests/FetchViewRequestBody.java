package API.Pojos.Requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FetchViewRequestBody {
    @JsonProperty("view_type")
    @Expose
    public Integer viewType;
    @JsonProperty("publish_id")
    @Expose
    public Integer publishId;

}
