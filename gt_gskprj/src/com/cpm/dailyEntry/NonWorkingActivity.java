package com.cpm.dailyEntry;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.ReasonModel;
import com.cpm.delegates.StoreBean;
import com.cpm.gt_gsk.R;
import com.cpm.xmlGetterSetter.NonWrkingMasterGetterSetter;

public class NonWorkingActivity extends Activity implements
		OnItemSelectedListener, OnClickListener {

	private PepsicoDatabase database;
	String reasonname, reasonid, image, entry, intime;
	Button save;
	private ArrayAdapter<CharSequence> reason_adapter;
	protected String _path;
	private static String Reason[];
	private static String ReasonId[];
	private static String Image[];
	private Spinner reasonspinner;
	private static String Entry[];
	protected String _pathforcheck = "";
	private ArrayList<StoreBean> storedata = new ArrayList<StoreBean>();
	private ArrayList<ReasonModel>reasondata =new ArrayList<ReasonModel>();
	private String image1 = "";
	private ArrayList<CoverageBean> cdata = new ArrayList<CoverageBean>();
	protected boolean _taken;
	protected static final String PHOTO_TAKEN = "photo_taken";
	private SharedPreferences preferences;
	String TempDate = "";
	String _UserId, visit_date, store_id;
	protected boolean status = true;

	AlertDialog alert;
	EditText edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nonworking);
		reasonspinner = (Spinner) findViewById(R.id.spinner2);
		save = (Button) findViewById(R.id.save);
		// text = (TextView) findViewById(R.id.testview4);
		edit = (EditText) findViewById(R.id.edit);

		TempDate = new Date().toLocaleString().toString().replace(' ', '_')
				.replace(',', '_').replace(':', '-');

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		_UserId = preferences.getString(CommonString.KEY_USERNAME, "");
		visit_date = preferences.getString(CommonString.KEY_DATE, null);
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");

		database = new PepsicoDatabase(this);
		database.open();

		cdata = database.getCoverageData(visit_date, null);
		storedata = database.getStoreData(visit_date);
		reasondata = database.GetNonWorkingReason();
		intime = getCurrentTime();

		for (int i = 0; i < storedata.size(); i++) {
			if (storedata.get(i).getStatus()
					.equalsIgnoreCase(CommonString.KEY_U)) {
				status = false;
				break;
			}
		}

		if (cdata.size() == 0 && status) {

			Reason = new String[reasondata.size()];

			ReasonId = new String[reasondata.size()];

			Entry = new String[reasondata.size()];

			Image = new String[reasondata.size()];

			for (int i = 0; i < reasondata.size(); i++) {

				ReasonModel data = new ReasonModel();

				data = reasondata.get(i);

				ReasonId[i] = data.getReasonid();

				Reason[i] = data.getReason();

				Image[i] = data.getImage();

				Entry[i] = data.getEntry();

			}
		} else {
			Reason = new String[reasondata.size() - 2];

			ReasonId = new String[reasondata.size() - 2];
			Entry = new String[reasondata.size() - 2];

			Image = new String[reasondata.size() - 2];

			int j = 0;

			for (int i = 0; i < reasondata.size() - 2; i++) {

				ReasonModel data = new ReasonModel();

				data = reasondata.get(j);

				if (data.getReason().equalsIgnoreCase("Leave (Full day)")
						|| data.getReason().equalsIgnoreCase("Holiday")) {
					j++;
					i--;
				} else {

					ReasonId[i] = data.getReasonid();
					Reason[i] = data.getReason();
					Image[i] = data.getImage();
					Entry[i] = data.getEntry();

					j++;
				}
			}
		}
		save.setOnClickListener(this);
		
		reason_adapter = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_spinner_item);

		reason_adapter.add("Select Reason");
		for (int i = 0; i < Reason.length; i++) {
			reason_adapter.add(Reason[i]);
		}

		reasonspinner.setAdapter(reason_adapter);

		reason_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		reasonspinner.setOnItemSelectedListener(this);

	}
	
	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub

		switch (arg0.getId()) {
		case R.id.spinner2:
			if (position != 0) {
				reasonname = Reason[position - 1];
				reasonid = ReasonId[position - 1];
				//image = Image[position - 1];
				//entry = Entry[position - 1];
				
				if(image.equalsIgnoreCase("true"))
				{
					//text.setVisibility(View.VISIBLE);
					//camera.setVisibility(View.VISIBLE);
				}
				else
				{
					//text.setVisibility(View.INVISIBLE);
				//	camera.setVisibility(View.INVISIBLE);
				}

			} 
			else 
			{
				reasonname = "";
				reasonid = "";
				image = "";
				entry = "";
			}
			break;
		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		Intent i = new Intent(this, StoreVisitedActivity.class);
		startActivity(i);
		NonWorkingActivity.this.finish();
	}

	
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	protected void startCameraActivity() {
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.save) {

			AlertDialog.Builder builder = new AlertDialog.Builder(
					NonWorkingActivity.this);
			builder.setMessage("Do you want to save the data ")
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

									alert.getButton(AlertDialog.BUTTON_POSITIVE)
											.setEnabled(false);

									CoverageBean cdata = new CoverageBean();
									cdata.setStoreId(store_id);
									cdata.setVisitDate(visit_date);
									cdata.setUserId(_UserId);
									cdata.setInTime(intime);
									cdata.setOutTime(getCurrentTime());
									cdata.setReason(reasonname);
									cdata.setReasonid(reasonid);
									cdata.setLatitude("0.0");
									cdata.setLongitude("0.0");
									cdata.setImage(image1);
									cdata.setRemark(edit.getText().toString()
											.replaceAll("[&^<>{}'$]", " "));
									cdata.setStatus(CommonString.STORE_STATUS_LEAVE);
									
									database.InsertCoverageData(cdata);

									//database.updateStoreStatusOnLeave(store_id, visit_date,
										//	CommonString.STORE_STATUS_LEAVE);

									
/*SharedPreferences.Editor editor = preferences
											.edit();
									editor.putString(
											CommonString.KEY_STOREVISITED_STATUS,
											"no");
									editor.commit();*/

									Intent intent = new Intent(
											getApplicationContext(),
											StorelistActivity.class);
									startActivity(intent);
									NonWorkingActivity.this.finish();

								}

							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});

			alert = builder.create();
			alert.show();

		}

	}

	public String getCurrentTime() {

		Calendar m_cal = Calendar.getInstance();

		String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);

		return intime;

	}
}
