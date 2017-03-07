package com.example.ramadanel_sayed.localshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ramadanel_sayed.localshop.MyClasses.ProfileData;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    Button SignUp;
    Button LogIn;
    CheckBox remebermeBtn;
    Button ForgotPassword;

    EditText userNameOrEmail;
    EditText Password;
    String UserNOREmail;
    ProgressDialog progressDialog;
    List<ProfileData> list;
    String UserNameToUseIt;
    ProfileData profileData;
    String UserPassword;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        profileData=new ProfileData();
        progressDialog=new ProgressDialog(LoginActivity.this,R.style.MyTheme);
        progressDialog.setTitle("Connecting..");
        progressDialog.setMessage("LogIn.....Plz wait...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        SignUp = (Button) findViewById(R.id.sign);
        LogIn = (Button) findViewById(R.id.log);
        remebermeBtn=(CheckBox)findViewById(R.id.check);
        ForgotPassword = (Button) findViewById(R.id.forgot);

        userNameOrEmail = (EditText) findViewById(R.id.editText);
        Password = (EditText) findViewById(R.id.editText2);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            userNameOrEmail.setText(loginPreferences.getString("username", ""));
            Password.setText(loginPreferences.getString("password", ""));
            remebermeBtn.setChecked(true);
        }

        remebermeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username=userNameOrEmail.getText().toString();
                String password=Password.getText().toString();
                if (remebermeBtn.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", username);
                    loginPrefsEditor.putString("password", password);
                    loginPrefsEditor.commit();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }
            }
        });



        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });
        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);

            }
        });


        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isLoggedIn = false;

                UserNOREmail = userNameOrEmail.getText().toString();
                UserPassword = Password.getText().toString();
                list=profileData.getAllCustomers();
              //  list.size();
                for (ProfileData pro:list)
                {

                    if (((pro.Email.equalsIgnoreCase(UserNOREmail)) || (pro.UserName.equalsIgnoreCase(UserNOREmail))) && (pro.Password.equalsIgnoreCase(UserPassword))) {

                        UserNameToUseIt=pro.UserName;
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Welcome"+pro.FirstName+" "+pro.LastName, Toast.LENGTH_SHORT).show();
                        isLoggedIn = true;
                        Intent i = new Intent(LoginActivity.this, MainApp.class);
                        i.putExtra("userNam",UserNameToUseIt);
                        startActivity(i);
                        finish();
                    }
                }
                if (isLoggedIn == false) {
                    progressDialog.dismiss();

                    Toast toast = Toast.makeText(LoginActivity.this, "invalid user name or password", Toast.LENGTH_LONG);//or LENGTH_short
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG); // or LENGTH_short
                    //  toast.setText("ya gmalhooooo");
                    toast.show();
                }



            }
        });


    }
}
