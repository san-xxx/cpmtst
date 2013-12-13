package com.cpm.dailyEntry;

import java.util.ArrayList;
import java.util.Calendar;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.SkuStoreBean;
import com.cpm.gt_gsk.R;

public class SkuDisplay extends Activity implements OnClickListener {

	// static GSKDatabase mDataClassObj;

	private Button savebtn;

	public static final int Info_SELECT = 1;
	public static final int POPUP_SELECT = 2;
	private String store_id, username, intime, brand_id, visit_date;

	String _storeId;

	String _storeName;

	String _storeAddress;

	String _storeCity;
	String store_name;

	String _UserId;

	String _Currentdate;

	String _intime;

	// private DateTime datetimeObj;

	private int _mid;
	boolean check = false;
	Long ChkMidValue;
	static ListView l1;
	String serverdate = "";
	private PepsicoDatabase database;
	private SharedPreferences preferences;
	private String date;
	public static ArrayList<SkuStoreBean> stockdata = new ArrayList<SkuStoreBean>();

	// ArrayList<StockAvailability> stockdataupload = new
	// ArrayList<StockAvailability>();

	// ArrayList<CategoryModel> categorydata = new ArrayList<CategoryModel>();

	public static int counter = 0;

	private static class EfficientAdapter extends BaseAdapter {
		private LayoutInflater mInflater;

		private Context mcontext;

		public EfficientAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
			mcontext = context;
		}

		public int getCount() {
			return stockdata.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ViewHolder holder;

			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.sku_row, null);
				holder = new ViewHolder();

				holder.text = (TextView) convertView
						.findViewById(R.id.mainpage_rememberme);
				holder.stockQuantity = (EditText) convertView
						.findViewById(R.id.editText2);
				holder.faceup = (EditText) convertView
						.findViewById(R.id.editText1);

				// holder.faceup = (EditText) convertView
				// .findViewById(R.id.faceup);

				convertView.setTag(holder);
			} else 
			{
				holder = (ViewHolder) convertView.getTag();
			}

			holder.text.setText(stockdata.get(position).getSku());
			holder.stockQuantity.setText(stockdata.get(position)
					.getStock());
			holder.faceup.setText(stockdata.get(position)
					.getFaceup());

			if (position == 0) {

				// holder.text1.setText(stockdata.get(position).getSku());

			} else {

				// if (stockdata.get(position - 1).getBrand()
				// .equalsIgnoreCase(stockdata.get(position).getBrand())) {
				// holder.text.setText("");
				// holder.text1.setText(stockdata.get(position).getSku());

			}
			/*
			 * else {
			 * 
			 * 
			 * //holder.text.setText(stockdata.get(position).getBrand());
			 * //holder.text1.setText(stockdata.get(position).getSku()); }
			 */

			// holder.stock.setText(stockdata.get(position).getItemName());
			// holder.faceup.setText(stockdata.get(position).getFaceup());

			holder.stockQuantity
					.setOnFocusChangeListener(new OnFocusChangeListener() {
						public void onFocusChange(View v, boolean hasFocus) {

							if (!hasFocus) {
								final int position = v.getId();
								final EditText Caption = (EditText) v;
								String value1 = Caption.getText().toString();
								Toast.makeText(mcontext, "got it", 2000).show();
								if (value1.equals("")) {

									stockdata.get(position).setStock("");

								} else {

									stockdata.get(position)
											.setStock(value1);
									
									
									
								}

							}
						}
					});

			holder.faceup.setOnFocusChangeListener(new OnFocusChangeListener() {
				public void onFocusChange(View v, boolean hasFocus) {

					if (!hasFocus) {
						final int position = v.getId();
						final EditText Caption = (EditText) v;
						String value1 = Caption.getText().toString();
						Toast.makeText(mcontext, "got it", 2000).show();
						if (value1.equals("")) {
					
							stockdata.get(position).setFaceup("");

						} else {

							stockdata.get(position)
									.setFaceup(value1);
							
							
							
						}

					}
				}
			});

			holder.text.setId(position);
			holder.faceup.setId(position);
			holder.stockQuantity.setId(position);
			return convertView;
		}

		static class ViewHolder {

			TextView text;

			EditText stockQuantity, faceup;

		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sku_layout);

		database = new PepsicoDatabase(this);
		database.open();

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		visit_date = preferences.getString(CommonString.KEY_DATE, null);
		store_id = preferences.getString(CommonString.KEY_STORE_ID, null);
		store_name = preferences.getString(CommonString.KEY_STORE_NAME, "");
		TextView text1 = (TextView) findViewById(R.id.mainpage_remembermetext);
		text1.setText(store_name);

		ChkMidValue = checkMid();

		if (ChkMidValue > 0) {
			
			check=true;
			stockdata = database.ViewInsertedSkuStock(ChkMidValue);
			

		} else {

			stockdata = database.getSkuData();
			
		}

		savebtn = (Button) findViewById(R.id.button1);

		l1 = (ListView) findViewById(R.id.list1);
		l1.setAdapter(new EfficientAdapter(this));
		// l1.getAdapter().notifyAll();

		savebtn.setOnClickListener(this);

	}

	public String getCurrentTime() {

		Calendar m_cal = Calendar.getInstance();

		String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);

		return intime;

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

	public long checkMid() {

		long mid = 0;

		mid = database.CheckMid(visit_date, store_id);

		return mid;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.button1) {

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Are you sure you want to save")
					.setCancelable(false)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

									database.open();
									if(check)
									{
										database.UpdateSkuData(getMid(),
												store_id, stockdata);
									}
									else{
									database.InsertAssetData(getMid(),
											store_id, stockdata);
									}
									
									Intent DailyEntryMenu= new Intent(SkuDisplay.this,DailyentryMenuActivity.class);
									startActivity(DailyEntryMenu);
									
									

								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			AlertDialog alert = builder.create();

			alert.show();

		}

	}
	
		@Override
		public void onBackPressed() {
			// TODO Auto-generated method stub
			super.onBackPressed();
			
			Intent intent = new Intent(this, DailyentryMenuActivity.class);
			startActivity(intent);
			SkuDisplay.this.finish();
			
			
		}
	

}
