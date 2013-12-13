package com.cpm.upload;

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
import com.cpm.delegates.SkuStoreBean;
import com.cpm.gt_gsk.R;
import com.cpm.message.AlertMessage;
import com.cpm.xmlGetterSetter.FailureGetterSetter;
import com.cpm.xmlHandler.FailureXMLHandler;

public class UploadDataActivity extends Activity {

	private Dialog dialog;
	private ProgressBar pb;
	private TextView percentage, message;
	String app_ver;
	private String visit_date, username;
	private SharedPreferences preferences;
	private PepsicoDatabase database;
	private String reasonid, faceup, stock, length;
	private int factor, k;
	String datacheck = "";
	String[] words;
	String validity, storename;
	int mid;
	String sod = "";
	String total_sku = "";
	String sku = "";
	String sos_data = "";
	String category_data = "";

	private ArrayList<CoverageBean> coverageBeanlist = new ArrayList<CoverageBean>();
	private ArrayList<PosmBean> Posmlist = new ArrayList<PosmBean>();
	private ArrayList<SkuStoreBean> assetlist = new ArrayList<SkuStoreBean>();
	private ArrayList<ComplianceBean> Promotion = new ArrayList<ComplianceBean>();
	private FailureGetterSetter failureGetterSetter = null;
	static int counter = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload_option);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		visit_date = preferences.getString(CommonString.KEY_DATE, null);
		username = preferences.getString(CommonString.KEY_USERNAME, null);
		app_ver = preferences.getString(CommonString.KEY_VERSION, "");
		database = new PepsicoDatabase(this);
		database.open();

		new UploadTask(this).execute();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		database.close();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		Intent i = new Intent(this, DailyentryMenuActivity.class);
		startActivity(i);
		UploadDataActivity.this.finish();
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
			dialog.setTitle("Uploading Data");
			dialog.setCancelable(false);
			dialog.show();
			pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
			percentage = (TextView) dialog.findViewById(R.id.percentage);
			message = (TextView) dialog.findViewById(R.id.message);
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			dialog.dismiss();

			if (result.equals(CommonString.KEY_SUCCESS)) {

				AlertMessage message = new AlertMessage(
						UploadDataActivity.this,
						AlertMessage.MESSAGE_UPLOAD_DATA, "success", null);
				message.showMessage();
			} else if (!result.equals("")) {
				AlertMessage message = new AlertMessage(
						UploadDataActivity.this, AlertMessage.MESSAGE_ERROR
								+ result, "success", null);
				message.showMessage();
			}

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

				// uploading coverage

				SAXParserFactory saxPF = SAXParserFactory.newInstance();
				SAXParser saxP = saxPF.newSAXParser();
				XMLReader xmlR = saxP.getXMLReader();

				for (int i = 0; i < coverageBeanlist.size(); i++) {
					if (!coverageBeanlist.get(i).getStatus()
							.equalsIgnoreCase("D")) {

						String onXML = "[DATA][USER_DATA][STORE_ID]"
								+ coverageBeanlist.get(i).getStoreId()
								+ "[/STORE_ID]" + "[VISIT_DATE]"
								+ coverageBeanlist.get(i).getVisitDate()
								+ "[/VISIT_DATE][LATITUDE]"
								+ coverageBeanlist.get(i).getLatitude()
								+ "[/LATITUDE][LONGITUDE]"
								+ coverageBeanlist.get(i).getLongitude()
								+ "[/LONGITUDE][INTIME]"
								+ coverageBeanlist.get(i).getInTime()
								+ "[/INTIME][OUTTIME]"
								+ coverageBeanlist.get(i).getOutTime()
								+ "[/OUTTIME][UPLOAD_STATUS]P"
								+ "[/UPLOAD_STATUS][CREATED_BY]" + username
								+ "[/CREATED_BY][REASON_REMARK]"
								+ coverageBeanlist.get(i).getRemark()
								+ "[/REASON_REMARK][REASON_ID]"
								+ coverageBeanlist.get(i).getReasonid()
								+ "[/REASON_ID][APP_VERSION]" + app_ver
								+ "[/APP_VERSION]" + "[/USER_DATA][/DATA]";

						SoapObject request = new SoapObject(
								CommonString.NAMESPACE,
								CommonString.METHOD_UPLOAD_DR_STORE_COVERAGE);
						request.addProperty("onXML", onXML);

						SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
								SoapEnvelope.VER11);
						envelope.dotNet = true;
						envelope.setOutputSoapObject(request);

						HttpTransportSE androidHttpTransport = new HttpTransportSE(
								CommonString.URL);

						androidHttpTransport
								.call(CommonString.SOAP_ACTION_UPLOAD_DR_STORE_COVERAGE,
										envelope);
						Object result = (Object) envelope.getResponse();

						datacheck = result.toString();
						words = datacheck.split("\\;");
						validity = (words[0]);

						if (validity.equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
							database.updateCoverageStatus(
									coverageBeanlist.get(i).getMID(),
									CommonString.KEY_P);

							database.updateStoreStatusOnLeave(coverageBeanlist
									.get(i).getStoreId(), visit_date,
									CommonString.KEY_P);
						} else {
							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_FALSE)) {
								return CommonString.METHOD_UPLOAD_DR_STORE_COVERAGE;
							}

							// for failure
							FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
							xmlR.setContentHandler(failureXMLHandler);

							InputSource is = new InputSource();
							is.setCharacterStream(new StringReader(result
									.toString()));
							xmlR.parse(is);

							failureGetterSetter = failureXMLHandler
									.getFailureGetterSetter();

							if (failureGetterSetter.getStatus()
									.equalsIgnoreCase(CommonString.KEY_FAILURE)) {
								return CommonString.METHOD_UPLOAD_DR_STORE_COVERAGE
										+ ","
										+ failureGetterSetter.getErrorMsg();
							}
						}
						mid = Integer.parseInt((words[1]));

						// storename =
						// database.getStoreName(coverageBeanlist.get(
						// i).getStoreId());

						runOnUiThread(new Runnable() {

							public void run() {
								// TODO Auto-generated method stub
								k = k + factor;
								pb.setProgress(k);
								percentage.setText(k + "%");
								message.setText(storename
										+ " Data is Uploading");
							}
						});

						if (!(coverageBeanlist.get(i).getReasonid()
								.equalsIgnoreCase("")
								|| coverageBeanlist.get(i).getReasonid()
										.equalsIgnoreCase(null) || coverageBeanlist
								.get(i).getReasonid().equalsIgnoreCase("0"))) {

							/*
							 * database.updateStoreStatusOnCheckout(
							 * coverageBeanlist.get(i).getStoreId(), visit_date,
							 * CommonString.KEY_L);
							 */
							System.out.println("");
						}

						else {

							assetlist = database
									.ViewInsertedSkuStock(coverageBeanlist.get(
											i).getMID());

							if (!(assetlist.size() == 0)) {

								for (int j = 0; j < assetlist.size(); j++) {

									String stock = "";
									String faceup = "";
									if (assetlist.get(j).getFaceup()
											.equalsIgnoreCase("")) {
										faceup = "0";
									} else {
										faceup = assetlist.get(j).getFaceup();
									}

									if (assetlist.get(j).getStock()
											.equalsIgnoreCase("")) {
										stock = "0";
									} else {
										stock = assetlist.get(j).getStock();
									}

									onXML = "[DATA][USER_DATA][MID]" + mid
											+ "[/MID][CREATED_BY]" + username
											+ "[/CREATED_BY][SKU_CD]"
											+ assetlist.get(j).getSkuId()
											+ "[/SKU_CD]" + "[FACEUP]" + faceup
											+ "[/FACEUP][STOCK_QTY]" + stock
											+ "[/STOCK_QTY]" + "[REMARK]"
											+ username + "[/REMARK]"
											+ "[/USER_DATA][/DATA]";

									request = new SoapObject(
											CommonString.NAMESPACE,
											CommonString.METHOD_UPLOAD_ASSET);
									request.addProperty("onXML", onXML);

									envelope = new SoapSerializationEnvelope(
											SoapEnvelope.VER11);
									envelope.dotNet = true;
									envelope.setOutputSoapObject(request);

									androidHttpTransport = new HttpTransportSE(
											CommonString.URL);

									androidHttpTransport
											.call(CommonString.SOAP_ACTION_UPLOAD_ASSET,
													envelope);
									result = (Object) envelope.getResponse();

									if (!result.toString().equalsIgnoreCase(
											CommonString.KEY_SUCCESS)) {

										if (result.toString().equalsIgnoreCase(
												CommonString.KEY_FALSE)) {
											return CommonString.METHOD_UPLOAD_ASSET;
										}

										FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
										xmlR.setContentHandler(failureXMLHandler);

										InputSource is = new InputSource();
										is.setCharacterStream(new StringReader(
												result.toString()));
										xmlR.parse(is);

										failureGetterSetter = failureXMLHandler
												.getFailureGetterSetter();

										if (failureGetterSetter
												.getStatus()
												.equalsIgnoreCase(
														CommonString.KEY_FAILURE)) {
											return CommonString.METHOD_UPLOAD_ASSET
													+ ","
													+ failureGetterSetter
															.getErrorMsg();
										}
									}
								}
							}
							runOnUiThread(new Runnable() {

								public void run() { // TODO
													// Auto-generated
													// method

								}
							});

							// uploading posm data

							Posmlist = database
									.getInsertedPosmData(coverageBeanlist
											.get(i).getStoreId());

							if (!(Posmlist.size() == 0)) {

								for (int j = 0; j < Posmlist.size(); j++) {

									String quantity = "";

									if (Posmlist.get(j).getQuantity()
											.equalsIgnoreCase("")) {
										quantity = "0";
									} else {
										quantity = Posmlist.get(j)
												.getQuantity();
									}

									onXML = "[DATA][USER_DATA][MID]" + mid
											+ "[/MID][CREATED_BY]" + username
											+ "[/CREATED_BY][POSM_CD]"
											+ Posmlist.get(j).getPosm_id()
											+ "[/POSM_CD]" + "[QTY]" + quantity
											+ "[/QTY][IMAGE_URL]"
											+ Posmlist.get(j).getImage()
											+ "[/IMAGE_URL]"
											+ "[/USER_DATA][/DATA]";

									request = new SoapObject(
											CommonString.NAMESPACE,
											CommonString.METHOD_UPLOAD_POSM);
									request.addProperty("onXML", onXML);

									envelope = new SoapSerializationEnvelope(
											SoapEnvelope.VER11);
									envelope.dotNet = true;
									envelope.setOutputSoapObject(request);

									androidHttpTransport = new HttpTransportSE(
											CommonString.URL);

									androidHttpTransport
											.call(CommonString.SOAP_ACTION_UPLOAD_POSM,
													envelope);
									result = (Object) envelope.getResponse();

									if (!result.toString().equalsIgnoreCase(
											CommonString.KEY_SUCCESS)) {

										if (result.toString().equalsIgnoreCase(
												CommonString.KEY_FALSE)) {
											return CommonString.SOAP_ACTION_UPLOAD_POSM;
										}

										FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
										xmlR.setContentHandler(failureXMLHandler);

										InputSource is = new InputSource();
										is.setCharacterStream(new StringReader(
												result.toString()));
										xmlR.parse(is);

										failureGetterSetter = failureXMLHandler
												.getFailureGetterSetter();

										if (failureGetterSetter
												.getStatus()
												.equalsIgnoreCase(
														CommonString.KEY_FAILURE)) {
											return CommonString.METHOD_UPLOAD_ASSET
													+ ","
													+ failureGetterSetter
															.getErrorMsg();
										}
									}
								}
							}
							runOnUiThread(new Runnable() {

								public void run() { // TODO
													// Auto-generated
													// method

									message.setText("Assets Data Upload");
								}
							});

							Promotion = database
									.getInsertedComplianceData(coverageBeanlist
											.get(i).getStoreId());

							if (!(Promotion.size() == 0)) {

								for (int j = 0; j < Promotion.size(); j++) {

									onXML = "[DATA][USER_DATA][MID]"
											+ mid
											+ "[/MID][CREATED_BY]"
											+ username
											+ "[/CREATED_BY][PROMOTION_CD]"
											+ Promotion.get(j).getPromtion_cd()
											+ "[/PROMOTION_CD]"
											+ "[COMPLIANCE_CD]"
											+ Promotion.get(j)
													.getCompliance_id()
											+ "[/COMPLIANCE_CD][COMPLIANCE_VALID]"
											+ Promotion.get(j)
													.getAvailability()
											+ "[/COMPLIANCE_VALID]"
											+ "[IMAGE_URL]"
											+ Promotion.get(j).getImage()
											+ "[/IMAGE_URL]"
											+ "[/USER_DATA][/DATA]";

									request = new SoapObject(
											CommonString.NAMESPACE,
											CommonString.METHOD_UPLOAD_COMPLIANCE);
									request.addProperty("onXML", onXML);

									envelope = new SoapSerializationEnvelope(
											SoapEnvelope.VER11);
									envelope.dotNet = true;
									envelope.setOutputSoapObject(request);

									androidHttpTransport = new HttpTransportSE(
											CommonString.URL);

									androidHttpTransport
											.call(CommonString.SOAP_ACTION_COMPLIANCE,
													envelope);
									result = (Object) envelope.getResponse();

									if (!result.toString().equalsIgnoreCase(
											CommonString.KEY_SUCCESS)) {

										if (result.toString().equalsIgnoreCase(
												CommonString.KEY_FALSE)) {
											return CommonString.SOAP_ACTION_COMPLIANCE;
										}

										FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
										xmlR.setContentHandler(failureXMLHandler);

										InputSource is = new InputSource();
										is.setCharacterStream(new StringReader(
												result.toString()));
										xmlR.parse(is);

										failureGetterSetter = failureXMLHandler
												.getFailureGetterSetter();

										if (failureGetterSetter
												.getStatus()
												.equalsIgnoreCase(
														CommonString.KEY_FAILURE)) {
											return CommonString.METHOD_UPLOAD_ASSET
													+ ","
													+ failureGetterSetter
															.getErrorMsg();
										}
									}
								}
							}
							runOnUiThread(new Runnable() {

								public void run() { // TODO
													// Auto-generated
													// method
									k = k + factor;
									pb.setProgress(k);
									percentage.setText(k + "%");
									message.setText(storename
											+ " Data is Uploading");
									message.setText("Assets Data Upload");
								}
							});
						}

						database.open();
						database.updateCoverageStatus(coverageBeanlist.get(i)
								.getMID(), CommonString.KEY_D);

						database.updateStoreStatusOnLeave(
								coverageBeanlist.get(i).getStoreId(),
								visit_date, CommonString.KEY_D);

						// SET COVERAGE STATUS

						String statusxml = "[DATA][USER_DATA][STORE_ID]"
								+ coverageBeanlist.get(i).getStoreId()
								+ "[/STORE_ID][CREATED_BY]" + username
								+ "[/CREATED_BY][VISIT_DATE]"
								+ coverageBeanlist.get(i).getVisitDate()
								+ "[/VISIT_DATE][STATUS]" + CommonString.KEY_D
								+ "[/STATUS][/USER_DATA][/DATA]";

						request = new SoapObject(CommonString.NAMESPACE,
								CommonString.METHOD_SET_COVERAGE_STATUS);

						request.addProperty("onXML", statusxml);

						envelope = new SoapSerializationEnvelope(
								SoapEnvelope.VER11);
						envelope.dotNet = true;
						envelope.setOutputSoapObject(request);

						androidHttpTransport = new HttpTransportSE(
								CommonString.URL);

						androidHttpTransport.call(
								CommonString.SOAP_ACTION_SET_COVERAGE_STATUS,
								envelope);
						result = (Object) envelope.getResponse();
						
						if (result.toString().equalsIgnoreCase(
								CommonString.KEY_SUCCESS)) {

						} else {
							if (result.toString().equalsIgnoreCase(
									CommonString.KEY_FALSE)) {

							}

						}


					}
				}

				return CommonString.KEY_SUCCESS;

			}

			catch (Exception ex) {
				ex.printStackTrace();
			}
			return "";
		}
	}
}
