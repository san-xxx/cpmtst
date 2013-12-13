package com.cpm.upload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cpm.Constants.CommonString;
import com.cpm.dailyEntry.DailyentryMenuActivity;
import com.cpm.database.PepsicoDatabase;

import com.cpm.delegates.ComplianceBean;
import com.cpm.delegates.CoverageBean;

import com.cpm.delegates.PosmBean;

import com.cpm.delegates.StoreBean;
import com.cpm.gt_gsk.R;
import com.cpm.message.AlertMessage;

import com.cpm.xmlGetterSetter.FailureGetterSetter;
import com.cpm.xmlHandler.FailureXMLHandler;

public class UploadImageActivity extends Activity {

	private Dialog dialog;
	private ProgressBar pb;
	private TextView percentage, message;
	private String visit_date;
	private SharedPreferences preferences;
	private PepsicoDatabase database;
	private int factor, k;
	private FailureGetterSetter failureGetterSetter = null;

	String result, username;
	String datacheck = "";
	String[] words;
	String validity, storename;
	String mid = "";
	String errormsg = "";
	static int counter = 1;
	private ArrayList<CoverageBean> coverageBeanlist = new ArrayList<CoverageBean>();
	private ArrayList<PosmBean> Posmlist = new ArrayList<PosmBean>();
	private ArrayList<ComplianceBean> compliancelist = new ArrayList<ComplianceBean>();
	StoreBean storestatus = new StoreBean();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload_option);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		visit_date = preferences.getString(CommonString.KEY_DATE, null);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		username = preferences.getString(CommonString.KEY_USERNAME, null);

		database = new PepsicoDatabase(this);
		database.open();

		new UploadTask(this).execute();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		Intent i = new Intent(this, DailyentryMenuActivity.class);
		startActivity(i);
		UploadImageActivity.this.finish();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		// database.close();
	}

	private class UploadTask extends AsyncTask<Void, Void, String> {
		private Context context;

		UploadTask(Context context) {
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			dialog = new Dialog(context);
			dialog.setContentView(R.layout.custom);
			dialog.setTitle("Uploading Image");
			dialog.setCancelable(false);
			dialog.show();
			pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
			percentage = (TextView) dialog.findViewById(R.id.percentage);
			message = (TextView) dialog.findViewById(R.id.message);
		}

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub

			try {
				coverageBeanlist = database.getCoverageData(visit_date, null);

				if (coverageBeanlist.size() > 0) {

					if (coverageBeanlist.size() == 1) {
						factor = 50;
					} else {

						factor = 100 / (coverageBeanlist.size());
					}
				}

				for (int i = 0; i < coverageBeanlist.size(); i++) {

					storestatus = database.getStoreStatus(coverageBeanlist.get(
							i).getStoreId());

					database.open();
					storename = database.getStoreName(coverageBeanlist.get(i)
							.getStoreId());

					runOnUiThread(new Runnable() {

						public void run() {
							// TODO Auto-generated method stub
							k = k + factor;
							pb.setProgress(k);
							percentage.setText(k + "%");
							message.setText(storename + " Images");
						}
					});

					if (!(coverageBeanlist.get(i).getReasonid()
							.equalsIgnoreCase("")
							|| coverageBeanlist.get(i).getReasonid()
									.equalsIgnoreCase(null) || coverageBeanlist
							.get(i).getReasonid().equalsIgnoreCase("0"))) {
						if (coverageBeanlist.get(i).getImage() != null
								&& !coverageBeanlist.get(i).getImage()
										.equals("")) {
						}
					} else {
						// sod images Data
						database.open();
						Posmlist = database
								.getInsertedPosmData(coverageBeanlist.get(i)
										.getStoreId());

						for (int j = 0; j < Posmlist.size(); j++) {

							if (Posmlist.get(j).getImage() != null
									&& !Posmlist.get(j).getImage().equals("")) {

								if (new File("/mnt/sdcard/GT_GSK_Images/"
										+ coverageBeanlist.get(i).getStoreId()
										+ "/" + Posmlist.get(j).getImage())
										.exists()) {

									result = UploadPOSMImage(coverageBeanlist
											.get(i).getStoreId(),

									Posmlist.get(j).getImage());

									if (result.toString().equalsIgnoreCase(
											CommonString.KEY_FALSE)) {

										return CommonString.METHOD_Get_DR_POSM_IMAGES;
									} else if (result
											.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

										return CommonString.METHOD_Get_DR_POSM_IMAGES
												+ "," + errormsg;
									}

									runOnUiThread(new Runnable() {

										public void run() {
											// TODO Auto-generated method
											// stub

											message.setText("POSM Images Uploaded");
										}
									});
								}
							}

						}
						// secondary window images

						compliancelist = database
								.getInsertedComplianceData(coverageBeanlist
										.get(i).getStoreId());
						for (int j = 0; j < compliancelist.size(); j++) {

							if (compliancelist.get(j).getImage() != null
									&& !compliancelist.get(j).getImage()
											.equals("")) {

								if (new File("/mnt/sdcard/GT_GSK_Images/"
										+ coverageBeanlist.get(i).getStoreId()
										+ "/"
										+ compliancelist.get(j).getImage())
										.exists()) {

									result = UploadComplianceImage(
											coverageBeanlist.get(i)
													.getStoreId(),
											compliancelist.get(j).getImage());

									if (result.toString().equalsIgnoreCase(
											CommonString.KEY_FALSE)) {

										return CommonString.METHOD_Get_DR_COMPLIANCE_IMAGES;
									} else if (result
											.equalsIgnoreCase(CommonString.KEY_FAILURE)) {

										return CommonString.METHOD_Get_DR_COMPLIANCE_IMAGES
												+ "," + errormsg;
									}

									runOnUiThread(new Runnable() {

										public void run() {
											// TODO Auto-generated method
											// stub

											message.setText("Secondary Window Images Uploaded");
										}
									});
								}
							}

						}

					}

					database.open();
					database.deleteStoreMidData(coverageBeanlist.get(i)
							.getMID(), coverageBeanlist.get(i).getStoreId());
					database.updateStoreStatus(coverageBeanlist.get(i)
							.getStoreId(), coverageBeanlist.get(i)
							.getVisitDate());

					// SET COVERAGE STATUS
					String statusxml = "[DATA][USER_DATA][STORE_ID]"
							+ coverageBeanlist.get(i).getStoreId()
							+ "[/STORE_ID][CREATED_BY]" + username
							+ "[/CREATED_BY][VISIT_DATE]"
							+ coverageBeanlist.get(i).getVisitDate()
							+ "[/VISIT_DATE][STATUS]" + CommonString.KEY_U
							+ "[/STATUS][/USER_DATA][/DATA]";

					SoapObject request = new SoapObject(
							CommonString.NAMESPACE,
							CommonString.METHOD_SET_COVERAGE_STATUS);
					request.addProperty("onXML", statusxml);

					SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
							SoapEnvelope.VER11);
					envelope.dotNet = true;
					envelope.setOutputSoapObject(request);

					HttpTransportSE androidHttpTransport = new HttpTransportSE(
							CommonString.URL);

					androidHttpTransport
							.call(CommonString.SOAP_ACTION_SET_COVERAGE_STATUS,
									envelope);
					Object result = (Object) envelope.getResponse();

					if (!result.toString().equalsIgnoreCase(
							CommonString.KEY_SUCCESS)) {

						if (result.toString().equalsIgnoreCase(
								CommonString.KEY_FALSE)) {

						}

						SAXParserFactory saxPF = SAXParserFactory.newInstance();
						SAXParser saxP = saxPF.newSAXParser();
						XMLReader xmlR = saxP.getXMLReader();

					}

					if (result.toString().equalsIgnoreCase(
							CommonString.KEY_SUCCESS)) {

					}

				}

				// for geotag

				return CommonString.KEY_SUCCESS;
			} catch (MalformedURLException e) {

				final AlertMessage message = new AlertMessage(
						UploadImageActivity.this,
						AlertMessage.MESSAGE_EXCEPTION, "download", e);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						message.showMessage();
					}
				});

			} catch (IOException e) {
				final AlertMessage message = new AlertMessage(
						UploadImageActivity.this,
						AlertMessage.MESSAGE_SOCKETEXCEPTION,
						"socket_uploadimage", e);
				counter++;
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						message.showMessage();
						/*
						 * if (counter < 3) { new
						 * UploadTask(UploadImageActivity.this).execute(); }
						 * else { message.showMessage(); counter = 1; }
						 */
					}
				});
			}

			catch (Exception e) {
				final AlertMessage message = new AlertMessage(
						UploadImageActivity.this,
						AlertMessage.MESSAGE_EXCEPTION, "download", e);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						message.showMessage();
					}
				});
			}

			return "";
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			dialog.dismiss();

			if (result.equals(CommonString.KEY_SUCCESS)) {
				AlertMessage message = new AlertMessage(
						UploadImageActivity.this,
						AlertMessage.MESSAGE_UPLOAD_IMAGE, "success", null);
				message.showMessage();
			} else if (!result.equals("")) {
				AlertMessage message = new AlertMessage(
						UploadImageActivity.this, result, "success", null);
				message.showMessage();
			}
		}

		public String UploadPOSMImage(String storeid, String path)
				throws Exception {

			errormsg = "";
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeFile("/mnt/sdcard/GT_GSK_Images/" + storeid
					+ "/" + path, o);

			// The new size we want to scale to
			final int REQUIRED_SIZE = 1024;

			// Find the correct scale value. It should be the power of 2.
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;

			while (true) {
				if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// Decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			Bitmap bitmap = BitmapFactory.decodeFile(
					"/mnt/sdcard/GT_GSK_Images/" + storeid + "/" + path, o2);

			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
			byte[] ba = bao.toByteArray();
			String ba1 = Base64.encodeBytes(ba);

			SoapObject request = new SoapObject(CommonString.NAMESPACE,
					CommonString.METHOD_Get_DR_POSM_IMAGES);

			request.addProperty("img", ba1);
			request.addProperty("name", path);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);

			HttpTransportSE androidHttpTransport = new HttpTransportSE(
					CommonString.URL);

			androidHttpTransport.call(
					CommonString.SOAP_ACTION_Get_DR_POSM_IMAGES, envelope);
			Object result = (Object) envelope.getResponse();

			if (!result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {

				if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
					return CommonString.KEY_FALSE;
				}

				SAXParserFactory saxPF = SAXParserFactory.newInstance();
				SAXParser saxP = saxPF.newSAXParser();
				XMLReader xmlR = saxP.getXMLReader();

				// for failure
				FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
				xmlR.setContentHandler(failureXMLHandler);

				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(result.toString()));
				xmlR.parse(is);

				failureGetterSetter = failureXMLHandler
						.getFailureGetterSetter();

				if (failureGetterSetter.getStatus().equalsIgnoreCase(
						CommonString.KEY_FAILURE)) {
					errormsg = failureGetterSetter.getErrorMsg();
					return CommonString.KEY_FAILURE;
				}
			} else {
				new File("/mnt/sdcard/GT_GSK_Images/" + storeid + "/" + path)
						.delete();
			}

			return "";
		}

		public String UploadComplianceImage(String storeid, String path)
				throws Exception {

			errormsg = "";
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeFile("/mnt/sdcard/GT_GSK_Images/" + storeid
					+ "/" + path, o);

			// The new size we want to scale to
			final int REQUIRED_SIZE = 1024;

			// Find the correct scale value. It should be the power of 2.
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;

			while (true) {
				if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// Decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			Bitmap bitmap = BitmapFactory.decodeFile(
					"/mnt/sdcard/GT_GSK_Images/" + storeid + "/" + path, o2);

			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
			byte[] ba = bao.toByteArray();
			String ba1 = Base64.encodeBytes(ba);

			SoapObject request = new SoapObject(CommonString.NAMESPACE,
					CommonString.METHOD_Get_DR_COMPLIANCE_IMAGES);

			request.addProperty("img", ba1);
			request.addProperty("name", path);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);

			HttpTransportSE androidHttpTransport = new HttpTransportSE(
					CommonString.URL);

			androidHttpTransport
					.call(CommonString.SOAP_ACTION_Get_DR_COMPLIANCE_IMAGES,
							envelope);
			Object result = (Object) envelope.getResponse();

			if (!result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {

				if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
					return CommonString.KEY_FALSE;
				}

				SAXParserFactory saxPF = SAXParserFactory.newInstance();
				SAXParser saxP = saxPF.newSAXParser();
				XMLReader xmlR = saxP.getXMLReader();

				// for failure
				FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
				xmlR.setContentHandler(failureXMLHandler);

				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(result.toString()));
				xmlR.parse(is);

				failureGetterSetter = failureXMLHandler
						.getFailureGetterSetter();

				if (failureGetterSetter.getStatus().equalsIgnoreCase(
						CommonString.KEY_FAILURE)) {
					errormsg = failureGetterSetter.getErrorMsg();
					return CommonString.KEY_FAILURE;
				}
			} else {
				new File("/mnt/sdcard/GT_GSK_Images/" + storeid + "/" + path)
						.delete();
			}

			return "";
		}

	}
}
