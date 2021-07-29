package API.Pojos.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Templates {
	@JsonProperty("templates/www/admin/issues.html")
	@Expose
	public String templatesWwwAdminIssuesHtml;
}
