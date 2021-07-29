package helpshift.sdkxautomation.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import API.APIHelper.APICalls;
import API.Pojos.Response.AgentUploadImageResponse;
import GenericUtilities.Globals;
import UI.AppiumUtilities.AppiumUtil;
import UI.PageObjects.ChatScreen;
import UI.PageObjects.FAQSectionScreen;
import UI.PageObjects.HomeScreen;
import helpshift.sdkxautomation.listeners.AllureListener;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Description;

@Listeners(AllureListener.class)
public class SmokeTests extends BaseTest {

	@Test(description = "Show Conversation - Issue Life Cycle")
	@Description("Create an issue, start agent conversation, resolve an issue, give CSAT feedback")
	public void basicIssueLifeCycle() throws Exception {
		HomeScreen homescreen = new HomeScreen(getDriver());
		ChatScreen chatscreen = homescreen.openChatScreen();
		// user sends a message
		chatscreen.tapOnReplyFooter();
		chatscreen.sendMessage(Globals.USER_MESSAGE);
		chatscreen.waitForIssueToBeCreated(Globals.USER_MESSAGE);
		APICalls apiCalls = new APICalls();
		apiCalls.xhrLogin();
		String view_Id = apiCalls.getNewIssuesViewId();
		String issue_id = apiCalls.searchIssueViaTitle(Globals.USER_MESSAGE, view_Id);
		// agent replies
		apiCalls.agentSendsMessage(issue_id, Globals.ADMIN_MESSAGE);
		// issue resolution
		apiCalls.agentChangeStatus(issue_id, 2);
		chatscreen.waitForResolutionQuestion();
		chatscreen.answerToResolutionQuestion(Globals.YES);
		// csat feedback
		chatscreen.waitForRatingExperiencePrompt();
		chatscreen.clickRating(Globals.STAR_RATING);
		chatscreen.enterFeedback(Globals.CSAT_FEEDBACK_MESSAGE);
		chatscreen.clickFeedbackSubmit();
		chatscreen.waitForIssueToBeResolved();
	}

	@Test(description = "FAQ Section - Contact us CTA")
	@Description("Verify visibility of 'Contact us' CTA by enabling/disabling it from the dashboard.")
	public void verifyFaqSectionContactUsCTA() throws Exception {
		HomeScreen homescreen = new HomeScreen(getDriver());
		AppiumUtil appiumUtil = new AppiumUtil(getDriver());
		// set contact us visibility to 'never'
		APICalls apiCalls = new APICalls();
		apiCalls.xhrLogin();
		String appId = apiCalls.getAppId("appium-automation");
		apiCalls.hcConfigContactUsVisibility(appId, "never");
		// select faq section
		FAQSectionScreen faqSectionScreen = homescreen.navigateToFAQSection();
		appiumUtil.waitForContextAndSwitch((AppiumDriver) getDriver(), "webview", 30);
		appiumUtil.switchToFrame(0);
		// select an faq
		faqSectionScreen.selectFaq();
		faqSectionScreen.scrollToFaqFeedbackSection();
		// verify contact us CTA is not displayed after marking the faq as unhelpful
		Assert.assertEquals("Was this article helpful?", faqSectionScreen.faqFeedbackQuestionTitle());
		faqSectionScreen.tapFeedbackOption("No");
		Assert.assertEquals("Thank you for your feedback. Please contact us if you still need help.",
				faqSectionScreen.faqFeedbackSubmittedText());
		Assert.assertFalse(faqSectionScreen.isContactUsCTADisplayed());
		// set contact us visibility to 'after_faq_unhelpful'
		apiCalls.hcConfigContactUsVisibility(appId, "after_faq_unhelpful");
		faqSectionScreen.tapBackCTA();
		// select an faq
		faqSectionScreen.selectFaq();
		faqSectionScreen.scrollToFaqFeedbackSection();
		// verify contact us CTA is displayed after marking the faq as unhelpful
		Assert.assertEquals("Was this article helpful?", faqSectionScreen.faqFeedbackQuestionTitle());
		faqSectionScreen.tapFeedbackOption("No");
		Assert.assertEquals("Thank you for your feedback. Please contact us if you still need help.",
				faqSectionScreen.faqFeedbackSubmittedText());
		Assert.assertTrue(faqSectionScreen.isContactUsCTADisplayed());
	}

