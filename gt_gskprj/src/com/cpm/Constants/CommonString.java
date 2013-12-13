package com.cpm.Constants;

public class CommonString {

	// preferenec keys
	public static final String KEY_USERNAME = "username";
	public static final String KEY_PASSWORD = "password";
	public static final String KEY_REMEMBER = "remember";
	public static final String KEY_RIGHT_NAME = "right_name";
	public static final String KEY_PATH = "path";
	public static final String KEY_VERSION = "version";
	
	
	public static final String TABLE_INSERT_SKU_DATA = "SKU_ASSET_DATA";
	// public static final String KEY_APPVERSION = "1.1";
	
	public static final String KEY_DATE = "date";
	public static final String MID = "MID";
	public static final String KEY_P = "P";
	public static final String KEY_D = "D";
	public static final String KEY_U = "U";
	public static final String KEY_N = "NOT_VISITED";
	public static final String STORE_STATUS_LEAVE = "L";

	public static final String KEY_pepsi = "pepsi";
	public static final String KEY_other = "other";

	// webservice constants
	
	public static final String KEY_SUCCESS = "Success";
	public static final String KEY_FAILURE = "Failure";
	public static final String KEY_FALSE = "False";
	public static final String KEY_CHANGED = "Changed";
	public static final String KEY_CHECKOUT_STATUS = "CHECKOUT_STATUS";
	public static final String KEY_SERVICE_STATUS = "SERVICE_STATUS";
	public static final String KEY_UPLOADCHECKOUT_STATUS = "UPLOADCHECKOUT_STATUS";
	public static final String KEY_NO_DATA = "No Data";

	public static final String KEY_PROMO = "PROMO";
	public static final String KEY_PROMO_TYPE = "PROMO_TYPE";
	public static final String KEY_PROMO_ID = "PROMO_ID";
	public static final String KEY_PROMO_TYPE_ID = "PROMO_TYPE_ID";
	
	public static final String KEY_MID = "MID";
	public static final String KEY_SKU_STOCK = "SKU_STOCK";
	public static final String KEY_SKU_ID = "SKU_ID";
	public static final String KEY_SKUFACEUP = "SKUFACEUP";
	public static final String KEY_SKUNAME = "SKUNAME";

	public static final String KEY_DOM1 = "DOM1";
	public static final String KEY_DOM2 = "DOM2";
	public static final String KEY_DOM3 = "DOM3";

	public static final String KEY_ENTRY = "ENTRY";
	public static final String KEY_IMAGE = "IMAGE";
	public static final String KEY_REMARK = "KEY_REMARK";

	public static final String KEY_SOD_ID = "SOD_ID";

	public static final String KEY_ASSETS_ID = "ASSETS_ID";
	public static final String KEY_ASSETS = "ASSET";

	public static final String KEY_STOCK_REASON_ID = "STOCKREASON_ID";
	public static final String KEY_STOCK_REASON = "STOCK_REASON";

	// location
	public static final String TABLE_LOCATION_STATUS = "LOCATION_STATUS";
	public static final String KEY_NETWORK_STATUS = "NETWORK_STATUS";
	public static final String KEY_CURRENT_TIME = "CURRENT_TIME";

	public static final String NAMESPACE = "http://tempuri.org/";

	public static final String URL1 = "http://10.200.20.133/PepsicoService/PepsicoWebService.asmx";
	 public static final String URL2 =
	 "http://pepsico.parinaam.in/Pepsicowebservice.asmx";
	 public static final String URL="http://gskgt.parinaam.in/GSKWebservice.asmx";

	public static final String METHOD_LOGIN = "Attendance";
	public static final String SOAP_ACTION_LOGIN = "http://tempuri.org/"
			+ METHOD_LOGIN;

	public static final String METHOD_UPLOAD_STORE_STATUS = "InsertUserCurrentLocation";
	public static final String SOAP_ACTION_UPLOAD_STORE_STATUS = "http://tempuri.org/"
			+ METHOD_UPLOAD_STORE_STATUS;

	public static final String METHOD_NAME_JCP = "DownLoadStoreJcp";
	public static final String SOAP_ACTION_JCP = "http://tempuri.org/"
			+ METHOD_NAME_JCP;
	
	
	//String value for promotional master
	
	public static final String METHOD_NAME_DownLoad_Promotional_Master = "DownLoad_Promotional_Master";
	public static final String SOAP_ACTION_Promotional_Master = "http://tempuri.org/"
			+ METHOD_NAME_DownLoad_Promotional_Master;
	

