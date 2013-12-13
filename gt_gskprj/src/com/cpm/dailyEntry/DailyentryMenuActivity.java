package com.cpm.dailyEntry;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.ComplianceBean;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.PosmBean;
import com.cpm.delegates.SkuStoreBean;
import com.cpm.gt_gsk.R;


public class DailyentryMenuActivity extends Activity {

	private PepsicoDatabase database;
	private Bundle extras = null;
	private String store_id, store_address, store_name, visit_date, username,
			store_latitude, store_longitude;
	private Intent intent;
	private TextView text;
	private SharedPreferences preferences = null;
	private SharedPreferences.Editor editor = null;

	public static ArrayList<SkuStoreBean> stockdata = new ArrayList<SkuStoreBean>();
	private static ArrayList<ComplianceBean> compliance_list = new ArrayList<ComplianceBean>();
	private static ArrayList<PosmBean> posm_list = new ArrayList<PosmBean>();
	private ArrayList<CoverageBean> coverageBeanlist = new ArrayList<CoverageBean>();

	boolean sos, sod, assest, promotion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dailyactivitymenu);

		//text = (TextView) findViewById(R.id.mainpage_remembermetext);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		store_name = preferences.getString(CommonString.KEY_STORE_NAME, "");
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");
		visit_date = preferences.getString(CommonString.KEY_DATE, "");
		username = preferences.getString(CommonString.KEY_USERNAME, "");

		database = new PepsicoDatabase(this);
		database.open();

		

		//text.setText(store_name);
	}

	public void onButtonClick(View v) {
		switch (v.getId()) {
		case R.id.btn_news_training:
			intent = new Intent(this, SkuDisplay.class);
			startActivity(intent);
			DailyentryMenuActivity.this.finish();
			break;
		case R.id.btn_posmtheme:
			intent = new Intent(this, SecondaryScreenwindow.class);
			startActivity(intent);
			DailyentryMenuActivity.this.finish();
			break;
		case R.id.btn_flex:

			intent = new Intent(this, PosmDeployed.class);
			startActivity(intent);
			DailyentryMenuActivity.this.finish();
			break;
	

		}

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
		filldata();

	
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		intent = new Intent(this, StorelistActivity.class);
		startActivity(intent);
		DailyentryMenuActivity.this.finish();
	}

	public long checkMid() {
		return database.CheckMid(visit_date, store_id);
	}

	public String getCurrentTime() {

		Calendar m_cal = Calendar.getInstance();

		String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);

		return intime;

	}
	
	
	
	private void filldata() {

		coverageBeanlist = database.getCoverageData(visit_date, store_id);

		for (int i = 0; i < coverageBeanlist.size(); i++) {

			stockdata = database.ViewInsertedSkuStock(coverageBeanlist.get(i).getMID());

			if (stockdata.size() > 0) {
				((Button) findViewById(R.id.btn_news_training))
						.setBackgroundResource(
										R.drawable.focussku_tick);

			}

			compliance_list = database.getInsertedComplianceData(store_id);
			
			if (compliance_list.size() > 0) {

				((Button) findViewById(R.id.btn_posmtheme))
						.setBackgroundResource(
										R.drawable.shareofshelf_tick);
			}

			posm_list = database.getInsertedPosmData(store_id);
			if (posm_list.size() > 0) {
				((Button) findViewById(R.id.btn_flex))
						.setBackgroundResource(
								
							
										R.drawable.posm_deployed_tick_ico);
			}

			

			

			
			

		
		}}
		

	
}
