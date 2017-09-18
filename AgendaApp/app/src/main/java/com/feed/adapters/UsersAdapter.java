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
import com.feed.views.CustomDetails;

/**
 * UsersAdapter is adapter used for holding information of each user
 */
public class UsersAdapter extends ArrayAdapter<User> implements Filterable {

	public UsersAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}

	private ArrayList<User> usersList;
	private Activity activity;

	public UsersAdapter(Activity activity, int textViewResourceId,
						ArrayList<User> usersList) {
		super(activity, textViewResourceId, usersList);
		this.usersList = usersList;
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
		private TextView txtNameFirst;
		private TextView txtNameLast;
		private TextView txtDescription;
		private ImageView imgAvatar;
		private ImageView imgEdit;
		private ImageView imgStar;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		ViewHolder holder = null;

		// check for reusing the view
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.user_item, null);
			holder = new ViewHolder();
			holder.txtNameFirst = (TextView) v
					.findViewById(R.id.txt_name_first);
			holder.txtNameLast = (TextView) v
					.findViewById(R.id.txt_name_last);
			holder.txtDescription = (TextView) v
					.findViewById(R.id.txt_desc);
			holder.imgAvatar = (ImageView) v
					.findViewById(R.id.img_avatar);
			holder.imgEdit = (ImageView) v
					.findViewById(R.id.icon_edit);
			holder.imgStar = (ImageView) v
					.findViewById(R.id.icon_star);

			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}

		final User user = getItem(position);
		if (user != null) {
			holder.txtNameFirst.setText(((com.feed.entities.User.Name)user.getName()).getFirst());
			holder.txtNameLast.setText(((com.feed.entities.User.Name)user.getName()).getLast());
		}

		return v;
	}

	/**
	 * Get AllUsers
	 * @return - users list
	 */
	public ArrayList<User> getAllUsers() {
		return this.usersList;
	}
}