	// String value for SKU master
	
	public static final String METHOD_NAME_DOWNLOAD_SKU_MASTER= "DownLoad_SKU_Master";
	public static final String SOAP_ACTION_DOWNLAOD_SKU_MASTER = "http://tempuri.org/"
			+ METHOD_NAME_DOWNLOAD_SKU_MASTER;
	
	//string value for Master
	
	public static final String METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER= "DownLoad_NonWorkingReason_Master";
	public static final String SOAP_ACTION_DOWNLAOD_NON_WORKING_MASTER = "http://tempuri.org/"
			+ METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER;
	
	//string value for DowloadComplaince
	
	public static final String METHOD_NAME_DOWNLOAD_COMPLIANCE= "DowloadComplaince";
	public static final String SOAP_ACTION_DOWNLAOD_COMPLIANCE= "http://tempuri.org/"
			+ METHOD_NAME_DOWNLOAD_COMPLIANCE;
	
	//STRING VALUE FOR DowloadPromotionWithComplainceByMapping
	
	public static final String METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING= "DowloadPromotionWithComplainceByMapping";
	public static final String SOAP_ACTION_DOWNLAOD_COMPLIANCE_MAPPING= "http://tempuri.org/"
			+ METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING;
	
	
	
	
	public static final String METHOD_VERTICAL_MASTER = "DOWLOAD_VERTICALMASTER";
	public static final String SOAP_ACTION_VERTICAL_MASTER = "http://tempuri.org/"
			+ METHOD_VERTICAL_MASTER;

	public static final String METHOD_BRAND_MASTER = "DOWLOAD_BRANDMASTER";
	public static final String SOAP_ACTION_BRAND_Master = "http://tempuri.org/"
			+ METHOD_BRAND_MASTER;

	public static final String METHOD_VERTICAL_BRAND_MAPPING = "DOWLOAD_VERTICALBRANDMAPPING";
	public static final String SOAP_ACTION_VERTICAL_BRAND_Mapping = "http://tempuri.org/"
			+ METHOD_VERTICAL_BRAND_MAPPING;

	public static final String METHOD_VERTICAL_SKU_MAPPING = "SKUBRANDMAPPINGDownload";
	public static final String SOAP_ACTION_VERTICAL_SKU_Mapping = "http://tempuri.org/"
			+ METHOD_VERTICAL_SKU_MAPPING;

	public static final String METHOD_CATEGORY_MASTER = "DOWLOAD_CATEGORYMASTER";
	public static final String SOAP_ACTION_CATEGORY_MASTER = "http://tempuri.org/"
			+ METHOD_CATEGORY_MASTER;

	public static final String METHOD_CATEGORY_SKU_MAPPING = "CATEGORYSKUMAPPINGDownload";
	public static final String SOAP_ACTION_CATEGORY_SKU_MAPPING = "http://tempuri.org/"
			+ METHOD_CATEGORY_SKU_MAPPING;

	public static final String METHOD_CATEGORY_VERTICAL_MAPPING = "CATEGORYVERTICALMAPPINGDownload";
	public static final String SOAP_ACTION_CATEGORY_VERTICAL_MAPPING = "http://tempuri.org/"
			+ METHOD_CATEGORY_VERTICAL_MAPPING;

	public static final String METHOD_CATEGORY_POSM_MAPPING = "POSMBRANDMAPPINGDownload";
	public static final String SOAP_ACTION_POSM_MAPPING = "http://tempuri.org/"
			+ METHOD_CATEGORY_POSM_MAPPING;

	public static final String METHOD_SKU_MASTER_DOWNLOAD = "SKU_MASTERDownload";
	public static final String SOAP_ACTION_SKU_MASTER = "http://tempuri.org/"
			+ METHOD_SKU_MASTER_DOWNLOAD;

	public static final String METHOD_COMPANY_MASTER_DOWNLOAD = "COMPANY_MASTERDownload";
	public static final String SOAP_ACTION_COMPANY_MASTER = "http://tempuri.org/"
			+ METHOD_COMPANY_MASTER_DOWNLOAD;

	// Shahab
	public static final String METHOD_NONSKU_REASON = "DOWLOAD_NON_STOCK_REASON_MASTER";
	public static final String SOAP_ACTION_NONSKU_REASON = "http://tempuri.org/"
			+ METHOD_NONSKU_REASON;

