package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ARIRB_Extra {
    @JsonProperty("library-version")
    @Expose
    public String libraryVersion;
    @JsonProperty("webchat-library-version")
    @Expose
    public String webchatLibraryVersion;
    @JsonProperty("chatbots-supported?")
    @Expose
    public Boolean chatbotsSupported;
}
