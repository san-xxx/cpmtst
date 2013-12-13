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
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.ComplianceBean;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.PosmBean;
import com.cpm.gt_gsk.R;

public class PosmDeployed extends Activity implements OnClickListener{

	public String store_name;
	public ListView lv;
	private String store_id, username, intime, visit_date;
	private SharedPreferences preferences;
	private PepsicoDatabase database;
	public static String presence[];
	public static String remarks[];
	public static int pos[], camera[], image1[], image2[], image3[];
	private static ArrayList<PosmBean> posm_list = new ArrayList<PosmBean>();
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
		setContentView(R.layout.posm_deployed);

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

		posm_list = database.getPosmData();

		posm_list = database.getInsertedPosmData(store_id);

		if (posm_list.size() == 0) {
			posm_list = database.getPosmData();

			for (int i2 = 0; i2 < posm_list.size(); i2++) {

				posm_list.get(i2).setCamera("NO");
				posm_list.get(i2).setImage("");
				
				
			

			}
		}

		else {

			update = true;
			save.setText("Update");

			for (int i2 = 0; i2 < posm_list.size(); i2++) {

				if (!posm_list.get(i2).getImage().equalsIgnoreCase("")) {

					posm_list.get(i2).setCamera("YES");
					posm_list.get(i2).setImage(posm_list.get(i2).getImage());
				} else {
					posm_list.get(i2).setCamera("NO");
					posm_list.get(i2).setImage("");
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
			return posm_list.size();
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
				convertView = mInflater.inflate(R.layout.posmlistview, null);
				holder = new ViewHolder();

				holder.remarks = (TextView) convertView
						.findViewById(R.id.remarks);

				holder.camera = (ImageView) convertView
						.findViewById(R.id.cameraicon);

				holder.quantity = (EditText)convertView
						.findViewById(R.id.quantity);

				convertView.setTag(holder);

			} else {

				holder = (ViewHolder) convertView.getTag();

			}

			holder.remarks.setText(posm_list.get(position).getPosm());
			holder.quantity.setText(posm_list.get(position).getQuantity());
			holder.camera.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mposition = position;
					_pathforcheck = posm_list.get(position).getPosm() + ".jpg";

					_path = str + _pathforcheck;

					startCameraActivity();
				}
			});
			
			holder.quantity.setOnFocusChangeListener(new OnFocusChangeListener() {
				public void onFocusChange(View v, boolean hasFocus) {

					if (!hasFocus) {
						final int position = v.getId();
						final EditText Caption = (EditText) v;
						String value1 = Caption.getText().toString();
					//	Toast.makeText(mcontext, "got it", 2000).show();
						if (value1.equals("")) {
				
							posm_list.get(position).setQuantity("");

						} else {

							posm_list.get(position).setQuantity(value1);		
							
						}

					}
				}
			});
			
			holder.camera
					.setOnLongClickListener(new View.OnLongClickListener() {

						@Override
						public boolean onLongClick(View v) {
							// TODO Auto-generated method stub

							Intent intent = new Intent(PosmDeployed.this,
									ShowImage.class);
							_pathforcheck = posm_list.get(position).getImage();
							intent.putExtra("Image", _pathforcheck);
							startActivity(intent);

							return false;
						}
					});

			if (!img1.equalsIgnoreCase("")) {
				if (position == mposition) {
					posm_list.get(position).setCamera("YES");
					posm_list.get(position).setImage(img1);
					img1 = "";

				}
			}
			if (posm_list.get(position).getCamera().equalsIgnoreCase("NO")) {
				holder.camera.setBackgroundResource(R.drawable.cam);
			} else {
				holder.camera.setBackgroundResource(R.drawable.camtick);
			}

			
			holder.remarks.setId(position);
			holder.camera.setId(position);
			holder.quantity.setId(position);
			
			return convertView;
		}

		class ViewHolder {

			TextView remarks;
			ImageView camera;
			EditText quantity;
		}

	}

	public void onButtonClick(View v) {
		switch (v.getId()) {

		case R.id.save:

			AlertDialog.Builder builder = new AlertDialog.Builder(
					PosmDeployed.this);
			builder.setMessage("Do you want to save the data ")
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

									if (update) {

										database.open();
										database.deletePosmData(store_id);
										database.InsertPosmData(getMid(),
												store_id, posm_list);

										Intent i = new Intent(
												getApplicationContext(),
												DailyentryMenuActivity.class);
										startActivity(i);
										PosmDeployed.this.finish();

									} else {
										database.open();

										database.InsertPosmData(getMid(),
												store_id, posm_list);

										Intent i = new Intent(
												getApplicationContext(),
												DailyentryMenuActivity.class);
										startActivity(i);
										PosmDeployed.this.finish();
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

		PosmDeployed.this.finish();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		database.close();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
