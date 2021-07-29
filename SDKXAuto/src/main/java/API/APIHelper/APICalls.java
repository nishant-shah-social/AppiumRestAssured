package API.APIHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.jayway.jsonpath.JsonPath;

import API.Pojos.Requests.FetchViewRequestBody;
import API.Pojos.Response.AdvancedSearchResponseBody;
import API.Pojos.Response.AgentResolveIssueResponseBody;
import API.Pojos.Response.AgentSendMessageResponseBody;
import API.Pojos.Response.AgentUploadImageResponse;
import API.Pojos.Response.BuiltinView;
import API.Pojos.Response.FetchViewResponseBody;
import UI.AppiumUtilities.AppiumUtil;
import io.restassured.RestAssured;
import io.restassured.config.RedirectConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;


public class APICalls {

	private Cookies cookies;
	private Cookies newCookies;
	private String domain, extendedDomain, extension, adminUsername, adminPassword;

	public APICalls() {
		String environmentPath = System.getProperty("user.dir") + "/src/main/resources/Capabilities/Environment.json";
		try {
			domain = JsonPath.read(AppiumUtil.readFileAsString(environmentPath), "$.domain").toString();
			extendedDomain = JsonPath.read(AppiumUtil.readFileAsString(environmentPath), "$.extendedDomain").toString();
			extension = JsonPath.read(AppiumUtil.readFileAsString(environmentPath), "$.extension").toString();
			adminUsername = JsonPath.read(AppiumUtil.readFileAsString(environmentPath), "$.adminUsername").toString();
			adminPassword = JsonPath.read(AppiumUtil.readFileAsString(environmentPath), "$.adminPassword").toString();
		} catch (Exception e) {
			// TODO: Need to add a log statement
			e.printStackTrace();
		}
	}

