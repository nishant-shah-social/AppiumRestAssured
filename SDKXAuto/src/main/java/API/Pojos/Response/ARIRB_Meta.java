package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ARIRB_Meta {
    @JsonProperty("detected_language")
    @Expose
    public String detectedLanguage;
    @JsonProperty("custom_meta")
    @Expose
    public String customMeta;
    @JsonProperty("timezone")
    @Expose
    public String timezone;
    @JsonProperty("sdkx")
    @Expose
    public Boolean sdkx;
    @JsonProperty("extra")
    @Expose
    public ARIRB_Extra extra;
    @JsonProperty("first_end_user_message_id")
    @Expose
    public String firstEndUserMessageId;
    @JsonProperty("language")
    @Expose
    public String language;
    @JsonProperty("developer_set_language")
    @Expose
    public String developerSetLanguage;
    @JsonProperty("device_info")
    @Expose
    public ARIRB_DeviceInfo deviceInfo;
    @JsonProperty("lite_sdk_os")
    @Expose
    public String liteSdkOs;
    @JsonProperty("device_language")
    @Expose
    public String deviceLanguage;

}
