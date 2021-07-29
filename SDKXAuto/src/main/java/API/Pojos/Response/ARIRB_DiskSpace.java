package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ARIRB_DiskSpace {
    @JsonProperty("total-space-phone")
    @Expose
    public String totalSpacePhone;
    @JsonProperty("free-space-phone")
    @Expose
    public String freeSpacePhone;
}
