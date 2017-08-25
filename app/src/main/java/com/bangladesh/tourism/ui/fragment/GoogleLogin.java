package com.bangladesh.tourism.ui.fragment;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by hhson on 4/5/2016.
 */
public class GoogleLogin {


    private static final String SCOPE = "oauth2:https://www.googleapis.com/auth/userinfo.profile";
    String textName, textEmail, textGender, userImageUrl;
    Context mContext ;
    AccountManager mAccountManager;
    String token;
    int serverCode;

    public GoogleLogin(Context context)
    {
        mContext=context;
    }


    private String[] getAccountNames() {
        mAccountManager = AccountManager.get(mContext);
        Account[] accounts = mAccountManager.getAccountsByType(GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
        String[] names = new String[accounts.length];
        for(int i = 0; i < names.length; i++) {
            names[i] = accounts[i].name;
        }
        return names;
    }



    private AbstractGetNameTask getTask(LogInActivity activity, String email, String scope) {
        return new GetNameInForeground(activity, email, scope);
    }
    public void syncGoogleAccount() {
        if(isNetworkAvailable() == true) {
            String[] accountarrs = getAccountNames();
            if(accountarrs.length > 0) {
                getTask((LogInActivity)mContext, accountarrs[0], SCOPE).execute();

            } else {
                Toast.makeText(mContext, "No Google Account Sync!",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(mContext, "No Network Service!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
            Log.e("Network Testing", "Available");
            return true;
        }

        Log.e("Network Testing", "Not Available");
        return false;
    }





    void saveData()
    {
        try {
            JSONObject profileData = new JSONObject(AbstractGetNameTask.GOOGLE_USER_DATA);

            if(profileData.has("picture")) {
                userImageUrl = profileData.getString("picture");
                new GetImageFromUrl().execute(userImageUrl);
            }
            if(profileData.has("name")) {
                textName = profileData.getString("name");

            }
            if(profileData.has("gender")) {
                textGender = profileData.getString("gender");

            }

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            String name = preferences.getString("Name", "No");
            String gender = preferences.getString("Gender", "No");
            String photoUrl = preferences.getString("Url", "No");


            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Name", textName);
            editor.putString("Gender", textGender);
            editor.putString("Url", userImageUrl);
            editor.commit();
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public class GetImageFromUrl extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {

            Bitmap map = null;
            for(String url : urls) {
                map = downloadImage(url);
            }

            return map;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //imageProfile.setImageBitmap(bitmap);
            //send the image in database
        }

        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        private InputStream getHttpConnection(String urlString) throws IOException{
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpURLConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return stream;
        }
    }
}
