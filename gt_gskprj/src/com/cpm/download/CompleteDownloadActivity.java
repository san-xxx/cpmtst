package com.cpm.download;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.message.AlertMessage;
import com.cpm.gt_gsk.R;
import com.cpm.xmlGetterSetter.ComplianceByMappingGetterSetter;
import com.cpm.xmlGetterSetter.ComplianceGetterSetter;
import com.cpm.xmlGetterSetter.FailureGetterSetter;
import com.cpm.xmlGetterSetter.JCPGetterSetter;
import com.cpm.xmlGetterSetter.PosmGetterSetter;

import com.cpm.xmlGetterSetter.NonWrkingMasterGetterSetter;
import com.cpm.xmlGetterSetter.PromotionalMasterGettersetter;
import com.cpm.xmlGetterSetter.SkuMasterGetterSetter;
import com.cpm.xmlHandler.XMLHandlers;

public class CompleteDownloadActivity extends Activity {

	JCPGetterSetter jcpdata = null;
	PromotionalMasterGettersetter promotionMaster = null;
	SkuMasterGetterSetter SkuMastergettter = null;
	NonWrkingMasterGetterSetter NonWorkingMaster = null;
	ComplianceGetterSetter Compliancegettersetter = null;
	ComplianceByMappingGetterSetter complianceMappingGetterSetter = null;
	PosmGetterSetter posmdownloaded = null;

	private FailureGetterSetter failureGetterSetter = null;

	private Dialog dialog;
	private ProgressBar pb;
	private TextView percentage, message;
	private String username, date;
	private Data data;
	private PepsicoDatabase db;