	public void xhrLogin() {
		String baseUri = "https://" + domain + "." + extendedDomain + "." + extension;
		RestAssured.baseURI = baseUri;
		cookies = RestAssured.given().header("content-type", "application/x-www-form-urlencoded").header("User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36")
				.get(Routes.login).then().extract().response().detailedCookies();

		HashMap<String, String> formParams = new HashMap<>();
		formParams.put("username", adminUsername);
		formParams.put("password", adminPassword);
		formParams.put("_csrf_token", cookies.getValue("_csrf_token"));

		newCookies = RestAssured.given()
				.config(RestAssured.config().redirect(RedirectConfig.redirectConfig().followRedirects(false)))
				.header("Content-Type", "application/x-www-form-urlencoded")
				.header("User-Agent",
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36")
				.header("Referer", baseUri + Routes.login).contentType(ContentType.URLENC.withCharset("UTF-8"))
				.cookies(cookies).params(formParams).post(Routes.login).then().extract().response()
				.getDetailedCookies();
	}

	
	public String getNewIssuesViewId() {
		FetchViewRequestBody fetchViewRequestBody = new FetchViewRequestBody();
		fetchViewRequestBody.publishId = 5;
		fetchViewRequestBody.viewType = 0;

		String baseUri = "https://" + domain + "." + extendedDomain + "." + extension;
		RestAssured.baseURI = baseUri;

		FetchViewResponseBody fetchViewResponseBody = RestAssured.given().header("User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36")
				.header("Host", domain + "." + extendedDomain + "." + extension)
				.header("X-Requested-With", "XMLHttpRequest").cookies(cookies).cookies(newCookies)
				.queryParam("view_type", 0).queryParam("publish_id", 5).get(Routes.fetchViews).then().extract()
				.response().as(FetchViewResponseBody.class);
		List<BuiltinView> views = fetchViewResponseBody.data.builtinViews;
		BuiltinView newView = views.stream().filter(view -> view.name.equals("All New Issues"))
				.collect(Collectors.toList()).get(0);
		return newView.id;
	}

	public String searchIssueViaTitle(String title, String viewId) throws InterruptedException {

		HashMap<String, Object> query = new HashMap<>();
		query.put("category", "primary");
		query.put("prop", "title");
		query.put("type", "text");
		query.put("op", "contains-all-of");
		List<String> val = new ArrayList<>();
		val.add(title);
		query.put("val", val);

		HashMap<String, Object> advancedSearchRequestBody = new HashMap<>();

		advancedSearchRequestBody.put("view_type", "view");
		advancedSearchRequestBody.put("type", "issue");
		advancedSearchRequestBody.put("view_id", viewId);
		advancedSearchRequestBody.put("is_mentions_view", false);
		advancedSearchRequestBody.put("query", query);
		advancedSearchRequestBody.put("_csrf_token", cookies.getValue("_csrf_token"));

		String baseUri = "https://" + domain + "." + extendedDomain + "." + extension;
		RestAssured.baseURI = baseUri;

		AdvancedSearchResponseBody advancedSearchResponseBody = RestAssured.given().header("User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36")
				.header("Host", domain + "." + extendedDomain + "." + extension).
				// contentType(ContentType.URLENC.withCharset("UTF-8")).
				header("X-Requested-With", "XMLHttpRequest").cookies(cookies).cookies(newCookies)
				.formParams(advancedSearchRequestBody).post(Routes.advancedSearch).then().extract().response()
				.as(AdvancedSearchResponseBody.class);
		return advancedSearchResponseBody.data.issues.get(0).id;
	}

	public String agentSendsMessage(String issueId, String message) {
		HashMap<String, Object> agentSendMessageRequestBody = new HashMap<>();
		agentSendMessageRequestBody.put("body", message);
		agentSendMessageRequestBody.put("avatar_resolution", 36);
		agentSendMessageRequestBody.put("avatar_type", "agent");
		agentSendMessageRequestBody.put("issue_id", issueId);
		agentSendMessageRequestBody.put("_csrf_token", cookies.getValue("_csrf_token"));

		String baseUri = "https://" + domain + "." + extendedDomain + "." + extension;
		RestAssured.baseURI = baseUri;

		AgentSendMessageResponseBody agentSendMessageResponseBody = RestAssured.given().header("User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36")
				.header("Host", domain + "." + extendedDomain + "." + extension)
				.header("X-Requested-With", "XMLHttpRequest").cookies(cookies).cookies(newCookies)
				.formParams(agentSendMessageRequestBody).post(Routes.agentSendMessage).then().extract().response()
				.as(AgentSendMessageResponseBody.class);
		return agentSendMessageResponseBody.data.message.id;
	}

	public void agentChangeStatus(String issueId, int status) {
		HashMap<String, Object> agentResolveIssueRequestBody = new HashMap<>();
		agentResolveIssueRequestBody.put("id", issueId);
		agentResolveIssueRequestBody.put("status", status);
		agentResolveIssueRequestBody.put("_csrf_token", cookies.getValue("_csrf_token"));

		String baseUri = "https://" + domain + "." + extendedDomain + "." + extension;
		RestAssured.baseURI = baseUri;

		AgentResolveIssueResponseBody agentResolveIssueResponseBody = RestAssured.given().header("User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36")
				.header("Host", domain + "." + extendedDomain + "." + extension)
				.header("X-Requested-With", "XMLHttpRequest").cookies(cookies).cookies(newCookies)
				.formParams(agentResolveIssueRequestBody).post(Routes.agentResolveIssue).then().extract().response()
				.as(AgentResolveIssueResponseBody.class);
	}

	public AgentUploadImageResponse uploadAttachment(String issueId, File file, String filetype) throws IOException {
		String multipartFileType = null;
		if (filetype.equalsIgnoreCase("image"))
			multipartFileType = "image/*";

		HashMap<String, Object> agentSendMessageRequestBody = new HashMap<>();
		agentSendMessageRequestBody.put("issue_id", issueId);
		agentSendMessageRequestBody.put("_csrf_token", cookies.getValue("_csrf_token"));
		String baseUri = "https://" + domain + "." + extendedDomain + "." + extension;
		RestAssured.baseURI = baseUri;
		AgentUploadImageResponse agentUploadImageResponse = RestAssured.given().header("User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36")
				.header("host", "sbox-sigma.helpshift.mobi").header("X-Requested-With", "XMLHttpRequest")
				.cookies(cookies).cookies(newCookies).params(agentSendMessageRequestBody)
				.multiPart("file", file, multipartFileType).post(Routes.agentUploadImage).then().extract()
				.response().as(AgentUploadImageResponse.class);

		return agentUploadImageResponse;
	}

	public String sendMessageWithAttachment(AgentUploadImageResponse agentUploadImageResponse, String body,
			String issueId) {
		HashMap<String, Object> agentSendMessageRequestBody = new HashMap<>();
		agentSendMessageRequestBody.put("attachments", "[{\"url\":\"" + agentUploadImageResponse.url + "\","
				+ "\"url-key\":\"" + agentUploadImageResponse.urlKey + "\"," + "\"content-type\":\""
				+ agentUploadImageResponse.contentType + "\"," + "\"file-name\":\"" + agentUploadImageResponse.fileName
				+ "\"," + "\"image\":true," + "\"secure?\":true," + "\"size\":" + agentUploadImageResponse.size + ","
				+ "\"thumbnail\":\"" + agentUploadImageResponse.thumbnail + "\"," + "\"thumbnail-key\":\""
				+ agentUploadImageResponse.thumbnailKey + "\"}]");

		agentSendMessageRequestBody.put("body", body);
		agentSendMessageRequestBody.put("avatar_resolution", 36);
		agentSendMessageRequestBody.put("avatar_type", "agent");
		agentSendMessageRequestBody.put("issue_id", issueId);
		agentSendMessageRequestBody.put("_csrf_token", cookies.getValue("_csrf_token"));

		String baseUri = "https://" + domain + "." + extendedDomain + "." + extension;
		RestAssured.baseURI = baseUri;
		AgentSendMessageResponseBody agentSendMessageResponseBody = RestAssured.given().header("User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36")
				.header("Host", domain + "." + extendedDomain + "." + extension)
				.header("X-Requested-With", "XMLHttpRequest").cookies(cookies).cookies(newCookies)
				.accept(ContentType.JSON).formParams(agentSendMessageRequestBody)
				.post(Routes.agentSendMessage).then().extract().response()
				.as(AgentSendMessageResponseBody.class);
		return agentSendMessageResponseBody.data.message.id;
	}

	public void hcConfigContactUsVisibility(String appId, String visibility) {
		HashMap<String, Object> agentSendMessageRequestBody = new HashMap<>();
		agentSendMessageRequestBody.put("app_id", appId);
		agentSendMessageRequestBody.put("contact_us_visibility", visibility);
		agentSendMessageRequestBody.put("_csrf_token", cookies.getValue("_csrf_token"));
		String baseUri = "https://" + domain + "." + extendedDomain + "." + extension;
		RestAssured.baseURI = baseUri;
		AgentSendMessageResponseBody agentSendMessageResponseBody = RestAssured.given().header("User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36")
				.header("Host", domain + "." + extendedDomain + "." + extension)
				.header("X-Requested-With", "XMLHttpRequest").cookies(cookies).cookies(newCookies)
				.formParams(agentSendMessageRequestBody).post(Routes.updateHcConfig)
				.as(AgentSendMessageResponseBody.class);
	}

	public String getAppId(String appName) {
		HashMap<String, String> hashmap = new HashMap<String, String>();
		hashmap.put("app_slug", appName);
		String BaseUri = "https://sbox-sigma.helpshift.mobi/xhr/view/app-details/";
		AgentSendMessageResponseBody agentSendMessageResponseBody = RestAssured.given().header("User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36")
				.header("Host", domain + "." + extendedDomain + "." + extension)
				.header("X-Requested-With", "XMLHttpRequest").cookies(cookies).cookies(newCookies).queryParams(hashmap)
				.get(BaseUri).as(AgentSendMessageResponseBody.class);
		return agentSendMessageResponseBody.data.id;
	}
}
