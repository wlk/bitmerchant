package com.bitmerchant.tools;

import com.bitmerchant.wallet.LocalWallet;



public class DataSources {

	public static String APP_NAME = "bitmerchant";
	
	
	
	public static Integer SPARK_WEB_PORT = 4567;
	
	public static String HTTP() {return LocalWallet.INSTANCE.controller.getIsSSLEncrypted() ? "https://" : "http://";}

	public static String EXTERNAL_IP = Tools.httpGet("http://api.ipify.org/").trim();


	public static String WEB_SERVICE_EXTERNAL_URL() {
		if (DOMAIN == null) {
			return  HTTP() + EXTERNAL_IP + ":" + SPARK_WEB_PORT + "/";
		} else {
			return HTTP() + DOMAIN + ":" + SPARK_WEB_PORT + "/";
		}
	}

	public static String WEB_SERVICE_INTERNAL_URL() {
		return HTTP() + "localhost:" + SPARK_WEB_PORT + "/";
	}
	
	public static String DOMAIN = null;

	// The path to the bitmerchant dir
	public static String HOME_DIR = System.getProperty( "user.home" ) + "/.bitmerchant";
	
	// This should not be used, other than for unzipping to the home dir
	public static final String CODE_DIR = System.getProperty("user.dir");
	
	public static final String SOURCE_CODE_HOME() {return HOME_DIR + "/src";}
	
	
	public static final String SHADED_JAR_FILE = CODE_DIR + "/target/bitmerchant-shaded.jar";
	
	public static final String SHADED_JAR_FILE_2 = CODE_DIR + "/bitmerchant-shaded.jar";
	
	public static final String ZIP_FILE() {return HOME_DIR + "/bitmerchant-shaded.zip";}
	
	public static final String TOOLS_JS() {return SOURCE_CODE_HOME() + "/web/js/tools.js";}
	
	public static String DB_FILE() {
		return HOME_DIR + "/" + APP_NAME + ".db";
	}
	
	public static final String SQL_FILE() {return SOURCE_CODE_HOME() + "/bitmerchant-wallet.sql";}
	
	public static final String SQL_VIEWS_FILE() {return SOURCE_CODE_HOME() + "/bitmerchant-wallet-views.sql";}
	
	public static final String BUTTON_JSON_REQ ="{\n \"button\" : {\n    \"name\": \"kittin mittinz\",\n    \"type\": \"buy_now\",\n       \"text\": \"Buy with USD/BTC\",\n    \"price_string\": \"0.50\",\n    \"price_currency_iso\": \"USD\",\n  \"network\": \"test\",\n   \"callback_url\": \"http://www.example.com/my_custom_button_callback\",\n    \"description\": \"Sample description\"\n  }\n   \n}";
	
	public static final String KEYSTORE_FILE() {return HOME_DIR + "/keystore.jks";}
	
	public static final String KEYSTORE_PASSWORD_FILE() {return HOME_DIR + "/pass";}
	
	public static final String KEYSTORE_DOMAIN_FILE() {return HOME_DIR + "/domain";}
	
}
