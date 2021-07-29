package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ASMRB_Data {
	@JsonProperty("message")
	@Expose
	public ASMRB_Message message;

	@JsonProperty("id")
	@Expose
	public String id;
}
