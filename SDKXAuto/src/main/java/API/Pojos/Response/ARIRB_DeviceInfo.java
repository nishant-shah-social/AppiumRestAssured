package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ARIRB_DeviceInfo {
    @JsonProperty("library-version")
    @Expose
    public String libraryVersion;
    @JsonProperty("disk-space")
    @Expose
    public ARIRB_DiskSpace diskSpace;
    @JsonProperty("application-name")
    @Expose
    public String applicationName;
    @JsonProperty("battery-status")
    @Expose
    public String batteryStatus;
    @JsonProperty("device-model")
    @Expose
    public String deviceModel;
    @JsonProperty("battery-level")
    @Expose
    public String batteryLevel;
    @JsonProperty("country-code")
    @Expose
    public String countryCode;
    @JsonProperty("network-type")
    @Expose
    public String networkType;
    @JsonProperty("carrier-name")
    @Expose
    public String carrierName;
    @JsonProperty("os-version")
    @Expose
    public String osVersion;
    @JsonProperty("application-version")
    @Expose
    public String applicationVersion;
    @JsonProperty("application-identifier")
    @Expose
    public String applicationIdentifier;
    @JsonProperty("platform")
    @Expose
    public String platform;
}