	public static final String METHOD_SKU_FOCUS_DOWNLOAD = "SKUAVALIBILITY_FOCUS";
	public static final String SOAP_ACTION_SKU_FOCUS = "http://tempuri.org/"
			+ METHOD_SKU_FOCUS_DOWNLOAD;

	public static final String METHOD_MAPPING_COMPETITOR = "DOWLOAD_MAPPINGCOMPEPITORBRAND";
	public static final String SOAP_ACTION_MAPPING_COMPETITOR = "http://tempuri.org/"
			+ METHOD_MAPPING_COMPETITOR;

	public static final String METHOD_POSM_MASTER_DOWNLOAD = "DOWLOAD_POSMMASTER";
	public static final String SOAP_ACTION_POSM_MASTER_DOWNLOAD = "http://tempuri.org/"
			+ METHOD_POSM_MASTER_DOWNLOAD;

	public static final String METHOD_SKUTARGET_FOCUS = "SOD_TARGET";
	public static final String SOAP_ACTION_SKUTARGET_FOCUS = "http://tempuri.org/"
			+ METHOD_SKUTARGET_FOCUS;

	public static final String METHOD_PROMOTION_TARGET = "DOWLOAD_TARGETPROMOTION";
	public static final String SOAP_ACTION_PROMOTION = "http://tempuri.org/"
			+ METHOD_PROMOTION_TARGET;

	public static final String METHOD_ASSETS_MASTER = "DOWLOAD_ASSETMASTER";
	public static final String SOAP_ACTION_ASSET_MASTER = "http://tempuri.org/"
			+ METHOD_ASSETS_MASTER;

	public static final String METHOD_ASSETS_TARGET = "ASSET_TARGET";
	public static final String SOAP_ACTION_ASSET_TARGET = "http://tempuri.org/"
			+ METHOD_ASSETS_TARGET;

	// Geo Tag
	public static final String METHOD_GEO_TAG = "STORES_BY_MAPPING";
	public static final String SOAP_ACTION_GEO_TAG = "http://tempuri.org/"
			+ METHOD_GEO_TAG;

	public static final String METHOD_GEO_TAG_CITY = "DOWLOAD_CITYMASTER";
	public static final String SOAP_ACTION_GEO_TAG_CITY = "http://tempuri.org/"
			+ METHOD_GEO_TAG_CITY;

	// Geo Tag
	public static final String TABLE_GEOTAG_CITY = "GEO_TAG_CITY";
	public static final String TABLE_GEO_TAG_MAPPING = "GEO_TAG_MAPPING";

	public static final String TABLE_INSERT_GEO_TAG = "INSERT_GEO_TAG_DATA";

	// Upload Coverage
	public static final String METHOD_UPLOAD_DR_STORE_COVERAGE = "UPLOAD_COVERAGE";
	public static final String SOAP_ACTION_UPLOAD_DR_STORE_COVERAGE = "http://tempuri.org/"
			+ METHOD_UPLOAD_DR_STORE_COVERAGE;

	public static final String METHOD_UPLOAD_ASSET = "Upload_Stock_Availiablity";
	public static final String SOAP_ACTION_UPLOAD_ASSET = "http://tempuri.org/"
			+ METHOD_UPLOAD_ASSET;

	public static final String METHOD_UPLOAD_POSM = "Upload_Posm_Deployed";
	public static final String SOAP_ACTION_UPLOAD_POSM = "http://tempuri.org/"
			+ METHOD_UPLOAD_POSM;
	
	public static final String METHOD_UPLOAD_COMPLIANCE = "Upload_Promotion";
	public static final String SOAP_ACTION_COMPLIANCE = "http://tempuri.org/"
			+ METHOD_UPLOAD_COMPLIANCE;

	public static final String METHOD_UPLOAD_AVAILABILITY = "InsertSOSXML_UPLOAD_NEW";// "SOS_UPLOAD";
	public static final String SOAP_ACTION_UPLOAD_AVAILABILITY = "http://tempuri.org/"
			+ METHOD_UPLOAD_AVAILABILITY;

	public static final String METHOD_UPLOAD_CATEGORY_IMAGES = "Upload_AVAILABILITY_CATEGORY_IMAGES";// "SOS_UPLOAD";
	public static final String SOAP_ACTION_UPLOAD_CATEGORY_IMAGES = "http://tempuri.org/"
			+ METHOD_UPLOAD_CATEGORY_IMAGES;

