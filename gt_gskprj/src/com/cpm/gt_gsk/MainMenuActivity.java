package com.cpm.gt_gsk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.autoupdate.AutoupdateActivity;
import com.cpm.dailyEntry.StorelistActivity;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.StoreBean;
import com.cpm.download.CompleteDownloadActivity;
import com.cpm.message.AlertMessage;

import com.cpm.upload.UploadDataActivity;
import com.cpm.upload.UploadOptionActivity;

public class MainMenuActivity extends Activity implements LocationListener {

	private Intent intent;
	private Dialog dialog;
	private ProgressBar pb;
	private PepsicoDatabase database;
	private int versionCode;
	private SharedPreferences preferences;
	private String date;
	private TextView percentage, message;
	private ArrayList<CoverageBean> coverageBeanlist = new ArrayList<CoverageBean>();
	private ArrayList<StoreBean> list = new ArrayList<StoreBean>();

	private LocationManager locmanager = null;
	private double latitude = 0.0, longitude = 0.0;
	String status;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainpage);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		date = preferences.getString(CommonString.KEY_DATE, null);

		database = new PepsicoDatabase(this);
		database.open();

		locmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
		intent.putExtra("enabled", false);
		sendBroadcast(intent);

		try {
			versionCode = getPackageManager().getPackageInfo(getPackageName(),
					0).versionCode;
		} catch (Exception e) {

		}

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		database.close();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		database.open();
	}

	public void onButtonClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.dash_dailyentry:

			list = database.getStoreData(date);

			if (list.size() == 0) {
				Toast.makeText(getBaseContext(), "Please Download Data First",
						Toast.LENGTH_LONG).show();
			} else {
				intent = new Intent(getBaseContext(), StorelistActivity.class);
				startActivity(intent);

				MainMenuActivity.this.finish();

			}

			break;
		case R.id.dash_download:

			if (CheckNetAvailability()) {

				if (AllowDownload()) {

					if (!preferences.getString(CommonString.KEY_VERSION, "")
							.equals(Integer.toString(versionCode))) {

						showToast("Please Update the Application first");

					} else {
						intent = new Intent(getBaseContext(),
								CompleteDownloadActivity.class);
						startActivity(intent);
					}

				} else {

					AlertDialog.Builder builder = new AlertDialog.Builder(
							MainMenuActivity.this);
					builder.setMessage("Please Upload Previous Data First")
							.setCancelable(false)
							.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {

											/*
											 * intent = new Intent(
											 * getBaseContext(),
											 * UploadAllDataActivity.class);
											 * startActivity(intent);
											 */

										}
									});

					AlertDialog alert = builder.create();
					alert.show();
				}

			} else {
				showToast("No Network Available");
			}

			break;
		case R.id.dash_upload:
			if (CheckNetAvailability()) {
				intent = new Intent(getBaseContext(),
						UploadOptionActivity.class);
				startActivity(intent);

				MainMenuActivity.this.finish();

			} else {
				showToast("No Network Available");
			}
			break;
		case R.id.auto_update:
			if (CheckNetAvailability()) {

				if (!preferences.getString(CommonString.KEY_VERSION, "")
						.equals(Integer.toString(versionCode))) {
					intent = new Intent(getBaseContext(),
							AutoupdateActivity.class);
					intent.putExtra(CommonString.KEY_PATH,
							preferences.getString(CommonString.KEY_PATH, ""));
					intent.putExtra(CommonString.KEY_STATUS, true);
					startActivity(intent);
					MainMenuActivity.this.finish();

				} else {
					Toast.makeText(getBaseContext(), "No Updates",
							Toast.LENGTH_LONG).show();
				}

			} else {
				showToast("No Network Available");
			}
			break;
		case R.id.exit:
			AlertMessage message = new AlertMessage(this,
					AlertMessage.MESSAGE_EXIT, "exit", null);
			message.showMessage();

			break;
		case R.id.geotag:

			break;

		}
	}

	public boolean AllowDownload() {

		boolean result = true;

		coverageBeanlist = database.getCoverageData(null, null);

		for (int i = 0; i < coverageBeanlist.size(); i++) {

			if (!(coverageBeanlist.get(i).getVisitDate().equals(date))) {

				result = false;
				break;

			}

		}

		return result;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

	}

	public boolean CheckNetAvailability() {

		boolean connected = false;
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState() == NetworkInfo.State.CONNECTED
				|| connectivityManager.getNetworkInfo(
						ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
			// we are connected to a network
			connected = true;
		}
		return connected;
	}

	private void showToast(String message) {
		Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	public String getCurrentTime() {

		Calendar m_cal = Calendar.getInstance();

		String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);

		return intime;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.backup:

			AlertDialog.Builder builder1 = new AlertDialog.Builder(
					MainMenuActivity.this);
			builder1.setMessage(
					"Are you sure you want to take the backup of your data")
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									try {

										File file = new File(Environment
												.getExternalStorageDirectory(),
												"DroidSystem");
										if (!file.isDirectory()) {
											file.mkdir();
										}

										File sd = Environment
												.getExternalStorageDirectory();
										File data = Environment
												.getDataDirectory();

										if (sd.canWrite()) {
											String currentDBPath = "//data//com.cpm.parinaampepsico//databases//PEPSICO_DATABASE";
											String backupDBPath = "SystemDB"
													+ date.replace('/', '-');

											File currentDB = new File(data,
													currentDBPath);
											File backupDB = new File(
													"/mnt/sdcard/DroidSystem/",
													backupDBPath);

											if (currentDB.exists()) {
												FileChannel src = new FileInputStream(
														currentDB).getChannel();
												FileChannel dst = new FileOutputStream(
														backupDB).getChannel();
												dst.transferFrom(src, 0,
														src.size());
												src.close();
												dst.close();
											}
										}
									} catch (Exception e) {
										System.out.println(e.getMessage());
									}

								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			AlertDialog alert1 = builder1.create();
			alert1.show();

			break;
		}
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		latitude = location.getLatitude();
		longitude = location.getLongitude();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
