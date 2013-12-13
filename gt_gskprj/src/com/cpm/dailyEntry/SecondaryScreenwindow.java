package com.cpm.dailyEntry;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.ComplianceBean;
import com.cpm.delegates.CoverageBean;

import com.cpm.gt_gsk.R;

public class SecondaryScreenwindow extends Activity {

	public String store_name;
	public ListView lv;
	private String store_id, username, intime, visit_date;
	private SharedPreferences preferences;
	private PepsicoDatabase database;
	public static String presence[];
	public static String remarks[];
	public static int pos[], camera[], image1[], image2[], image3[];
	private static ArrayList<ComplianceBean> compliance_list = new ArrayList<ComplianceBean>();
	protected String _path;
	private static File file;
	private static String str, path;
	String img1 = "";
	private static Uri uri;
	public static final int Info_SELECT = 1;
	public static final int POPUP_SELECT = 2;
	static int mposition = -1;
	AlertDialog alert;
	Boolean update = false;
	Button save;

	private static final int CAMERA_PIC_REQUEST = 1;
	protected static String _pathforcheck = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondary_window);

		lv = (ListView) findViewById(R.id.list);
		save = (Button) findViewById(R.id.save);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		intime = getCurrentTime();
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		store_name = preferences.getString(CommonString.KEY_STORE_NAME, "");
		username = preferences.getString(CommonString.KEY_USERNAME, "");
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");

		visit_date = preferences.getString(CommonString.KEY_DATE, "");

		TextView text1 = (TextView) findViewById(R.id.mainpage_remembermetext);
		text1.setText(store_name);

		database = new PepsicoDatabase(this);
		database.open();

		compliance_list = database.getInsertedComplianceData(store_id);

		if (compliance_list.size() == 0) {
			compliance_list = database.getComplianceData();

			for (int i2 = 0; i2 < compliance_list.size(); i2++) {

				compliance_list.get(i2).setCamera("NO");
				compliance_list.get(i2).setImage("");
				compliance_list.get(i2).setAvailability("NO");

			}
		}

		else {

			update = true;
			save.setText("Update");

			for (int i2 = 0; i2 < compliance_list.size(); i2++) {

				if (!compliance_list.get(i2).getImage().equalsIgnoreCase("")) {

					compliance_list.get(i2).setCamera("YES");
					compliance_list.get(i2).setImage(
							compliance_list.get(i2).getImage());
				} else {
					compliance_list.get(i2).setCamera("NO");
					compliance_list.get(i2).setImage("");
				}
				if (compliance_list.get(i2).getAvailability()
						.equalsIgnoreCase("1")) {
					compliance_list.get(i2).setAvailability("YES");

				} else {
					compliance_list.get(i2).setAvailability("NO");
				}

			}

		}

		lv.setAdapter(new MyAdapter(this));

		lv.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

			}

			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {
				// TODO Auto-generated method stub
				lv.invalidateViews();
			}

		});

		str = Environment.getExternalStorageDirectory() + "/GT_GSK_Images/"
				+ store_id + "/";

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i("MakeMachine", "resultCode: " + resultCode);
		switch (resultCode) {
		case 0:
			Log.i("MakeMachine", "User cancelled");
			break;

		case -1:

			if (_pathforcheck != null && !_pathforcheck.equals("")) {
				if (new File(str + _pathforcheck).exists()) {

					img1 = _pathforcheck;
					lv.invalidateViews();
					_pathforcheck = "";
					break;

				}
			}

			break;
		}
	}

	protected void startCameraActivity() {

		try {
			Log.i("MakeMachine", "startCameraActivity()");
			File file = new File(_path);
			Uri outputFileUri = Uri.fromFile(file);

			Intent intent = new Intent(
					android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

			startActivityForResult(intent, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		Context mcontext;

		public MyAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
			mcontext = context;
		}

		public int getCount() {
			return compliance_list.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final ViewHolder holder;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.secondarylistview,
						null);
				holder = new ViewHolder();

				holder.presence = (ToggleButton) convertView
						.findViewById(R.id.toggleButton);

				holder.remarks = (TextView) convertView
						.findViewById(R.id.remarks);
				holder.compliance = (TextView) convertView
						.findViewById(R.id.compliance);

				holder.camera = (ImageView) convertView
						.findViewById(R.id.cameraicon);

				convertView.setTag(holder);

			} else {

				holder = (ViewHolder) convertView.getTag();

			}

			holder.remarks
					.setText(compliance_list.get(position).getPromotion());
			holder.compliance.setText(compliance_list.get(position)
					.getCompliance());
			holder.camera.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mposition = position;
					_pathforcheck = compliance_list.get(position)
							.getCompliance() + ".jpg";

					_path = str + _pathforcheck;

					startCameraActivity();
				}
			});

			holder.camera
					.setOnLongClickListener(new View.OnLongClickListener() {

						@Override
						public boolean onLongClick(View v) {
							// TODO Auto-generated method stub

							Intent intent = new Intent(
									SecondaryScreenwindow.this, ShowImage.class);
							_pathforcheck = compliance_list.get(position)
									.getImage();
							intent.putExtra("Image", _pathforcheck);
							startActivity(intent);
							return false;
						}
					});

			holder.presence.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					// presence[position] = "0";
					String val = holder.presence.getText().toString();
					compliance_list.get(position).setAvailability(val);

				}
			});

			if (!img1.equalsIgnoreCase("")) {
				if (position == mposition) {
					compliance_list.get(position).setCamera("YES");
					compliance_list.get(position).setImage(img1);
					img1 = "";

				}
			}
			if (compliance_list.get(position).getCamera()
					.equalsIgnoreCase("NO")) {
				holder.camera.setBackgroundResource(R.drawable.cam);
			} else {
				holder.camera.setBackgroundResource(R.drawable.camtick);
			}
			if (compliance_list.get(position).getAvailability()
					.equalsIgnoreCase("NO")) {
				holder.presence.setChecked(false);
			} else {
				holder.presence.setChecked(true);
			}

			return convertView;
		}

		class ViewHolder {

			ToggleButton presence;
			TextView remarks, compliance;
			ImageView camera;
		}

	}

	public void onButtonClick(View v) {
		switch (v.getId()) {

		case R.id.save:

			AlertDialog.Builder builder = new AlertDialog.Builder(
					SecondaryScreenwindow.this);
			builder.setMessage("Do you want to save the data ")
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

									if (update) {

										database.open();
										database.deleteComplianceData(store_id);
										database.InsertComplianceData(getMid(),
												store_id, compliance_list);

										Intent i = new Intent(
												getApplicationContext(),
												DailyentryMenuActivity.class);
										startActivity(i);
										SecondaryScreenwindow.this.finish();

									} else {
										database.open();

										database.InsertComplianceData(getMid(),
												store_id, compliance_list);

										Intent i = new Intent(
												getApplicationContext(),
												DailyentryMenuActivity.class);
										startActivity(i);
										SecondaryScreenwindow.this.finish();
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
			AlertDialog alert = builder.create();
			alert.show();

			break;

		}

	}

	public long getMid() {

		long mid = 0;

		mid = database.CheckMid(visit_date, store_id);

		if (mid == 0) {
			CoverageBean cdata = new CoverageBean();
			cdata.setStoreId(store_id);
			cdata.setVisitDate(visit_date);
			cdata.setUserId(username);
			cdata.setInTime(intime);
			cdata.setOutTime(getCurrentTime());
			cdata.setReason("");
			cdata.setReasonid("0");
			cdata.setLatitude("0.0");
			cdata.setLongitude("0.0");
			mid = database.InsertCoverageData(cdata);

		}

		return mid;
	}

	public String getCurrentTime() {

		Calendar m_cal = Calendar.getInstance();

		String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);

		return intime;

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		Intent intent = new Intent(this, DailyentryMenuActivity.class);

		startActivity(intent);

		SecondaryScreenwindow.this.finish();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		database.close();
	}

}