	public static final String METHOD_UPLOAD_SOD_DATA = "ShareOfDisplay_UPDATE";
	public static final String SOAP_ACTION_UPLOAD_SOD = "http://tempuri.org/"
			+ METHOD_UPLOAD_SOD_DATA;

	public static final String METHOD_UPLOAD_GEOTAG = "UPLOAD_Geotag";
	public static final String SOAP_ACTION_UPLOAD_GEOTAG = "http://tempuri.org/"
			+ METHOD_UPLOAD_GEOTAG;

	public static final String METHOD_NON_WORKING_MASTER = "DOWLOAD_NONWORKINGREGIONMASTER";
	public static final String SOAP_ACTION_NONWORKING = "http://tempuri.org/"
			+ METHOD_NON_WORKING_MASTER;

	public static final String METHOD_SET_COVERAGE_STATUS = "Upload_Status";
	public static final String SOAP_ACTION_SET_COVERAGE_STATUS = "http://tempuri.org/"
			+ METHOD_SET_COVERAGE_STATUS;

	public static final String METHOD_SOD_DELETE = "DeleteSod";
	public static final String SOAP_ACTION_SOD_DELETE = "http://tempuri.org/"
			+ METHOD_SOD_DELETE;

	public static final String METHOD_SOD_UPLOAD = "ShareOfDisplay_UPDATE";
	public static final String SOAP_ACTION_SOD_UPLOAD = "http://tempuri.org/"
			+ METHOD_SOD_UPLOAD;

	public static final String METHOD_PROMOTION_PEPSI_UPLOAD = "Upload_PEPSICO_PromotionActivity";
	public static final String SOAP_ACTION_PROMOTION_PEPSI_UPLOAD = "http://tempuri.org/"
			+ METHOD_PROMOTION_PEPSI_UPLOAD;

	public static final String METHOD_PROMOTION_COM_UPLOAD = "UploadCOMPETITION_PROMOTION";
	public static final String SOAP_ACTION_PROMOTION_COM_UPLOAD = "http://tempuri.org/"
			+ METHOD_PROMOTION_COM_UPLOAD;

	public static final String METHOD_CHECKOUT = "Checkout_Status";
	public static final String SOAP_ACTION_CHECKOUT = "http://tempuri.org/"
			+ METHOD_CHECKOUT;

	// database
	

	
	
	

	


	public static final String TABLE_COVERAGE_DATA = "COVERAGE_DATA";


	public static final String TABLE_VERTICAL_MASTER = "VERTICAL_MASTER";
	public static final String TABLE_BRAND_MASTER = "BRAND_MASTER";
	public static final String TABLE_BRAND_MAPPING = "BRAND_MAPPING";
	public static final String TABLE_SKU_MAPPING = "SKU_MAPPING";
	public static final String TABLE_POSM_MASTER = "POSM_MASTER";
	public static final String TABLE_CATEGORY_MASTER = "CATEGORY_MASTER";
	public static final String TABLE_CATEGORY_MAPPING = "CATEGORY_MAPPING";
	public static final String TABLE_CATEGORY_VERTICALMAPPING = "CATEGORY_VERTICALMAPPING";

	public static final String TABLE_SKU_MASTER = "SKU_MASTER";
	public static final String TABLE_COMPANY_MASTER = "COMPANY_MASTER";
	public static final String TABLE_SKU_FOCUS = "SKU_FOCUS";
	public static final String TABLE_COMPETITOR_MAPPING = "COMPETITOR_MAPPING";
	public static final String TABLE_SKU_TARGET = "SOD_TARGET";
	public static final String TABLE_SOD_DATA = "SOD_MASTER";
	public static final String TABLE_SOD_PEPSIDATA = "SOD_TRANSACTION";
	public static final String TABLE_SOD_OTHERDATA = "SOD_TEMP";
	public static final String TABLE_SKU_AVAILBILITY = "SKU_AVAILBILITY";

	public static final String TABLE_ASSET_MASTER = "ASSET_MASTER";
	public static final String TABLE_ASSET_TARGET = "ASSEST_TARGET";

	public static final String TABLE_INSERT_ASSET_DATA = "ASSET_DATA";

	public static final String TABLE_NON_WORKING_REASON = "NON_WORKING_DATA";

	public static final String TABLE_NON_SKU_REASON = "NON_SKU_REASON";

	public static final String TABLE_SOS_PEPSI = "SOS_PEPSI";

