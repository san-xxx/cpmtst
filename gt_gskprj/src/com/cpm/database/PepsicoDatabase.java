package com.cpm.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cpm.Constants.CommonString;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.ComplianceBean;
import com.cpm.delegates.PosmBean;
import com.cpm.delegates.ReasonModel;
import com.cpm.delegates.SkuStoreBean;
import com.cpm.delegates.StoreBean;
import com.cpm.delegates.Storenamebean;
import com.cpm.xmlGetterSetter.ComplianceByMappingGetterSetter;
import com.cpm.xmlGetterSetter.ComplianceGetterSetter;
import com.cpm.xmlGetterSetter.JCPGetterSetter;
import com.cpm.xmlGetterSetter.NonWrkingMasterGetterSetter;
import com.cpm.xmlGetterSetter.PosmGetterSetter;
import com.cpm.xmlGetterSetter.PromotionalMasterGettersetter;
import com.cpm.xmlGetterSetter.SkuMasterGetterSetter;

public class PepsicoDatabase extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "GTGSK_DATABASE";
	public static final int DATABASE_VERSION = 1;

	private SQLiteDatabase db;

	public PepsicoDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	public void open() {
		try {
			db = this.getWritableDatabase();
		} catch (Exception e) {
		}
	}

	public void close() {
		db.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		// CREATING TABLE FOR GTGSK
		db.execSQL(CommonString.CREATE_TABLE_PROMOTIONAL_MASTER_GTGSK);
		db.execSQL(CommonString.CREATE_TABLE_SKU_MASTER_GTGSK);
		db.execSQL(CommonString.CREATE_TABLE_NONWORKINGREASON_GTGSK);
		db.execSQL(CommonString.CREATE_TABLE_COMPLIACEMASTER_GTGSK);
		db.execSQL(CommonString.CREATE_TABLE_COMPLIACEMASTERMAPPING_GTGSK);
		db.execSQL(CommonString.CREATE_TABLE_COMPLIANCE_DATA);

		db.execSQL(CommonString.CREATE_TABLE_POSM_MASTER_GTGSK);
		db.execSQL(CommonString.CREATE_TABLE_POSM_DATA);

		db.execSQL(CommonString.CREATE_TABLE_COVERAGE_DATA);
		db.execSQL(CommonString.CREATE_TABLE_STORE_MASTER);

		db.execSQL(CommonString.CREATE_TABLE_GEO_TAG_CITY);
		db.execSQL(CommonString.CREATE_TABLE_GEO_TAG_MAPPING);

		db.execSQL(CommonString.CREATE_TABLE_ASSETS_MASTER);

		db.execSQL(CommonString.CREATE_TABLE_NON_WORKING);

		db.execSQL(CommonString.CREATE_TABLE_NON_SKU_REASON);

		db.execSQL(CommonString.CREATE_TABLE_PROMOTION_PEPSI);

		db.execSQL(CommonString.CREATE_TABLE_LOCATION_STATUS);
		db.execSQL(CommonString.CREATE_TABLE_CHECKOUT);
		db.execSQL(CommonString.CREATE_TABLE_SKU_DATA);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_COVERAGE_DATA);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_STORE_DETAIL);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_VERTICAL_MASTER);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_BRAND_MASTER);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SKU_TARGET);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SOD_DATA);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SKU_AVAILBILITY);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SOD_PEPSIDATA);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SOD_OTHERDATA);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_ASSET_MASTER);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_ASSET_TARGET);

		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_INSERT_ASSET_DATA);

		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_INSERT_SKU_DATA);

		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_INSERT_GEO_TAG);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_GEO_TAG_MAPPING);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_GEOTAG_CITY);

		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_CATEGORY_MASTER);
		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_CATEGORY_VERTICALMAPPING);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_POSM_MASTER);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SKU_FOCUS);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SKU_MASTER);
		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_NON_WORKING_REASON);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_COMPANY_MASTER);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SOS_PEPSI);
		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_COMPETITOR_MAPPING);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_NON_SKU_REASON);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SOS_IMAGES);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_SOS_BRAND_CHECK);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_PROMOTION_OTHER);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_PROMOTION_PEPSI);
		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_INSERT_PROMOTION_PEPSI);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_LOCATION_STATUS);
		db.execSQL("DROP TABLE IF EXISTS " + CommonString.TABLE_CHECK_OUT);

		// DROPPING TABLE GTGSK

		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_PROMOTION_NAME_GTGSK);
		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_SKU_MASTER_GTGSK);
		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_NON_WORKING_MASTER_GTGSK);
		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_COMPLIANCE_MASTER_GTGSK);
		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_COMPLIANCE_MAPPING_MASTER_GTGSK);

		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_INSERT_COMPLIANCE_DATA);
		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_POSM_MASTER_GTGSK);
		db.execSQL("DROP TABLE IF EXISTS "
				+ CommonString.TABLE_INSERT_POSM_DATA);

		onCreate(db);
	}

	public void deleteAllTables() {

		// DELETING TABLES GTGSK

		db.delete(CommonString.TABLE_INSERT_SKU_DATA, null, null);
		db.delete(CommonString.TABLE_PROMOTION_NAME_GTGSK, null, null);
		db.delete(CommonString.TABLE_SKU_MASTER_GTGSK, null, null);
		db.delete(CommonString.TABLE_NON_WORKING_MASTER_GTGSK, null, null);
		db.delete(CommonString.TABLE_COMPLIANCE_MASTER_GTGSK, null, null);
		db.delete(CommonString.TABLE_COMPLIANCE_MAPPING_MASTER_GTGSK, null,
				null);

		db.delete(CommonString.TABLE_INSERT_COMPLIANCE_DATA, null, null);
		db.delete(CommonString.TABLE_POSM_MASTER_GTGSK, null, null);
		db.delete(CommonString.TABLE_INSERT_POSM_DATA, null, null);

		db.delete(CommonString.TABLE_STORE_DETAIL, null, null);
		db.delete(CommonString.TABLE_COVERAGE_DATA, null, null);
		db.delete(CommonString.TABLE_VERTICAL_MASTER, null, null);
		db.delete(CommonString.TABLE_BRAND_MASTER, null, null);
		db.delete(CommonString.TABLE_SOD_PEPSIDATA, null, null);
		db.delete(CommonString.TABLE_SOD_OTHERDATA, null, null);
		db.delete(CommonString.TABLE_GEO_TAG_MAPPING, null, null);
		db.delete(CommonString.TABLE_GEO_TAG_MAPPING, null, null);
		db.delete(CommonString.TABLE_INSERT_GEO_TAG, null, null);
		db.delete(CommonString.TABLE_SKU_AVAILBILITY, null, null);
		db.delete(CommonString.TABLE_SOD_DATA, null, null);
		db.delete(CommonString.TABLE_CATEGORY_MASTER, null, null);
		db.delete(CommonString.TABLE_SKU_TARGET, null, null);
		db.delete(CommonString.TABLE_CATEGORY_VERTICALMAPPING, null, null);
		db.delete(CommonString.TABLE_POSM_MASTER, null, null);
		db.delete(CommonString.TABLE_SKU_FOCUS, null, null);
		db.delete(CommonString.TABLE_SKU_MASTER, null, null);
		db.delete(CommonString.TABLE_COMPANY_MASTER, null, null);
		db.delete(CommonString.TABLE_COMPETITOR_MAPPING, null, null);
		db.delete(CommonString.TABLE_ASSET_MASTER, null, null);
		db.delete(CommonString.TABLE_ASSET_TARGET, null, null);
		db.delete(CommonString.TABLE_INSERT_ASSET_DATA, null, null);
		db.delete(CommonString.TABLE_NON_WORKING_REASON, null, null);
		db.delete(CommonString.TABLE_SOS_PEPSI, null, null);
		db.delete(CommonString.TABLE_NON_SKU_REASON, null, null);
		db.delete(CommonString.TABLE_SOS_IMAGES, null, null);
		db.delete(CommonString.TABLE_SOS_BRAND_CHECK, null, null);
		db.delete(CommonString.TABLE_PROMOTION_OTHER, null, null);
		db.delete(CommonString.TABLE_PROMOTION_PEPSI, null, null);
		db.delete(CommonString.TABLE_INSERT_PROMOTION_PEPSI, null, null);
		db.delete(CommonString.TABLE_LOCATION_STATUS, null, null);
		db.delete(CommonString.TABLE_CHECK_OUT, null, null);

	}

	// CREATING FUNCTION FOR DATABASE INSERT GTGSK
	// promotional_master_function

	public void InsertPromotion_data(PromotionalMasterGettersetter data) {

		db.delete(CommonString.TABLE_PROMOTION_NAME_GTGSK, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getpromotionid().size(); i++) {

				values.put(CommonString.PROMOTION_CD, data.getpromotionid()
						.get(i));
				values.put(CommonString.PROMOTION_WINDOW, data
						.getpromotionname().get(i));

				db.insert(CommonString.TABLE_PROMOTION_NAME_GTGSK, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.getMessage());
		}

	}

	// insert for sku table
	public void InsertskuData(SkuMasterGetterSetter data) {

		db.delete(CommonString.TABLE_SKU_MASTER_GTGSK, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getSkumasterId().size(); i++) {

				values.put(CommonString.SKU_CD, data.getSkumasterId().get(i));
				values.put(CommonString.SKU, data.getskuMasterName().get(i));

				db.insert(CommonString.TABLE_SKU_MASTER_GTGSK, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.getMessage());
		}

	}

	// insert for NONWorking reason

	public void InsertNonWorkingReason(NonWrkingMasterGetterSetter data) {

		db.delete(CommonString.TABLE_NON_WORKING_MASTER_GTGSK, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getNonWorkingReasonid().size(); i++) {

				values.put(CommonString.REASON_ID, data.getNonWorkingReasonid()
						.get(i));
				values.put(CommonString.REASON, data.getNonWorkingReasonName()
						.get(i));

				db.insert(CommonString.TABLE_NON_WORKING_MASTER_GTGSK, null,
						values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.getMessage());
		}

	}

	// getting Non Working Reason

	public ArrayList<ReasonModel> GetNonWorkingReason() {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<ReasonModel> list = new ArrayList<ReasonModel>();
		Cursor dbcursor = null;

		try {

			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_NON_WORKING_MASTER_GTGSK, null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					ReasonModel sb = new ReasonModel();
					sb.setReasonid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.REASON_ID)));
					sb.setReason(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.REASON)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	// inserting for compliance

	public void InsertCompliance(ComplianceGetterSetter data) {

		db.delete(CommonString.TABLE_COMPLIANCE_MASTER_GTGSK, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getComplianceId().size(); i++) {

				values.put(CommonString.COMPLIANCE_ID, data.getComplianceId()
						.get(i));
				values.put(CommonString.COMPLIANCE, data.getComplianceName()
						.get(i));

				db.insert(CommonString.TABLE_COMPLIANCE_MASTER_GTGSK, null,
						values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.getMessage());
		}

	}

	// inserting gor compliance mapping

	public void InsertCompliancemapping(ComplianceByMappingGetterSetter data) {

		db.delete(CommonString.TABLE_COMPLIANCE_MAPPING_MASTER_GTGSK, null,
				null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getMappingComplianceId().size(); i++) {

				values.put(CommonString.COMPLIANCE_ID, data
						.getMappingComplianceId().get(i));
				values.put(CommonString.PROMOTIONCD, data
						.getMappingCompliancePromotion().get(i));

				db.insert(CommonString.TABLE_COMPLIANCE_MAPPING_MASTER_GTGSK,
						null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.getMessage());
		}

	}

	// jcp

	public void InsertStoreData(JCPGetterSetter data, String date) {

		db.delete(CommonString.TABLE_STORE_DETAIL, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getStoreid().size(); i++) {

				values.put(CommonString.KEY_STORE_CD, data.getStoreid().get(i));
				values.put(CommonString.KEY_STORE, data.getStorename().get(i));

				values.put(CommonString.KEY_ADDRES, data.getStoreaddres()
						.get(i));
				values.put(CommonString.KEY_USER_CITY, data.getStorelatitude()
						.get(i));
				values.put(CommonString.KEY_EMP_CD, data.getStorelongitude()
						.get(i));
				values.put(CommonString.KEY_CURRENT_DATETIME, data.getKEY_ID()
						.get(i));
				values.put(CommonString.KEY_STAT, data.getStatus().get(i));

				db.insert(CommonString.TABLE_STORE_DETAIL, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.getMessage());
		}

	}

	public void deleteStoreMidData(int mid, String store_id) {

		try {

			db.delete(CommonString.TABLE_COVERAGE_DATA, CommonString.KEY_ID
					+ " = '" + mid + "'", null);

			db.delete(CommonString.TABLE_INSERT_SKU_DATA, CommonString.MID
					+ " = '" + mid + "'", null);

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}

	public StoreBean getStoreStatus(String id) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");

		StoreBean sb = new StoreBean();

		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_STORE_DETAIL + "  WHERE STORE_CD = '"
					+ id + "'", null);

			if (dbcursor != null) {
				int numrows = dbcursor.getCount();

				dbcursor.moveToFirst();
				for (int i = 1; i <= numrows; ++i) {

					sb.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_CD)));
					sb.setStorename((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_NAME))));
					sb.setStoreaddress(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ADDRESS)));
					sb.setVisitdate((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_CURRENT_DATETIME))));
					

					sb.setStatus(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STAT)));

					dbcursor.moveToNext();

				}

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return sb;

	}

	public String getStoreName(String storeid) {
		String storename = "";
		Cursor cursor = db.rawQuery("select " + CommonString.KEY_STORE_NAME
				+ " from " + CommonString.TABLE_STORE_DETAIL + " where "
				+ CommonString.KEY_STORE_CD + "='" + storeid + "'", null);

		if (cursor != null) {
			cursor.moveToFirst();
			storename = cursor.getString(cursor
					.getColumnIndexOrThrow(CommonString.KEY_STORE_NAME));
		}

		cursor.close();
		return storename;
	}

	public void updateCoverageStatus(int mid, String status) {

		try {
			ContentValues values = new ContentValues();
			values.put(CommonString.KEY_STATUS, status);

			db.update(CommonString.TABLE_COVERAGE_DATA, values,
					CommonString.KEY_ID + "=" + mid, null);
		} catch (Exception e) {

		}
	}

	public void updateStoreStatus1(String storeid, String status) {

		try {
			ContentValues values = new ContentValues();
			values.put(CommonString.KEY_STATUS, status);

			db.update(CommonString.TABLE_STORE_DETAIL, values,
					CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
		} catch (Exception e) {

		}
	}

	public void delete_temp() {
		db.delete(CommonString.TABLE_SOD_OTHERDATA, null, null);
	}

	// delete data

	/*
	 * public static final String KEY_STORE_CD = "STORE_CD"; public static final
	 * String KEY_STORE = "STORE_NAME"; public static final String KEY_ADDRES =
	 * "ADDRESS"; public static final String KEY_USER_CITY = "CITY"; public
	 * static final String KEY_EMP_CD = "EMP_CD"; public static final String
	 * KEY_CURRENT_DATETIME = "CURRENT_DATETIME"; public static final String
	 * KEY_STAT = "STATUS";
	 */

	public ArrayList<StoreBean> getStoreData(String date) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<StoreBean> list = new ArrayList<StoreBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_STORE_DETAIL + " where "
					+ CommonString.KEY_CURRENT_DATETIME + "='" + date + "'",
					null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					StoreBean sb = new StoreBean();
					sb.setStoreid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_CD)));
					sb.setStorename((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE))));
					sb.setStoreaddress(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ADDRES)));
					sb.setVisitdate((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_USER_CITY))));
					sb.setLatitude((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_EMP_CD))));
					sb.setLongitude(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_CURRENT_DATETIME)));

					sb.setStatus(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STAT)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	public ArrayList<SkuStoreBean> ViewInsertedSkuStock(long mid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<SkuStoreBean> list = new ArrayList<SkuStoreBean>();
		Cursor dbcursor = null;

		try {

			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_INSERT_SKU_DATA + " where "
					+ CommonString.KEY_MID + " ='" + mid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					SkuStoreBean sb = new SkuStoreBean();
					sb.setStock(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_STOCK)));
					sb.setSkuitemid(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));
					sb.setSku(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKUNAME)));
					sb.setFaceup(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_SKUFACEUP)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	public void InsertAssetData(long mid, String storeid,
			ArrayList<SkuStoreBean> data) {

		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.size(); i++) {

				values.put(CommonString.MID, mid);
				values.put(CommonString.KEY_SKU_STOCK, data.get(i).getStock());
				values.put(CommonString.KEY_SKU_ID, data.get(i).getSkuId());
				values.put(CommonString.KEY_SKUNAME, data.get(i).getSku());
				values.put(CommonString.KEY_SKUFACEUP, data.get(i).getFaceup());

				db.insert(CommonString.TABLE_INSERT_SKU_DATA, null, values);
			}
		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	public void UpdateSkuData(long mid, String storeid,
			ArrayList<SkuStoreBean> data) {

		ContentValues values = new ContentValues();

		String id = String.valueOf(mid);
		try {

			for (int i = 0; i < data.size(); i++) {

				values.put(CommonString.KEY_SKUFACEUP, data.get(i).getFaceup());
				values.put(CommonString.KEY_SKU_STOCK, data.get(i).getStock());

				db.update(CommonString.TABLE_INSERT_SKU_DATA, values,
						CommonString.KEY_SKU_ID + " =? AND " + CommonString.MID
								+ " =?", new String[] { data.get(i).getSkuId(),
								id, });

			}
		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	public ArrayList<SkuStoreBean> getSkuData() {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<SkuStoreBean> list = new ArrayList<SkuStoreBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_SKU_MASTER_GTGSK, null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					SkuStoreBean sb = new SkuStoreBean();
					sb.setSku(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.SKU)));
					sb.setSkuitemid((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.SKU_CD))));
					sb.setFaceup("");
					sb.setStock("");

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	// get compliance

	public ArrayList<ComplianceBean> getComplianceData() {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<ComplianceBean> list = new ArrayList<ComplianceBean>();
		Cursor dbcursor = null;

		try {
			/*
			 * dbcursor = db.rawQuery("SELECT  * from " +
			 * CommonString.TABLE_COMPLIANCE_MASTER_GTGSK, null);
			 */

			dbcursor = db
					.rawQuery(
							"SELECT  CH.COMPLIANCE_CD,CH.PROMOTION_CD,COMPLIANCE,PROMOTION_NAME from COMPLIANCE_MAPPING_MASTER CH inner join COMPLIANCE_MASTER C on C.COMPLIANCE_CD = CH.COMPLIANCE_CD inner join DOWNLOAD_PROMOTION P on P.PROMOTION_CD = CH.PROMOTION_CD ",
							null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					ComplianceBean sb = new ComplianceBean();
					sb.setComliance(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.COMPLIANCE)));
					sb.setCompliance_id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.COMPLIANCE_ID)));
					sb.setPromtion_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.PROMOTION_CD)));
					sb.setPromotion(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.PROMOTION_WINDOW)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	// Insert compliance data

	public void InsertComplianceData(long mid, String storeid,
			ArrayList<ComplianceBean> data) {

		ContentValues values = new ContentValues();

		String available;
		try {

			for (int i = 0; i < data.size(); i++) {

				if (data.get(i).getAvailability().equalsIgnoreCase("YES")) {
					available = "1";
				} else {
					available = "0";
				}

				values.put(CommonString.MID, mid);
				values.put(CommonString.KEY_STORE_ID, storeid);
				values.put(CommonString.KEY_AVAILABILITY, available);
				values.put(CommonString.COMPLIANCE_ID, data.get(i)
						.getCompliance_id());
				values.put(CommonString.COMPLIANCE, data.get(i).getCompliance());
				values.put(CommonString.PROMOTION_CD, data.get(i)
						.getPromtion_cd());
				values.put(CommonString.PROMOTION_WINDOW, data.get(i)
						.getPromotion());

				values.put(CommonString.KEY_IMAGE, data.get(i).getImage());

				db.insert(CommonString.TABLE_INSERT_COMPLIANCE_DATA, null,
						values);
			}
		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	// delete data
	public void deleteComplianceData(String storeid) {

		try {

			db.delete(CommonString.TABLE_INSERT_COMPLIANCE_DATA,
					CommonString.KEY_STORE_ID + "='" + storeid + "'", null);

		} catch (Exception e) {

		}
	}

	// getInsertedData
	public ArrayList<ComplianceBean> getInsertedComplianceData(String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<ComplianceBean> list = new ArrayList<ComplianceBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_INSERT_COMPLIANCE_DATA + " where "
					+ CommonString.KEY_STORE_ID + " = '" + storeid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					ComplianceBean sb = new ComplianceBean();
					sb.setComliance(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.COMPLIANCE)));
					sb.setCompliance_id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.COMPLIANCE_ID)));
					sb.setPromtion_cd(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.PROMOTION_CD)));
					sb.setPromotion(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.PROMOTION_WINDOW)));
					sb.setImage(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE)));
					sb.setAvailability(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_AVAILABILITY)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	// Posm Deployed

	public ArrayList<PosmBean> getPosmData() {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<PosmBean> list = new ArrayList<PosmBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_POSM_MASTER_GTGSK, null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					PosmBean sb = new PosmBean();
					sb.setPosm_id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_POSM_CD)));
					sb.setPosm(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_POSM)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	public void InsertPosmData(long mid, String storeid,
			ArrayList<PosmBean> data) {

		ContentValues values = new ContentValues();

		String quantity = "";
		String image = "";
		try {

			for (int i = 0; i < data.size(); i++) {

				values.put(CommonString.MID, mid);
				values.put(CommonString.KEY_STORE_ID, storeid);

				values.put(CommonString.KEY_POSM_CD, data.get(i).getPosm_id());
				values.put(CommonString.KEY_POSM, data.get(i).getPosm());

				values.put(CommonString.KEY_IMAGE, data.get(i).getImage());

				values.put(CommonString.KEY_AVAILABILITY, data.get(i)
						.getQuantity());

				db.insert(CommonString.TABLE_INSERT_POSM_DATA, null, values);
			}
		} catch (Exception ex) {
			Log.d("Database Exception while Insert Posm Master Data ",
					ex.getMessage());
		}

	}

	public void deletePosmData(String storeid) {

		try {

			db.delete(CommonString.TABLE_INSERT_POSM_DATA,
					CommonString.KEY_STORE_ID + "='" + storeid + "'", null);

		} catch (Exception e) {

		}
	}

	public ArrayList<PosmBean> getInsertedPosmData(String storeid) {

		Log.d("FetchingStoredata--------------->Start<------------",
				"------------------");
		ArrayList<PosmBean> list = new ArrayList<PosmBean>();
		Cursor dbcursor = null;

		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_INSERT_POSM_DATA + " where "
					+ CommonString.KEY_STORE_ID + " = '" + storeid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					PosmBean sb = new PosmBean();
					sb.setPosm(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_POSM)));
					sb.setPosm_id(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_POSM_CD)));

					sb.setImage(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE)));
					sb.setQuantity(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_AVAILABILITY)));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return list;
		}

		Log.d("FetchingStoredat---------------------->Stop<-----------",
				"-------------------");
		return list;

	}

	// posm master
	public void InsertPosm(PosmGetterSetter data) {

		db.delete(CommonString.TABLE_POSM_MASTER_GTGSK, null, null);
		ContentValues values = new ContentValues();

		try {

			for (int i = 0; i < data.getPosmID().size(); i++) {

				values.put(CommonString.KEY_POSM_CD, data.getPosmID().get(i));
				values.put(CommonString.KEY_POSM, data.getPosm().get(i));

				db.insert(CommonString.TABLE_POSM_MASTER_GTGSK, null, values);

			}

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Store Data ",
					ex.getMessage());
		}

	}

	// getInsertedData

	public long InsertCoverageData(CoverageBean data) {

		ContentValues values = new ContentValues();

		try {

			values.put(CommonString.KEY_STORE_ID, data.getStoreId());
			values.put(CommonString.KEY_USER_ID, data.getUserId());
			values.put(CommonString.KEY_IN_TIME, data.getInTime());
			values.put(CommonString.KEY_OUT_TIME, data.getOutTime());
			values.put(CommonString.KEY_VISIT_DATE, data.getVisitDate());
			values.put(CommonString.KEY_LATITUDE, data.getLatitude());
			values.put(CommonString.KEY_LONGITUDE, data.getLongitude());
			values.put(CommonString.KEY_REASON_ID, data.getReasonid());
			values.put(CommonString.KEY_REASON, data.getReason());
			values.put(CommonString.KEY_STATUS, data.getStatus());
			values.put(CommonString.KEY_IMAGE, data.getImage());
			values.put(CommonString.KEY_REMARK, data.getRemark());

			return db.insert(CommonString.TABLE_COVERAGE_DATA, null, values);

		} catch (Exception ex) {
			Log.d("Database Exception while Insert Closes Data ",
					ex.getMessage());
		}
		return 0;
	}

	public boolean update_CoverageTable(String time, String id) {
		ContentValues values = new ContentValues();

		values.put(CommonString.KEY_OUT_TIME, time);

		return db.update(CommonString.TABLE_COVERAGE_DATA, values,
				CommonString.KEY_STORE_ID + " =" + id, null) > 0;

	}

	public int getCoverageData1() {
		Cursor dbcursor = null;
		int count = 0;
		try {

			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_COVERAGE_DATA + " where "
					+ CommonString.KEY_STATUS + " = N", null);

			count = dbcursor.getCount();

		} catch (Exception e) {
			// TODO: handle exception
			Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
			return count;
		}

		return count;

	}

	// getCoverageData
	public ArrayList<CoverageBean> getCoverageData(String visitdate,
			String store_id) {

		ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
		Cursor dbcursor = null;

		try {

			if (visitdate == null) {

				dbcursor = db.rawQuery("SELECT  * from "
						+ CommonString.TABLE_COVERAGE_DATA, null);

			} else if (store_id == null) {
				dbcursor = db.rawQuery("SELECT  * from "
						+ CommonString.TABLE_COVERAGE_DATA + " where "
						+ CommonString.KEY_VISIT_DATE + "='" + visitdate + "'",
						null);
			} else {
				dbcursor = db.rawQuery("SELECT  * from "
						+ CommonString.TABLE_COVERAGE_DATA + " where "
						+ CommonString.KEY_VISIT_DATE + "='" + visitdate
						+ "' AND " + CommonString.KEY_STORE_ID + "='"
						+ store_id + "'", null);
			}

			if (dbcursor != null) {

				dbcursor.moveToFirst();
				while (!dbcursor.isAfterLast()) {
					CoverageBean sb = new CoverageBean();
					sb.setMID((dbcursor.getInt(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_ID))));
					sb.setStoreId(dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
					sb.setUserId((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_USER_ID))));
					sb.setInTime(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IN_TIME)))));
					sb.setOutTime(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_OUT_TIME)))));
					sb.setVisitDate((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE))))));
					sb.setLatitude(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)))));
					sb.setLongitude(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)))));
					sb.setReasonid(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)))));
					sb.setReason(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REASON)))));
					sb.setRemark(((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_REMARK)))));
					sb.setStatus((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_STATUS))))));
					

					sb.setImage((((dbcursor.getString(dbcursor
							.getColumnIndexOrThrow(CommonString.KEY_IMAGE))))));

					list.add(sb);
					dbcursor.moveToNext();
				}
				dbcursor.close();
				return list;
			}

		} catch (Exception e) {
			Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}

		return list;

	}

	public void deleteCoverageData(String date) {

		try {
			db.delete(CommonString.TABLE_COVERAGE_DATA, CommonString.KEY_DATE
					+ "=" + date, null);

		} catch (Exception e) {

		}
	}

	// Update store status
	/*
	 * public void updateStoreStatus(String storeid, String visitdate) {
	 * 
	 * try { ContentValues values = new ContentValues();
	 * values.put(CommonString.KEY_STATUS, "U");
	 * 
	 * db.update(CommonString.TABLE_STORE_DETAIL, values,
	 * CommonString.KEY_STORE_ID + "='" + storeid + "' AND " +
	 * CommonString.KEY_VISIT_DATE + "='" + visitdate + "'", null); } catch
	 * (Exception e) {
	 * 
	 * } }
	 */

	public void updateStoreStatusOnLeave(String storeid, String visitdate,
			String status) {

		try {
			ContentValues values = new ContentValues();
			values.put(CommonString.KEY_STAT, status);

			db.update(CommonString.TABLE_STORE_DETAIL, values,
					CommonString.KEY_STORE_CD + "='" + storeid + "' AND "
							+ CommonString.KEY_CURRENT_DATETIME + "='" + visitdate
							+ "'", null);
		} catch (Exception e) {
			Log.d("Exception when fetching Coverage Data!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());

		}
	}
	
	public void updateStoreStatus(String storeid, String visitdate) {

		try {
			ContentValues values = new ContentValues();
			values.put(CommonString.KEY_STAT, "U");

			db.update(CommonString.TABLE_STORE_DETAIL, values,
					CommonString.KEY_STORE_CD + "='" + storeid + "' AND "
							+ CommonString.KEY_CURRENT_DATETIME + "='" + visitdate
							+ "'", null);
		} catch (Exception e) {

		}
	}

	public int CheckMid(String currdate, String storeid) {

		Cursor dbcursor = null;
		int mid = 0;
		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_COVERAGE_DATA + "  WHERE "
					+ CommonString.KEY_VISIT_DATE + " = '" + currdate
					+ "' AND " + CommonString.KEY_STORE_ID + " ='" + storeid
					+ "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();

				mid = dbcursor.getInt(dbcursor
						.getColumnIndexOrThrow(CommonString.KEY_ID));

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		return mid;
	}

	public String Checkstat(String storeid) {

		Cursor dbcursor = null;
		String mid = "";
		try {
			dbcursor = db.rawQuery("SELECT STATUS FROM "
					+ CommonString.TABLE_COVERAGE_DATA + "  WHERE "
					+ CommonString.KEY_STORE_ID + " ='" + storeid + "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();

				mid = dbcursor.getString(dbcursor
						.getColumnIndexOrThrow(CommonString.KEY_STATUS));

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		return mid;
	}

	public String CheckMidWithStatus(String currdate, String storeid) {

		Cursor dbcursor = null;
		String status = "";
		try {
			dbcursor = db.rawQuery("SELECT  * from "
					+ CommonString.TABLE_COVERAGE_DATA + "  WHERE "
					+ CommonString.KEY_VISIT_DATE + " = '" + currdate
					+ "' AND " + CommonString.KEY_STORE_ID + " ='" + storeid
					+ "'", null);

			if (dbcursor != null) {
				dbcursor.moveToFirst();

				status = dbcursor.getString(dbcursor
						.getColumnIndexOrThrow(CommonString.KEY_STATUS));

				dbcursor.close();

			}

		} catch (Exception e) {
			Log.d("Exception when fetching Records!!!!!!!!!!!!!!!!!!!!!",
					e.getMessage());
		}

		return status;
	}

}
