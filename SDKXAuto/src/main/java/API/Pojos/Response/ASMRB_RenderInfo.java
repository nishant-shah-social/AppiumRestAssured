package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ASMRB_RenderInfo {
    @JsonProperty("is_chatbot_message")
    @Expose
    public Boolean isChatbotMessage;
}