	public static final String TABLE_SOS_IMAGES = "SOS_IMAGES";

	public static final String TABLE_SOS_BRAND_CHECK = "SOS_BRAND_CHECK";

	public static final String TABLE_PROMOTION_OTHER = "PROMOTION_OTHER";

	public static final String TABLE_PROMOTION_PEPSI = "PROMOTION_PEPSI";

	public static final String TABLE_INSERT_PROMOTION_PEPSI = "INSERT_PROMOTION_PEPSI";

	public static final String TABLE_CHECK_OUT = "CHECK_OUT";

	public static final String CREATE_TABLE_NON_SKU_REASON = "CREATE TABLE NON_SKU_REASON(STOCK_REASON VARCHAR, STOCK_REASON_ID VARCHAR)";
	// public static final String CREATE_TABLE_KEY_MODEL_DATA =
	// "CREATE TABLE KEY_MODEL_DATA (MID INTEGER, KEY_MODEL_NAME VARCHAR,KEY_MODEL_ID INTEGER,KEY_MODEL_QTNY INTEGER)";

	
	
	//CONSTANT FIELD NAMES FOR GTGSK
	
	//FOR JCP DOWNLOAD 
	
	public static final String KEY_ID = "_id";
	public static final String KEY_STORE_ID = "STORE_ID";
	public static final String KEY_STORE_NAME = "STORE_NAME";
	public static final String KEY_ADDRESS = "ADDRESS";
	public static final String KEY_USER_ID = "USER_ID";
	public static final String KEY_IN_TIME = "IN_TIME";
	public static final String KEY_OUT_TIME = "OUT_TIME";
	public static final String KEY_VISIT_DATE = "VISIT_DATE";
	public static final String KEY_LATITUDE = "LATITUDE";
	public static final String KEY_LONGITUDE = "LONGITUDE";
	public static final String KEY_REASON_ID = "REASON_ID";
	public static final String KEY_REASON = "REASON";
	public static final String KEY_STATUS = "STATUS";
	
	
	
	
	
	public static final String KEY_STORE_CD = "STORE_CD";
	public static final String KEY_STORE = "STORE_NAME";
	public static final String KEY_ADDRES = "ADDRESS";
	public static final String KEY_USER_CITY = "CITY";
	public static final String KEY_EMP_CD = "EMP_CD";
	public static final String KEY_CURRENT_DATETIME = "CURRENT_DATETIME";
	public static final String KEY_STAT = "STATUS";
	
	
	
	//For 
	
	
	
	
	//FOR TABLE DOWNOAD_PROMOTION
		public static final String PROMOTION_CD="PROMOTION_CD";
		public static final String PROMOTION_WINDOW="PROMOTION_NAME";
	//FOR TABLE SKU_MASTER
		public static final String SKU_CD="SKU_CD";
		public static final String SKU="SKU";
	//FOR TBALE NON_WORKING_REASON
		public static final String REASON_ID="REASON_ID";
		public static final String REASON="REASON";
	//FOR TABLE COMPLIANCE_MASTER
		public static final String COMPLIANCE_ID="COMPLIANCE_CD";
		public static final String COMPLIANCE="COMPLIANCE";
	//FOR TABLE COMPLIANCE_MAPPING_MASTER
		public static final String COMPLIANCE_CD="COMPLIANCE_CD";
		public static final String PROMOTIONCD="PROMOTION_CD";
		
		//POSM Master
		public static final String METHOD_NAME_DOWNLOAD_POSM_MASTER= "POSM";
		public static final String SOAP_ACTION_DOWNLAOD_POSM_MASTER= "http://tempuri.org/"
				+ METHOD_NAME_DOWNLOAD_POSM_MASTER;
		
	
	//CREATING TABLE NAMES GTGSK
		
