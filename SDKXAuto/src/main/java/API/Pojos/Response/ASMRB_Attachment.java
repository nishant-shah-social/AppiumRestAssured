package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ASMRB_Attachment {

	@JsonProperty("secure?")
	@Expose
	public Boolean secure;
	@JsonProperty("url-key")
	@Expose
	public String urlKey;
	@JsonProperty("size")
	@Expose
	public Integer size;
	@JsonProperty("thumbnail")
	@Expose
	public String thumbnail;
	@JsonProperty("id")
	@Expose
	public String id;
	@JsonProperty("file-name")
	@Expose
	public String fileName;
	@JsonProperty("url")
	@Expose
	public String url;
	@JsonProperty("content-type")
	@Expose
	public String contentType;
	@JsonProperty("image")
	@Expose
	public boolean image;

}