	private SharedPreferences preferences = null;
	static int counter = 1;
	int eventType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainpage);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		username = preferences.getString(CommonString.KEY_USERNAME, "");
		date = preferences.getString(CommonString.KEY_DATE, "");

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		db = new PepsicoDatabase(this);
		db.open();

		new BackgroundTask(this).execute();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		// db.open();

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		db.close();
	}

	private class BackgroundTask extends AsyncTask<Void, Data, String> {
		private Context context;

		BackgroundTask(Context context) {
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			dialog = new Dialog(context);
			dialog.setContentView(R.layout.custom);
			dialog.setTitle("Download Files");
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

				data = new Data();

				// JCP

				XmlPullParserFactory factory = XmlPullParserFactory
						.newInstance();
				factory.setNamespaceAware(true);
				XmlPullParser xpp = factory.newPullParser();

				SoapObject request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_NAME_JCP);
				request.addProperty("UserId", username);

				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				HttpTransportSE androidHttpTransport = new HttpTransportSE(
						CommonString.URL);

				androidHttpTransport.call(CommonString.SOAP_ACTION_JCP,
						envelope);
				Object result = (Object) envelope.getResponse();

				if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
					return CommonString.METHOD_NAME_JCP;
				}

				if (result.toString()
						.equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
					return CommonString.METHOD_NAME_JCP;
				}

				// for failure
				xpp.setInput(new StringReader(result.toString()));
				xpp.next();
				eventType = xpp.getEventType();
				FailureGetterSetter failureGetterSetter = XMLHandlers
						.failureXMLHandler(xpp, eventType);

				if (failureGetterSetter.getStatus().equalsIgnoreCase(
						CommonString.KEY_FAILURE)) {
					return CommonString.METHOD_NAME_JCP + ","
							+ failureGetterSetter.getErrorMsg();
				}

				xpp.setInput(new StringReader(result.toString()));
				xpp.next();
				eventType = xpp.getEventType();
				jcpdata = XMLHandlers.JCPXMLHandler(xpp, eventType);

				data.value = 10;
				data.name = "JCP Data Downloading";
				publishProgress(data);

				// starting download for promotional master

				request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_NAME_DownLoad_Promotional_Master);
				// request.addProperty("UserId", username);

				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				androidHttpTransport = new HttpTransportSE(CommonString.URL);

				androidHttpTransport.call(
						CommonString.SOAP_ACTION_Promotional_Master, envelope);
				result = (Object) envelope.getResponse();

				if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
					return CommonString.METHOD_NAME_DownLoad_Promotional_Master;
				}

				if (result.toString()
						.equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
					return CommonString.METHOD_NAME_DownLoad_Promotional_Master;
				}

				// for failure
				xpp.setInput(new StringReader(result.toString()));
				xpp.next();
				eventType = xpp.getEventType();
				failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
						eventType);

				if (failureGetterSetter.getStatus().equalsIgnoreCase(
						CommonString.KEY_FAILURE)) {
					return CommonString.METHOD_NAME_DownLoad_Promotional_Master
							+ "," + failureGetterSetter.getErrorMsg();
				}

				xpp.setInput(new StringReader(result.toString()));
				xpp.next();
				eventType = xpp.getEventType();
				promotionMaster = XMLHandlers.PromotionalmasterHandler(xpp,
						eventType);

				// ending download for promotional master

				data.value = 20;
				data.name = "Downloading Promotional data";
				publishProgress(data);

				// downloding data for sku master

				request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_NAME_DOWNLOAD_SKU_MASTER);

				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				androidHttpTransport = new HttpTransportSE(CommonString.URL);

				androidHttpTransport.call(
						CommonString.SOAP_ACTION_DOWNLAOD_SKU_MASTER, envelope);
				result = (Object) envelope.getResponse();

				if (result.toString()
						.equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
					return CommonString.METHOD_NAME_DOWNLOAD_SKU_MASTER;
				}

				// for failure

				xpp.setInput(new StringReader(result.toString()));
				xpp.next();
				eventType = xpp.getEventType();

				failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
						eventType);

				if (failureGetterSetter.getStatus().equalsIgnoreCase(
						CommonString.KEY_FAILURE)) {
					return CommonString.METHOD_NAME_DOWNLOAD_SKU_MASTER + ","
							+ failureGetterSetter.getErrorMsg();
				}

				xpp.setInput(new StringReader(result.toString()));
				xpp.next();
				eventType = xpp.getEventType();

				SkuMastergettter = XMLHandlers.SkuMaster(xpp, eventType);
				// need to make table for sku master and insert values

				data.value = 30;
				data.name = "Downloading SkuMaster";
				publishProgress(data);

				request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER);

				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				androidHttpTransport = new HttpTransportSE(CommonString.URL);

				androidHttpTransport.call(
						CommonString.SOAP_ACTION_DOWNLAOD_NON_WORKING_MASTER,
						envelope);
				result = (Object) envelope.getResponse();

				if (result.toString()
						.equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
					return CommonString.METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER;
				}

				xpp.setInput(new StringReader(result.toString()));
				xpp.next();
				eventType = xpp.getEventType();

				failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
						eventType);

				if (failureGetterSetter.getStatus().equalsIgnoreCase(
						CommonString.KEY_FAILURE)) {
					return CommonString.METHOD_NAME_DOWNLOAD_NON_WORKING_MASTER
							+ "," + failureGetterSetter.getErrorMsg();
				}

				xpp.setInput(new StringReader(result.toString()));
				xpp.next();
				eventType = xpp.getEventType();

				NonWorkingMaster = XMLHandlers.nonwrking(xpp, eventType);
				// need to make table for non working masterfdfd

				data.value = 50;
				data.name = "Downloading NonWorkingMaster";
				publishProgress(data);

				request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_NAME_DOWNLOAD_COMPLIANCE);

				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				androidHttpTransport = new HttpTransportSE(CommonString.URL);

				androidHttpTransport.call(
						CommonString.SOAP_ACTION_DOWNLAOD_COMPLIANCE, envelope);
				result = (Object) envelope.getResponse();

				if (result.toString()
						.equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
					return CommonString.METHOD_NAME_DOWNLOAD_COMPLIANCE;
				}

				// for failure

				xpp.setInput(new StringReader(result.toString()));
				xpp.next();
				eventType = xpp.getEventType();

				failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
						eventType);

				if (failureGetterSetter.getStatus().equalsIgnoreCase(
						CommonString.KEY_FAILURE)) {
					return CommonString.METHOD_NAME_DOWNLOAD_COMPLIANCE + ","
							+ failureGetterSetter.getErrorMsg();
				}

				xpp.setInput(new StringReader(result.toString()));
				xpp.next();
				eventType = xpp.getEventType();

				Compliancegettersetter = XMLHandlers.ComplianceXMLHandler(xpp,
						eventType);
				// need to make table for compliance

				data.value = 60;
				data.name = "Downloading ComplianceData";
				publishProgress(data);

				request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING);

				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				androidHttpTransport = new HttpTransportSE(CommonString.URL);

				androidHttpTransport.call(
						CommonString.SOAP_ACTION_DOWNLAOD_COMPLIANCE_MAPPING,
						envelope);
				result = (Object) envelope.getResponse();

				if (result.toString()
						.equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
					return CommonString.METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING;
				}

				// for failure

				xpp.setInput(new StringReader(result.toString()));
				xpp.next();
				eventType = xpp.getEventType();

				failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
						eventType);

				if (failureGetterSetter.getStatus().equalsIgnoreCase(
						CommonString.KEY_FAILURE)) {
					return CommonString.METHOD_NAME_DOWNLOAD_COMPLIANCE_MAPPING
							+ "," + failureGetterSetter.getErrorMsg();
				}

				xpp.setInput(new StringReader(result.toString()));
				xpp.next();
				eventType = xpp.getEventType();

				complianceMappingGetterSetter = XMLHandlers
						.ComplianceByMapping(xpp, eventType);
				// need to make table for compliance_mapping
				data.value = 70;
				data.name = "Downloading ComplianceMapping";
				publishProgress(data);

				// POSM Master

				data.value = 80;
				data.name = "Downloading POSMData";
				publishProgress(data);

				request = new SoapObject(CommonString.NAMESPACE,
						CommonString.METHOD_NAME_DOWNLOAD_POSM_MASTER);

				envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				envelope.dotNet = true;
				envelope.setOutputSoapObject(request);

				androidHttpTransport = new HttpTransportSE(CommonString.URL);

				androidHttpTransport
						.call(CommonString.SOAP_ACTION_DOWNLAOD_POSM_MASTER,
								envelope);
				result = (Object) envelope.getResponse();

				if (result.toString()
						.equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
					return CommonString.METHOD_NAME_DOWNLOAD_POSM_MASTER;
				}

				// for failure

				xpp.setInput(new StringReader(result.toString()));
				xpp.next();
				eventType = xpp.getEventType();

				failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
						eventType);

				if (failureGetterSetter.getStatus().equalsIgnoreCase(
						CommonString.KEY_FAILURE)) {
					return CommonString.METHOD_NAME_DOWNLOAD_POSM_MASTER + ","
							+ failureGetterSetter.getErrorMsg();
				}

				xpp.setInput(new StringReader(result.toString()));
				xpp.next();
				eventType = xpp.getEventType();

				posmdownloaded = XMLHandlers.Posm(xpp, eventType);

				db.open();
				db.InsertStoreData(jcpdata, date);
				db.InsertPromotion_data(promotionMaster);
				db.InsertskuData(SkuMastergettter);
				db.InsertNonWorkingReason(NonWorkingMaster);
				db.InsertCompliance(Compliancegettersetter);
				db.InsertCompliancemapping(complianceMappingGetterSetter);
				db.InsertPosm(posmdownloaded);

				data.value = 100;
				data.name = "Finishing";
				publishProgress(data);

				return CommonString.KEY_SUCCESS;

			} catch (MalformedURLException e) {

				final AlertMessage message = new AlertMessage(
						CompleteDownloadActivity.this,
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
						CompleteDownloadActivity.this,
						AlertMessage.MESSAGE_SOCKETEXCEPTION, "socket", e);
				counter++;
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub

						if (counter < 3) {
							new BackgroundTask(CompleteDownloadActivity.this)
									.execute();
						} else {
							message.showMessage();
							counter = 1;
						}

					}
				});
			}

			catch (Exception e) {
				final AlertMessage message = new AlertMessage(
						CompleteDownloadActivity.this,
						AlertMessage.MESSAGE_EXCEPTION, "download", e);

				e.getMessage();
				e.printStackTrace();
				e.getCause();
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
		protected void onProgressUpdate(Data... values) {
			// TODO Auto-generated method stub

			pb.setProgress(values[0].value);
			percentage.setText(values[0].value + "%");
			message.setText(values[0].name);

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();

			if (result.equals(CommonString.KEY_SUCCESS)) {
				AlertMessage message = new AlertMessage(
						CompleteDownloadActivity.this,
						AlertMessage.MESSAGE_DOWNLOAD, "success", null);
				message.showMessage();
			} else if (result.equals(CommonString.METHOD_NAME_JCP)) {
				AlertMessage message = new AlertMessage(
						CompleteDownloadActivity.this,
						AlertMessage.MESSAGE_JCP_FALSE, "success", null);
				message.showMessage();
			}

			else if (!result.equals("")) {
				AlertMessage message = new AlertMessage(
						CompleteDownloadActivity.this,
						AlertMessage.MESSAGE_ERROR + result, "success", null);
				message.showMessage();
			}

		}

	}

	class Data {
		int value;
		String name;
	}

}