	public static final String TABLE_STORE_DETAIL = "STORE_MASTER";//it is for jcp	
	public static final String TABLE_PROMOTION_NAME_GTGSK ="DOWNLOAD_PROMOTION";
	public static final String TABLE_SKU_MASTER_GTGSK = "SKUMASTER";
	public static final String TABLE_NON_WORKING_MASTER_GTGSK = "NON_WORKING_REASONMASTER";
	public static final String TABLE_COMPLIANCE_MASTER_GTGSK = "COMPLIANCE_MASTER";
	public static final String TABLE_COMPLIANCE_MAPPING_MASTER_GTGSK = "COMPLIANCE_MAPPING_MASTER";
	
	
	//CREATING TABLE FOR ABOVE TABLE NAMES
	//Tables
	
	
	
	
	public static final String KEY_AVAILABILITY="QUANTITY";
		public static final String TABLE_INSERT_COMPLIANCE_DATA = "COMPLIANCE_DATA_INSERTED";
		public static final String CREATE_TABLE_COMPLIANCE_DATA = "CREATE TABLE "
				+ TABLE_INSERT_COMPLIANCE_DATA + " (" + KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"			
				+ KEY_STORE_ID + " VARCHAR," + PROMOTION_CD + " VARCHAR,"
				+ PROMOTION_WINDOW + " VARCHAR,"+ COMPLIANCE_ID + " VARCHAR,"
				+ COMPLIANCE + " VARCHAR," + KEY_AVAILABILITY + " VARCHAR," +  KEY_IMAGE + " VARCHAR)";
		
		
	
	
	
		public static final String CREATE_TABLE_SKU_DATA = "CREATE TABLE "
				+ TABLE_INSERT_SKU_DATA + " (" + KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"
				+ KEY_SKU_STOCK + " VARCHAR," + KEY_SKU_ID + " VARCHAR,"
				+ KEY_SKUNAME + " VARCHAR," + KEY_SKUFACEUP + " VARCHAR)";
		
		
	//FOR JCP GT GSK
	
	public static final String CREATE_TABLE_STORE_MASTER = "CREATE TABLE "
			+ TABLE_STORE_DETAIL + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_CD
			+ " VARCHAR," + KEY_STORE + " VARCHAR," + KEY_ADDRES
			+ " VARCHAR," + KEY_USER_CITY + " VARCHAR," + KEY_EMP_CD
			+ " VARCHAR," + KEY_CURRENT_DATETIME + " VARCHAR," + KEY_STAT
			+ " VARCHAR)";
	
	
	//FOR PROMOTION GT GSK
	public static final String CREATE_TABLE_PROMOTIONAL_MASTER_GTGSK = "CREATE TABLE "
			+ TABLE_PROMOTION_NAME_GTGSK + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + PROMOTION_CD + " VARCHAR,"
			+ PROMOTION_WINDOW + " VARCHAR)";
	//FOR SKU GTGSK
	public static final String CREATE_TABLE_SKU_MASTER_GTGSK = "CREATE TABLE "
			+ TABLE_SKU_MASTER_GTGSK + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + SKU_CD + " VARCHAR,"
			+ SKU + " VARCHAR)";
	//FOR NOT WORKING REASON
	public static final String CREATE_TABLE_NONWORKINGREASON_GTGSK = "CREATE TABLE "
			+ TABLE_NON_WORKING_MASTER_GTGSK + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + REASON_ID + " VARCHAR,"
			+ REASON + " VARCHAR)";
	//FOR COMPLIANCE MASTER
	
	public static final String CREATE_TABLE_COMPLIACEMASTER_GTGSK = "CREATE TABLE "
			+ TABLE_COMPLIANCE_MASTER_GTGSK + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + COMPLIANCE_ID + " VARCHAR,"
			+ COMPLIANCE + " VARCHAR)";
	//FOR COMPLIANCE MAPPING
	
	public static final String CREATE_TABLE_COMPLIACEMASTERMAPPING_GTGSK = "CREATE TABLE "
			+ TABLE_COMPLIANCE_MAPPING_MASTER_GTGSK + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + COMPLIANCE_CD + " VARCHAR,"
			+ PROMOTIONCD + " VARCHAR)";
	//Posm Master
	
	//Posm
			public static final String KEY_POSM_CD="POSM_CD";
			public static final String KEY_POSM="POSM";
			public static final String TABLE_POSM_MASTER_GTGSK = "POSMMASTER";
			
