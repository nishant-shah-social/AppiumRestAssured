package GenericUtilities;

import java.sql.Date;
import java.sql.Timestamp;

public class Globals {

	// User Interaction Messages
	public static final String USER_MESSAGE = "SDKX Appium Automation"
			+ new Timestamp(new Date(0).getTime()).toString();
	public static final String ADMIN_MESSAGE = "Test Message From Agent";
	public static final String SEARCH_TEXT = "Update install credential";
	public static final String USER_REOPEN_MESSAGE = "The issue has been reopened.";
	public static final String CSAT_FEEDBACK_MESSAGE = "Amazing help by the agent";
	public static final String YES = "Yes";
	public static final String NO = "No";
	public static final String STAR_RATING = "3";
	public static final String TEST_IMAGE_PNG_AGENTMESSAGE = "Test Image type - png";
	public static final String TEST_IMAGE_JPEG_AGENTMESSAGE = "Test Image type - jpeg";
	public static final String TEST_GIF_AGENTMESSAGE = "Test GIF";
	public static final String TEST_CSV_AGENTMESSAGE = "Test CSV";
	public static final String TEST_DOCUMENT_AGENTMESSAGE = "Test Document";
	public static final String IMAGE = "image";
	public static final String FILE = "file";

	// File paths
	public static final String ENVIRONMENT_JSON = System.getProperty("user.dir")
			+ "/src/main/resources/Capabilities/Environment.json";
	public static final String ANDROID_CAPABILITIES_JSON = System.getProperty("user.dir")
			+ "/src/main/resources/Capabilities/AndroidCapabilities.json";
	public static final String IOS_CAPABILITIES_JSON = System.getProperty("user.dir")
			+ "/src/main/resources/Capabilities/IOSCapabilities.json";
	public static final String ATTACHMENTFILES_PATH = System.getProperty("user.dir")
			+ "/src/main/resources/AttachmentFiles/";
	public static final String TEST_IMAGE_PNG_FILEPATH = System.getProperty("user.dir")
			+ "/src/main/resources/AttachmentFiles/testImagepng.png";
	public static final String TEST_IMAGE_JPEG_FILEPATH = System.getProperty("user.dir")
			+ "/src/main/resources/AttachmentFiles/testImagejpeg.jpeg";
	public static final String TEST_GIF_FILEPATH = System.getProperty("user.dir")
			+ "/src/main/resources/AttachmentFiles/testGIF.gif";
	public static final String TEST_CSV_FILEPATH = System.getProperty("user.dir")
			+ "/src/main/resources/AttachmentFiles/testCSV.csv";
	public static final String TEST_DOCUMENT_FILEPATH = System.getProperty("user.dir")
			+ "/src/main/resources/AttachmentFiles/testDocument.doc";

	// Initialization properties
	public static String PLATFORM = System.getProperty("platform", "ios");
	public static String EXECUTION_ENVIRONMENT = System.getProperty("env", "local");
	public static boolean TABLET = Boolean.parseBoolean(System.getProperty("isTablet", "false"));
	public static boolean MOBILE_WEB = Boolean.parseBoolean(System.getProperty("mobileWeb", "false"));

	// Tablet Screen size
	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;

	// Wait Time
	public static final int IMPLICIT_WAIT = 10;
	public static final int EXPLICIT_WAIT = 15;
	public static final int PAGE_FACTORY_DEFAULT_TIME = 30;

}
