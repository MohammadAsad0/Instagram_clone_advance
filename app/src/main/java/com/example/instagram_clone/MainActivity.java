package com.example.instagram_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagram_clone.R;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener {

    EditText username,password;
    Button button;
    TextView noAccount;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("aE870sosme8zhfgGkCh8MhV8mmHpuhOc0Slk16Zr")
                .clientKey("OvZtCuQiPSSqHoCMuij7j1HWf0OuiE7gWyiRDX7D")
                .server("https://parseapi.back4app.com")
                .build()
        );

        username=findViewById(R.id.usernameEditText);
        password=findViewById(R.id.passwordEditText);
        button=findViewById(R.id.loginButton);
        noAccount=findViewById(R.id.noAccountTextView);
        intent=new Intent(getApplicationContext(),FeedActivity.class);
        button.setTag(0);


        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.getTag().equals(0)) {
                    button.setTag(1);
                    button.setText("SignUp");
                    noAccount.setText("Already have an account? LogIn");
                } else {
                    button.setTag(0);
                    button.setText("LogIn");
                    noAccount.setText("Don't have an Account? SignUp");
                }
            }
        });


        if (ParseUser.getCurrentUser()!=null) {
            startActivity(intent);
        }

        password.setOnKeyListener(this);



    }

    public void onLoginButton(View view) {
        if(view.getTag().equals(0)) {
            ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Username or Password Incorrect!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            ParseUser user=new ParseUser();
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e==null) {
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            });
        }


    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN) {
            onLoginButton(v);
            return true;
        }
        return false;
    }


}