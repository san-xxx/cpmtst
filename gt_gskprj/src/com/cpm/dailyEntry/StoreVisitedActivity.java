package com.cpm.dailyEntry;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.gt_gsk.R;

import com.cpm.web.ShowUpdateActivity;

public class StoreVisitedActivity extends Activity {

	PepsicoDatabase database;
	private int _mid;
	String store_id, visit_date;
	SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.store_visited);

		RadioButton yes = (RadioButton) findViewById(R.id.yes);
		RadioButton no = (RadioButton) findViewById(R.id.no);

		database = new PepsicoDatabase(this);
		database.open();

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");
		visit_date = preferences.getString(CommonString.KEY_DATE, "");

		if (getMid() != 0) {
			yes.setChecked(true);
		}
	}

	public void onButtonClick(View v) {
		switch (v.getId()) {
		case R.id.yes:

			

			Intent i = new Intent(this, DailyentryMenuActivity.class);
			startActivity(i);
			StoreVisitedActivity.this.finish();
			

			break;

		case R.id.no:

			if (getMid() != 0) {

				AlertDialog.Builder builder = new AlertDialog.Builder(
						StoreVisitedActivity.this);
				builder.setMessage("Your all data will be deleted.")
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {

										UpdateData();

									}
								})
						.setNegativeButton("Back",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
										Intent i = new Intent(
												StoreVisitedActivity.this,
												StorelistActivity.class);
										startActivity(i);
										StoreVisitedActivity.this.finish();
									}
								});
				AlertDialog alert = builder.create();

				alert.show();

			} else {

				Intent in = new Intent(this, NonWorkingActivity.class);
				startActivity(in);
				StoreVisitedActivity.this.finish();

			}

			break;

		}
	}

	public long getMid() {

		int mid = 0;

		mid = database.CheckMid(visit_date, store_id);
		_mid = mid;
		return mid;
	}

	public void UpdateData() {

		database.open();
		database.deleteStoreMidData(_mid, store_id);

		//database.updateStoreStatusOnLeave(store_id, visit_date,
				//CommonString.KEY_N);

		
		Intent intent = new Intent(StoreVisitedActivity.this,
				NonWorkingActivity.class);

		startActivity(intent);

		StoreVisitedActivity.this.finish();

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		Intent i = new Intent(this, StorelistActivity.class);
		startActivity(i);
		StoreVisitedActivity.this.finish();
	}

}
