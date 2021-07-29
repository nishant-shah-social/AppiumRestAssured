package helpshift.sdkxautomation.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import API.APIHelper.APICalls;
import GenericUtilities.Globals;
import UI.AppiumUtilities.AppiumUtil;
import UI.PageObjects.ChatScreen;
import UI.PageObjects.HomeScreen;
import helpshift.sdkxautomation.listeners.AllureListener;
import io.qameta.allure.Description;

@Listeners(AllureListener.class)
public class ChatScreenTests extends BaseTest {

	HomeScreen homeScreen;
	ChatScreen chatScreen;
	APICalls apiCalls;
	AppiumUtil appiumUtil;

	@Test(description = "Show Conversation - Create Issue")
	@Description("Create an issue and start agent conversation")
	public void createIssueAndReplyByAdmin() throws Exception {
		homeScreen = new HomeScreen(getDriver());
		chatScreen = homeScreen.openChatScreen();

		// Create issue
		chatScreen.tapOnReplyFooter();
		String userMessage = chatScreen.createIssue();

		// Admin Interactions

		APICalls apiCalls = new APICalls();
		apiCalls.xhrLogin();
		String view_Id = apiCalls.getNewIssuesViewId();
		String issue_id = apiCalls.searchIssueViaTitle(userMessage, view_Id);

		// Admin Message
		apiCalls.agentSendsMessage(issue_id, Globals.ADMIN_MESSAGE);
	}

	@Test(description = "Show Conversation - Smart Intents")
	@Description("Search and select the smart intents")
	public void searchAndSelectSmartIntent() throws Exception {
		homeScreen = new HomeScreen(getDriver());
		chatScreen = homeScreen.openChatScreen();

		// Enter a string to trigger Smart Intents Search
		chatScreen.tapOnReplyFooter();
		chatScreen.sendMessage(Globals.SEARCH_TEXT);
		chatScreen.tapOnReplyFooter();
		chatScreen.waitForSISearchResultToBeVisible();

		// Select a Smart Intent
		chatScreen.siSearchTap(0);

		// Wait for issue creation via Smart Intent and send a user message
		chatScreen.waitForReplyFooterToBeVisible();
		chatScreen.sendMessage(Globals.USER_MESSAGE);
	}

	@Test(description = "Show Conversation - Resolve & Feedback")
	@Description("Resolve an issue and give CSAT feedback")
	public void resolveAndSendCSAT() throws Exception {
		homeScreen = new HomeScreen(getDriver());
		chatScreen = homeScreen.openChatScreen();
		// Create issue
		chatScreen.tapOnReplyFooter();
		String userMessage = chatScreen.createIssue();

		// Admin Interactions
		APICalls apiCalls = new APICalls();
		apiCalls = new APICalls();
		apiCalls.xhrLogin();
		String view_Id = apiCalls.getNewIssuesViewId();
		String issue_id = apiCalls.searchIssueViaTitle(userMessage, view_Id);

		// Admin Message
		apiCalls.agentSendsMessage(issue_id, Globals.ADMIN_MESSAGE);
		apiCalls.agentChangeStatus(issue_id, 2);

		// Wait for Resolution Question and select "Yes"
		chatScreen.waitForResolutionQuestion();
		chatScreen.answerToResolutionQuestion("Yes");

		// Wait for CSAT and send some rating and feedback message
		chatScreen.waitForRatingExperiencePrompt();
		chatScreen.clickRating("3");
		chatScreen.enterFeedback(Globals.CSAT_FEEDBACK_MESSAGE);

		// Click Submit and assert for issue resolution
		chatScreen.clickFeedbackSubmit();
		chatScreen.waitForIssueToBeResolved();
	}

	@Test(description = "Show Conversation - Reject Issue")
	@Description("Verify agent rejects an issue")
	public void rejectIssue() throws Exception {

		homeScreen = new HomeScreen(getDriver());
		chatScreen = homeScreen.openChatScreen();

		// Create issue
		chatScreen.tapOnReplyFooter();
		String userMessage = chatScreen.createIssue();

		// Admin Interactions
		APICalls apiCalls = new APICalls();
		apiCalls = new APICalls();
		apiCalls.xhrLogin();
		String view_Id = apiCalls.getNewIssuesViewId();
		String issue_id = apiCalls.searchIssueViaTitle(userMessage, view_Id);

		// Admin Message
		apiCalls.agentSendsMessage(issue_id, Globals.ADMIN_MESSAGE);
		apiCalls.agentChangeStatus(issue_id, 3);

		// Assert via New Conversation button visibility
		chatScreen.waitForIssueToBeResolved();
	}

	@Test(description = "Show Conversation - Resolve Issue and Reopen")
	@Description("Verify agent resolves the issue and user is able to reopen the same issue")
	public void resolveAndReopenIssue() throws Exception {

		homeScreen = new HomeScreen(getDriver());
		chatScreen = homeScreen.openChatScreen();

		// Create issue
		chatScreen.tapOnReplyFooter();
		String userMessage = chatScreen.createIssue();

		// Admin Interactions
		APICalls apiCalls = new APICalls();
		apiCalls = new APICalls();
		apiCalls.xhrLogin();
		String view_Id = apiCalls.getNewIssuesViewId();
		String issue_id = apiCalls.searchIssueViaTitle(userMessage, view_Id);

		// Admin Message
		apiCalls.agentSendsMessage(issue_id, Globals.ADMIN_MESSAGE);
		apiCalls.agentChangeStatus(issue_id, 2);

		// Wait for Resolution Question and select "No"
		chatScreen.waitForResolutionQuestion();
		chatScreen.answerToResolutionQuestion("No");

		// Send User Message to reopen

		chatScreen.tapOnReplyFooter();
		chatScreen.sendMessage(Globals.USER_REOPEN_MESSAGE);
		chatScreen.waitForIssueToBeCreated(Globals.USER_REOPEN_MESSAGE);
	}
}
