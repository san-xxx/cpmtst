package com.cpm.dailyEntry;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.StoreBean;
import com.cpm.message.AlertMessage;
import com.cpm.gt_gsk.R;

public class StorelistActivity extends ListActivity implements OnClickListener {

	private Intent intent;
	private Cursor cursor;
	private PepsicoDatabase database;
	private SharedPreferences preferences;
	private String date;
	private ArrayList<StoreBean> list = new ArrayList<StoreBean>();
	private SharedPreferences.Editor editor = null;
	boolean leave;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.storename);

		database = new PepsicoDatabase(this);
		database.open();

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		editor = preferences.edit();
		date = preferences.getString(CommonString.KEY_DATE, null);

		list = database.getStoreData(date);

		setListAdapter(new MyAdapter());

	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();

				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.storeviewlist, null);

				holder.storename = (TextView) convertView
						.findViewById(R.id.storelistviewxml_storename);
				holder.storeaddress = (TextView) convertView
						.findViewById(R.id.storelistviewxml_storeaddress);

				holder.imgtick = (ImageView) convertView
						.findViewById(R.id.storelistviewxml_storeico);

				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			if (list.get(position).getStatus().equals(CommonString.KEY_U)) {

				holder.imgtick.setBackgroundResource(R.drawable.tick_u);

				holder.imgtick.setVisibility(View.VISIBLE);
			} else if ((list.get(position).getStatus()
					.equals(CommonString.KEY_D))) {
				holder.imgtick.setVisibility(View.VISIBLE);
				holder.imgtick.setBackgroundResource(R.drawable.tick_d);

			} else if ((list.get(position).getStatus()
					.equals(CommonString.KEY_P))) {

				holder.imgtick.setVisibility(View.VISIBLE);
				holder.imgtick.setBackgroundResource(R.drawable.tick_p);
			}

			else if ((list.get(position).getStatus()
					.equals(CommonString.STORE_STATUS_LEAVE))) {

				holder.imgtick.setVisibility(View.VISIBLE);
				holder.imgtick.setBackgroundResource(R.drawable.tickl);
			} else if (validateCoverage(list.get(position).getStoreid())) {
				holder.imgtick.setVisibility(View.VISIBLE);

				if (leave)

					holder.imgtick.setBackgroundResource(R.drawable.tickl);
				else
					holder.imgtick.setBackgroundResource(R.drawable.tickgreenv);
			}

			else {

				holder.imgtick.setVisibility(View.VISIBLE);
				holder.imgtick.setBackgroundResource(R.drawable.storeico);

			}

			holder.storename.setText(list.get(position).getStorename());
			holder.storeaddress.setText(list.get(position).getStoreaddress());

			return convertView;
		}

		private class ViewHolder {
			TextView storename, storeaddress;
			ImageView imgtick;
		}

		public boolean validateCoverage(String storeid) {
			boolean result = false;

			if (database.CheckMid(date, storeid) > 0) {
				result = true;

				if (database.CheckMidWithStatus(date, storeid)
						.equalsIgnoreCase(CommonString.STORE_STATUS_LEAVE)) {
					leave = true;
					database.updateStoreStatusOnLeave(storeid, date,
							CommonString.STORE_STATUS_LEAVE);
				} else {
					leave = false;
				}

			}

			return result;

		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		/*
		 * String result = database.Checkstat(list.get(position).getStoreid());
		 * if (result.equals("L")) { Toast.makeText(getApplicationContext(),
		 * "Marked as Not Visited", Toast.LENGTH_SHORT).show(); } else
		 * 
		 * { intent = new Intent(getBaseContext(), StoreVisitedActivity.class);
		 * 
		 * editor.putString(CommonString.KEY_STORE_ID, list.get(position)
		 * .getStoreid()); editor.commit(); startActivity(intent);
		 * this.finish(); }
		 */

		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		list = database.getStoreData(date);
		StoreBean sb = list.get(position);

		if ((sb.getStatus().equals(CommonString.KEY_U))) {

			showToast(AlertMessage.MESSAGE_UPLOAD);

		} else if ((sb.getStatus().equals(CommonString.KEY_D))) {

			showToast(AlertMessage.MESSAGE_DATA_UPLOAD);

		} else if (((sb.getStatus().equals(CommonString.STORE_STATUS_LEAVE)))) {

			showToast(AlertMessage.MESSAGE_LEAVE);
		}

		else {

			editor = preferences.edit();
			editor.putString(CommonString.KEY_STORE_ID, sb.getStoreid());
			editor.putString(CommonString.KEY_STORE_NAME, sb.getStorename());
			editor.putString(CommonString.KEY_VISIT_DATE, sb.getVisitdate());

			editor.commit();

			intent = new Intent(getBaseContext(), StoreVisitedActivity.class);

			startActivity(intent);
			this.finish();

		}
	}

	public void onBackClick(View v) {

		Intent i = new Intent(this, com.cpm.gt_gsk.MainMenuActivity.class);
		startActivity(i);
		StorelistActivity.this.finish();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent i = new Intent(this, com.cpm.gt_gsk.MainMenuActivity.class);
		startActivity(i);
		StorelistActivity.this.finish();
	}

	private void showToast(String message) {
		Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub

	}

}
