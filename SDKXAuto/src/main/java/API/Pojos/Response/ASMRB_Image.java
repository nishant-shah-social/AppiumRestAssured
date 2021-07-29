package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ASMRB_Image {

	@JsonProperty("url")
	@Expose
	public String url;
	@JsonProperty("file-name")
	@Expose
	public String fileName;
	@JsonProperty("thumbnail")
	@Expose
	public String thumbnail;

}