	@Test(description = "Show Conversation - User Sends Attachments")
	@Description("Verify user is able to send different files as attachments.")
	public void sendUserFilesAsAttachment() throws Exception {
		HomeScreen homescreen = new HomeScreen(getDriver());
		if (getDriver() instanceof AppiumDriver)
			homescreen.downloadAttachments();
		// start the conversation with agent
		ChatScreen chatscreen = homescreen.openChatScreen();
		chatscreen.tapOnReplyFooter();
		chatscreen.sendMessage(Globals.USER_MESSAGE);
		// send various files as attachments and verify them
		if (getDriver() instanceof AppiumDriver) {
			chatscreen.tapAttachmentCTA().sendAttachment("testImagepng");
			Assert.assertTrue(chatscreen.verifyImageIsAttachedFromUser());
			chatscreen.tapAttachmentCTA().sendAttachment("testImagejpeg");
			Assert.assertTrue(chatscreen.verifyImageIsAttachedFromUser());
			chatscreen.tapAttachmentCTA().sendAttachment("testGIF");
			Assert.assertTrue(chatscreen.verifyImageIsAttachedFromUser());
			chatscreen.tapAttachmentCTA().sendAttachment("testCSV");
			Assert.assertTrue(chatscreen.verifyFileIsAttachedFromUser());
			chatscreen.tapAttachmentCTA().sendAttachment("testDocument");
			Assert.assertTrue(chatscreen.verifyFileIsAttachedFromUser());
		} else {
			chatscreen.sendAttachment(Globals.TEST_IMAGE_PNG_FILEPATH);
			Assert.assertTrue(chatscreen.verifyImageIsAttachedFromUser());
			chatscreen.sendAttachment(Globals.TEST_IMAGE_JPEG_FILEPATH);
			Assert.assertTrue(chatscreen.verifyImageIsAttachedFromUser());
			chatscreen.sendAttachment(Globals.TEST_GIF_FILEPATH);
			Assert.assertTrue(chatscreen.verifyImageIsAttachedFromUser());
			chatscreen.sendAttachment(Globals.TEST_CSV_FILEPATH);
			Assert.assertTrue(chatscreen.verifyFileIsAttachedFromUser());
			chatscreen.sendAttachment(Globals.TEST_DOCUMENT_FILEPATH);
			Assert.assertTrue(chatscreen.verifyFileIsAttachedFromUser());
		}
	}

	@Test(description = "Show Conversation - Agent Sends Attachments")
	@Description("Verify agent is able to send different files as attachments.")
	public void sendAgentFilesAsAttachment() throws Exception {
		HomeScreen homescreen = new HomeScreen(getDriver());
		ChatScreen chatscreen = homescreen.openChatScreen();
		// start conversation with agent
		chatscreen.tapOnReplyFooter();
		chatscreen.sendMessage(Globals.USER_MESSAGE);
		chatscreen.waitForIssueToBeCreated(Globals.USER_MESSAGE);
		APICalls apiCalls = new APICalls();
		apiCalls.xhrLogin();
		String view_Id = apiCalls.getNewIssuesViewId();
		String issue_id = apiCalls.searchIssueViaTitle(Globals.USER_MESSAGE, view_Id);
		apiCalls.agentSendsMessage(issue_id, Globals.ADMIN_MESSAGE);
		// send various files as attachments and verify them
		AgentUploadImageResponse agentUploadImageResponse = apiCalls.uploadAttachment(issue_id,
				new File(Globals.TEST_IMAGE_PNG_FILEPATH), Globals.IMAGE);
		apiCalls.sendMessageWithAttachment(agentUploadImageResponse, Globals.TEST_IMAGE_PNG_AGENTMESSAGE, issue_id);
		Assert.assertTrue(chatscreen.verifyImageIsAttachedFromAgent());
		agentUploadImageResponse = apiCalls.uploadAttachment(issue_id, new File(Globals.TEST_IMAGE_JPEG_FILEPATH),
				Globals.IMAGE);
		apiCalls.sendMessageWithAttachment(agentUploadImageResponse, Globals.TEST_IMAGE_JPEG_AGENTMESSAGE, issue_id);
		Assert.assertTrue(chatscreen.verifyImageIsAttachedFromAgent());
		agentUploadImageResponse = apiCalls.uploadAttachment(issue_id, new File(Globals.TEST_GIF_FILEPATH),
				Globals.IMAGE);
		apiCalls.sendMessageWithAttachment(agentUploadImageResponse, Globals.TEST_GIF_AGENTMESSAGE, issue_id);
		Assert.assertTrue(chatscreen.verifyImageIsAttachedFromAgent());
		agentUploadImageResponse = apiCalls.uploadAttachment(issue_id, new File(Globals.TEST_CSV_FILEPATH),
				Globals.FILE);
		apiCalls.sendMessageWithAttachment(agentUploadImageResponse, Globals.TEST_CSV_AGENTMESSAGE, issue_id);
		Assert.assertTrue(chatscreen.verifyFileIsAttachedFromAgent());
		agentUploadImageResponse = apiCalls.uploadAttachment(issue_id, new File(Globals.TEST_DOCUMENT_FILEPATH),
				Globals.FILE);
		apiCalls.sendMessageWithAttachment(agentUploadImageResponse, Globals.TEST_DOCUMENT_AGENTMESSAGE, issue_id);
		Assert.assertTrue(chatscreen.verifyFileIsAttachedFromAgent());
	}
}
