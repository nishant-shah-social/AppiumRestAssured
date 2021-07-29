package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentUploadImageResponse {

	@JsonProperty("secure?")
	@Expose
	public Boolean secure;
	@JsonProperty("width")
	@Expose
	public Integer width;
	@JsonProperty("url-key")
	@Expose
	public String urlKey;
	@JsonProperty("size")
	@Expose
	public Integer size;
	@JsonProperty("thumbnail")
	@Expose
	public String thumbnail;
	@JsonProperty("thumbnail-key")
	@Expose
	public String thumbnailKey;
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
	public Boolean image;
	@JsonProperty("height")
	@Expose
	public Integer height;
}