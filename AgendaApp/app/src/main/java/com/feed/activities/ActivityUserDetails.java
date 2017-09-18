package com.feed.activities;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oanastefanescu.agendaapp.R;
import com.feed.adapters.InfoUserAdapter;
import com.feed.adapters.RoundedCornersTransform;
import com.feed.adapters.UsersAdapter;
import com.feed.entities.User;
import com.feed.entities.UserConnect;
import com.feed.utils.FeedConstants;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Activity that holds details about user
 *
 * @author oana
 */
public class ActivityUserDetails extends Activity {
    private ArrayList<UserConnect> listConnections;
    private User user;
    private ImageView imgAvatar;
    private ImageView imgStar;
    private ImageView imgEdit;
    private ImageView imgMenu;
    private TextView txtFirstName;
    private TextView txtLastName;
    private ListView listView;
    private InfoUserAdapter adapter;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_details);

        user = null;
        if (getIntent().getSerializableExtra("user") != null) {
            user = (User) getIntent().getSerializableExtra("user");
        }
        activity = this;
        setUI();
        updateUI();
        setList();
        setActions();
    }

    private void setActions() {
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "edit was pressed", Toast.LENGTH_LONG).show();
            }
        });
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "menu btn was pressed", Toast.LENGTH_LONG).show();
            }
        });
        imgStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "rate btn was pressed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setList() {
        listConnections = new ArrayList<>();
        listConnections.add(new UserConnect(FeedConstants.PHONE, user.getPhone()));
        listConnections.add(new UserConnect(FeedConstants.EMAIL, user.getEmail()));

        com.feed.entities.User.Location location = (com.feed.entities.User.Location) user.getLocation();
        String address = location.getCity() + ", " + location.getStreet() + ", " + location.getPostcode();
        listConnections.add(new UserConnect(FeedConstants.ADDRESS, address));

        adapter = new InfoUserAdapter(this, 0, listConnections);
        listView.setItemsCanFocus(false);
        listView.setAdapter(adapter);
    }

    public void setUI() {
        listView = (ListView) findViewById(R.id.list_view_details);
        imgAvatar = (ImageView) findViewById(R.id.img_avatar);
        imgEdit = (ImageView) findViewById(R.id.img_edit);
        imgStar = (ImageView) findViewById(R.id.img_star);
        imgMenu = (ImageView) findViewById(R.id.img_menu);
        txtFirstName = (TextView) findViewById(R.id.txt_name_first);
        txtLastName = (TextView) findViewById(R.id.txt_name_last);
    }

    private void updateUI() {
        txtLastName.setText(((com.feed.entities.User.Name) user.getName()).getLast());
        txtFirstName.setText(((com.feed.entities.User.Name) user.getName()).getFirst());

        Picasso.with(this)
                .load(((com.feed.entities.User.Picture) user.getPicture()).getThumbnail())
                .placeholder(R.drawable.ic_launcher)
                .transform(new RoundedCornersTransform())
                .into(imgAvatar);
    }


}