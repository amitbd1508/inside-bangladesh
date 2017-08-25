package com.bangladesh.tourism.ui.fragment;

import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bangladesh.tourism.R;
import com.bangladesh.tourism.ui.activity.MainActivity;


public class LogInActivity extends AppCompatActivity {

    private static final String SCOPE = "oauth2:https://www.googleapis.com/auth/userinfo.profile";
    String textName, textEmail, textGender, userImageUrl;
    Context mContext = LogInActivity.this;
    AccountManager mAccountManager;
    String token;
    int serverCode;
    View loginGimal;
    Button sign_in_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        loginGimal = findViewById(R.id.layoutGmail);

        final GoogleLogin googleLogin=new GoogleLogin(LogInActivity.this);
        loginGimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleLogin.syncGoogleAccount();
                googleLogin.saveData();

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Login", "Yes");

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
