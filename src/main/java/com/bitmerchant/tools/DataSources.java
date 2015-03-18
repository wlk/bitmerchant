package com.bitmerchant.tools;

import com.bitmerchant.wallet.LocalWallet;


public class DataSources {

	public static String APP_NAME = "bitmerchant";
	
	
	
	public static final Integer SPARK_WEB_PORT = 4567;
		
	public static final String EXTERNAL_IP = Tools.httpGet("http://checkip.amazonaws.com/").trim();
	
	public static String WEB_SERVICE_EXTERNAL_URL = "http://" + EXTERNAL_IP + ":" + SPARK_WEB_PORT + "/";
	
	public static String WEB_SERVICE_INTERNAL_URL() {
		String httpType = LocalWallet.INSTANCE.controller.getIsSSLEncrypted() ? "https:" : "http:";
		return httpType + "//localhost:" + SPARK_WEB_PORT + "/";

	}
	
	// The path to the bitmerchant dir
	public static String HOME_DIR = System.getProperty( "user.home" ) + "/.bitmerchant";
	
	// This should not be used, other than for unzipping to the home dir
	public static final String CODE_DIR = System.getProperty("user.dir");
	
	public static final String SOURCE_CODE_HOME = HOME_DIR + "/src";
	
	
	public static final String SHADED_JAR_FILE = CODE_DIR + "/target/bitmerchant-shaded.jar";
	
	public static final String SHADED_JAR_FILE_2 = CODE_DIR + "/bitmerchant-shaded.jar";
	
	public static final String ZIP_FILE = HOME_DIR + "/bitmerchant-shaded.zip";
	
	public static final String TOOLS_JS = SOURCE_CODE_HOME + "/web/js/tools.js";
	
	public static String DB_FILE() {
		return HOME_DIR + "/" + APP_NAME + ".db";
	}
	
	public static final String SQL_FILE = SOURCE_CODE_HOME + "/bitmerchant-wallet.sql";
	
	public static final String SQL_VIEWS_FILE = SOURCE_CODE_HOME + "/bitmerchant-wallet-views.sql";
	
	public static final String BUTTON_JSON_REQ ="{\n \"button\" : {\n    \"name\": \"kittin mittinz\",\n    \"type\": \"buy_now\",\n       \"text\": \"Buy with USD/BTC\",\n    \"price_string\": \"0.50\",\n    \"price_currency_iso\": \"USD\",\n  \"network\": \"test\",\n   \"callback_url\": \"http://www.example.com/my_custom_button_callback\",\n    \"description\": \"Sample description\"\n  }\n   \n}";
	
	public static final String KEYSTORE_FILE = HOME_DIR + "/keystore.jks";
	
	public static final String KEYSTORE_PASSWORD_FILE = HOME_DIR + "/pass";
	
	public static final String KEYSTORE_DOMAIN_FILE = HOME_DIR + "/domain";
	
}
