package com.feed.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oanastefanescu.agendaapp.R;
import com.feed.entities.User;
import com.feed.entities.UserConnect;

/**
 * UsersAdapter is adapter used for holding information of each user
 */
public class InfoUserAdapter extends ArrayAdapter<UserConnect> implements Filterable {

	public InfoUserAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}

	private ArrayList<UserConnect> connectionsList;
	private Activity activity;

	public InfoUserAdapter(Activity activity, int textViewResourceId,
						ArrayList<UserConnect> usersList) {
		super(activity, textViewResourceId, usersList);
		this.connectionsList = usersList;
		this.activity = activity;
	}

	/**
	 * ViewHolder for list item that holds title and year in customTextViews
	 * objects
	 * 
	 * @author oana
	 * 
	 */
	 final private static class ViewHolder {
		private TextView txtType;
		private TextView txtValue;
		private ImageView imgPhone;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		ViewHolder holder = null;

		// check for reusing the view
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.user_details_item, null);
			holder = new ViewHolder();
			holder.txtType = (TextView) v
					.findViewById(R.id.txt_type);
			holder.txtValue = (TextView) v
					.findViewById(R.id.txt_value);
			holder.imgPhone = (ImageView) v
					.findViewById(R.id.img_phone);

			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}

		final UserConnect connection = getItem(position);
		if (connection != null) {
			holder.txtType.setText(connection.getType());
			holder.txtValue.setText(connection.getValue());
		}

		return v;
	}

	public ArrayList<UserConnect> getList() {
		return this.connectionsList;
	}
}