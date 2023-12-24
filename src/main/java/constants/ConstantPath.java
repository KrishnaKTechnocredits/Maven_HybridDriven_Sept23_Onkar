package constants;

public class ConstantPath {

	private final static String SRC_MAIN_RESOURCES = ".//src/main/resources";
	private final static String SRC_TEST_RESOURCES = ".//src/test/resources";
	
	public final static String ENV_FILEPATH = SRC_MAIN_RESOURCES + "/config/env.properties";
	public final static String CHROME_DRIVER_KEY = "webdriver.chrome.driver";
	public final static String CHROME_DRIVER_VALUE = SRC_MAIN_RESOURCES + "/chromeDriver/chromedriver.exe";
	public final static int WAIT = 30;
	public final static int FAST_WAIT = 5;
	
	
	public final static String EDGE_DRIVER_KEY = "webdriver.edge.driver";
	public final static String EDGE_DRIVER_VALUE = SRC_MAIN_RESOURCES + "/edgeDriver/msedgedriver.exe";
	
	
	public final static String LOGIN_WORKBOOK_PATH = SRC_TEST_RESOURCES + "/testdata/LoginData.xlsx";
}