		public static final String CREATE_TABLE_POSM_MASTER_GTGSK = "CREATE TABLE "
				+ TABLE_POSM_MASTER_GTGSK + " (" + KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_POSM_CD + " VARCHAR,"
				+ KEY_POSM + " VARCHAR)";
		
		
		public static final String TABLE_INSERT_POSM_DATA = "POSM_DATA_INSERTED";
		public static final String CREATE_TABLE_POSM_DATA = "CREATE TABLE "
				+ TABLE_INSERT_POSM_DATA + " (" + KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + MID + " INTEGER,"			
				+ KEY_STORE_ID + " VARCHAR," + KEY_POSM_CD + " VARCHAR,"
				+ KEY_POSM + " VARCHAR," + KEY_AVAILABILITY + " VARCHAR," +  KEY_IMAGE + " VARCHAR)";
		
	
	
	
	//*************** ENDS
	
	
	
	
	
	
	
	
	public static final String CREATE_TABLE_LOCATION_STATUS = "CREATE TABLE "
			+ TABLE_LOCATION_STATUS + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_DATE + " VARCHAR,"
			+ KEY_CURRENT_TIME + " VARCHAR," + KEY_LATITUDE + " VARCHAR,"
			+ KEY_LONGITUDE + " VARCHAR," + KEY_NETWORK_STATUS + " VARCHAR)";
	
	
	

	public static final String CREATE_TABLE_CHECKOUT = "CREATE TABLE "
			+ TABLE_CHECK_OUT + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_DATE + " VARCHAR,"
			+ KEY_CURRENT_TIME + " VARCHAR," + KEY_LATITUDE + " VARCHAR,"
			+ KEY_LONGITUDE + " VARCHAR)";

	public static final String CREATE_TABLE_COVERAGE_DATA = "CREATE TABLE "
			+ TABLE_COVERAGE_DATA + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
			+ " VARCHAR,USER_ID VARCHAR, " + KEY_IN_TIME + " VARCHAR,"
			+ KEY_OUT_TIME + " VARCHAR," + KEY_VISIT_DATE + " VARCHAR,"
			+ KEY_LATITUDE + " VARCHAR," + KEY_LONGITUDE + " VARCHAR,"
			+ KEY_REASON_ID + " VARCHAR," + KEY_REASON + " VARCHAR,"
			+ KEY_IMAGE + " VARCHAR,"+KEY_REMARK + " VARCHAR," + KEY_STATUS + " VARCHAR)";

	

	

	public static final String CREATE_TABLE_PROMOTION_PEPSI = "CREATE TABLE "
			+ TABLE_PROMOTION_PEPSI + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
			+ " VARCHAR," + KEY_PROMO + " VARCHAR," + KEY_PROMO_ID
			+ " VARCHAR," + KEY_PROMO_TYPE_ID + " VARCHAR," + KEY_PROMO_TYPE
			+ " VARCHAR)";



	

	public static final String CREATE_TABLE_NON_WORKING = "CREATE TABLE "
			+ TABLE_NON_WORKING_REASON + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_REASON_ID
			+ " VARCHAR," + KEY_REASON + " VARCHAR," + KEY_ENTRY + " VARCHAR,"
			+ KEY_IMAGE + " VARCHAR)";

	public static final String CREATE_TABLE_ASSETS_MASTER = "CREATE TABLE "
			+ TABLE_ASSET_MASTER + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_ASSETS + " VARCHAR,"
			+ KEY_ASSETS_ID + " VARCHAR)";


	// Upload Image
	

	public static final String METHOD_Get_DR_POSM_IMAGES = "GET_PosmDepLoyed_IMAGES";
	public static final String SOAP_ACTION_Get_DR_POSM_IMAGES = "http://tempuri.org/"
			+ METHOD_Get_DR_POSM_IMAGES;
	
	public static final String METHOD_Get_DR_COMPLIANCE_IMAGES = "GET_Store_SecondaryWindowImage";
	public static final String SOAP_ACTION_Get_DR_COMPLIANCE_IMAGES = "http://tempuri.org/"
			+ METHOD_Get_DR_COMPLIANCE_IMAGES;

	// Geo tag
	public static final String CREATE_TABLE_GEO_TAG_CITY = "CREATE TABLE "
			+ TABLE_GEOTAG_CITY + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + "CITY_ID" + " VARCHAR,"
			+ "CITY" + " VARCHAR)";

	public static final String CREATE_TABLE_GEO_TAG_MAPPING = "CREATE TABLE "
			+ TABLE_GEO_TAG_MAPPING + " (" + KEY_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_STORE_ID
			+ " VARCHAR," + KEY_STORE_NAME + " VARCHAR," + KEY_ADDRESS
			+ " VARCHAR," + "CITY_ID" + " VARCHAR," + "STORE_TYPE_ID"
			+ " VARCHAR," + KEY_LATITUDE + " VARCHAR," + KEY_LONGITUDE
			+ " VARCHAR," + KEY_STATUS + " VARCHAR)";

	
}
