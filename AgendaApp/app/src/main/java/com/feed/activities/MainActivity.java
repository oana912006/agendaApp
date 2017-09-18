package com.feed.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.oanastefanescu.agendaapp.R;
import com.feed.adapters.UsersAdapter;
import com.feed.entities.User;
import com.feed.services.Callback;
import com.feed.services.DownloadData;
import com.feed.services.ServiceManager;
import com.feed.services.UrlBuilder.ServiceMethod;
import com.feed.utils.FeedConstants;
import com.feed.views.CustomTitleApp;

/**
 * Main Activity that holds the list of users.
 *
 * @author oana
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Activity activity;
    private ArrayList<User> usersList;
    private ListView lwUsers;
    private CustomTitleApp txtErrorNetwork;
    private ServiceManager serviceGetUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_all_users);

        serviceGetUsers = new ServiceManager(this);
        usersList = new ArrayList<User>();
        activity = this;

        setUI();
        getAllUsers();
        setActions();
    }

    private void setUI() {
        lwUsers = (ListView) findViewById(R.id.lw_movies);
        txtErrorNetwork = (CustomTitleApp) findViewById(R.id.txt_error_network);
    }

    private void actionErrorCase(String errorMessage) {
        if (errorMessage.equals(DownloadData.error100)) {
            txtErrorNetwork.setVisibility(View.VISIBLE);
            txtErrorNetwork.setText(errorMessage);
        }
    }

    private void addNewUser(JSONObject jsonObject) {
        User user = new User();

        try {
            user.setGender(jsonObject.getString("gender"));
            user.setNat(jsonObject.getString("nat"));
            user.setCell(jsonObject.getString("cell"));
            user.setPhone(jsonObject.getString("phone"));
            user.setRegistered(jsonObject.getString("registered"));
            user.setDob(jsonObject.getString("dob"));
            user.setEmail(jsonObject.getString("email"));

            JSONObject userName = jsonObject.getJSONObject("name");
            String title = userName.getString("title");
            String first = userName.getString("first");
            String last = userName.getString("last");

            user.setName(title, first, last);

            JSONObject userLocation = jsonObject.getJSONObject("location");
            String street = userLocation.getString("street");
            String city = userLocation.getString("city");
            String postcode = userLocation.getString("postcode");
            String state = userLocation.getString("state");

            user.setLocation(street, city, state, postcode);

            JSONObject userLogin = jsonObject.getJSONObject("login");
            String username = userLogin.getString("username");
            String password = userLogin.getString("password");
            String salt = userLogin.getString("salt");
            String md5 = userLogin.getString("md5");
            String sha1 = userLogin.getString("sha1");
            String sha256 = userLogin.getString("sha256");

            user.setLogin(username,password,salt,md5,sha1,sha256);

            JSONObject userId = jsonObject.getJSONObject("id");
            String name = userId.getString("name");
            String value = userId.getString("value");
            user.setId(name,value);

            JSONObject userPicture = jsonObject.getJSONObject("picture");
            String large = userPicture.getString("large");
            String medium = userPicture.getString("medium");
            String thumbnail = userPicture.getString("thumbnail");
            user.setPicture(large,medium,thumbnail);

            usersList.add(user);

        } catch (JSONException e) {
            //handle jsonException
            Log.e(FeedConstants.DEBUG_TAG, e.getMessage());
        }
    }

    private void createAdapterList() {
        UsersAdapter usersAdapter = new UsersAdapter(activity, 0, usersList);
        lwUsers.setItemsCanFocus(false);
        lwUsers.setAdapter(usersAdapter);
    }

    /**
     * Call service to get a json feed with all users. From this feed will
     * be extracted information about each user
     */
    private void getAllUsers() {
        Object[] paramAllUsers = {};

        serviceGetUsers.call(ServiceMethod.GetAllUsers,
                FeedConstants.SERVICE_GET_ALL_USERS, paramAllUsers,
                new Callback() {

                    public void onError(Exception e) {

                        // user should be alerted that there is no network
                        // connection and data can't be reached
                        actionErrorCase(e.getMessage());
                    }

                    public <T> void finished(T data) {
                        // get jsonObject response
                        JSONArray jsonArray = serviceGetUsers.getResult();
                        JSONArray jsonArrayUsers = null;
                        try {
                            jsonArrayUsers = (JSONArray) jsonArray.getJSONObject(0).get("results");
                            for (int i = 0; i < jsonArrayUsers.length(); i++) {
                                addNewUser((JSONObject) jsonArrayUsers.get(i));
                            }
                            createAdapterList();
                        } catch (JSONException e) {
                            //handle jsonException
                            Log.e(FeedConstants.DEBUG_TAG, e.getMessage());
                        }
                    }
                });
    }

    /**
     * Set action on each list item. New Activity with details about the
     * corresponding user will be opened.
     */
    private void setActions() {
        lwUsers.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                    long arg3) {
                ArrayList<User> usersList = ((UsersAdapter) lwUsers
                        .getAdapter()).getAllUsers();
                Intent intent = new Intent(activity, ActivityUserDetails.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
