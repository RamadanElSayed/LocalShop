package com.example.ramadanel_sayed.localshop;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ramadanel_sayed.localshop.MyClasses.ProfileData;
import com.hbb20.CountryCodePicker;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    Button button;
    ImageButton dateFromCal;
    EditText userFirstName;
    EditText userSecondName;
    EditText userEmail;
    EditText userPassword;
    EditText userconfirmPassword;
    EditText NameofUser;
    EditText phoneNumber;
    CountryCodePicker ccp;
    CheckBox check;
    Button submit;
    String First_Name;
    String Last_Name;
    String Email;
    String datebirth;
    String password;
    String confirmPssword;
    String  userName;
    String  CountryName;
    String  CodeOfCountry;
    String phone;
    List<ProfileData>list;
    ProfileData profileData;
    boolean isLoggedIn;
    boolean isLoggedIntwo;
    String phoneWithCode;
    ProgressDialog progressDialog;

    EditText datewhichselected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        progressDialog=new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("SignUp..");
        progressDialog.setMessage("Uploading Your Data.....Plz wait...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        button=(Button)findViewById(R.id.terms);
        dateFromCal=(ImageButton)findViewById(R.id.datefromcal);
        userFirstName=(EditText)findViewById(R.id.editTextFirst_name);
        userSecondName=(EditText)findViewById(R.id.editText2Last_name);
        userEmail=(EditText)findViewById(R.id.editText3Email);
        userPassword=(EditText)findViewById(R.id.editText4Password);
        userconfirmPassword=(EditText)findViewById(R.id.editText5RepeatPassword);
        NameofUser=(EditText)findViewById(R.id.editTextuserName);
        phoneNumber=(EditText)findViewById(R.id.phone);
        ccp=(CountryCodePicker)findViewById(R.id.ccp);


        check=(CheckBox)findViewById(R.id.check);
        submit =(Button)findViewById(R.id.submittoServer);

        datewhichselected=(EditText)findViewById(R.id.editTextdateBirth);

        dateFromCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final java.util.Calendar c= java.util.Calendar.getInstance();
                int year=c.get(java.util.Calendar.YEAR);
                int month=c.get(java.util.Calendar.MONTH);
                int day=c.get(java.util.Calendar.DAY_OF_MONTH);
                DatePickerDialog dp=new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        //    Toast.makeText(MainActivity.this, year+ "/"+(month+1)+"/"+day, Toast.LENGTH_SHORT).show();
                        // java.util.Calendar dateofdaynow= java.util.Calendar.getInstance();
                        // java.text.DateFormat ddd=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ssZZZZZ", Locale.ENGLISH) ;
                        //String date2=ddd.format(new Date())  ;

                        datewhichselected.setText(day+ "/"+(month+1)+"/"+year);

                    }
                },year,month,day);
                dp.show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUpActivity.this,ActivityTerms.class);

                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                First_Name = userFirstName.getText().toString();
                Last_Name = userSecondName.getText().toString();
                Email = userEmail.getText().toString();
                datebirth = datewhichselected.getText().toString();
                password = userPassword.getText().toString();
                confirmPssword = userconfirmPassword.getText().toString();
                userName = NameofUser.getText().toString();
                phone = phoneNumber.getText().toString();
                CountryName = ccp.getSelectedCountryName();
                CodeOfCountry = ccp.getSelectedCountryCodeWithPlus();
                ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
                    @Override
                    public void onCountrySelected() {
                        CountryName = ccp.getSelectedCountryName();
                        CodeOfCountry = ccp.getSelectedCountryCodeWithPlus();
                    }
                });
                if ((First_Name.equals("")) || (Last_Name.equals("")) || (Email.equals("")) || (datebirth.equals("")) ||
                        (password.equals("")) || confirmPssword.equals("") || phone.equals("") || userName.equals("")) {

                    Toast toast = Toast.makeText(SignUpActivity.this, "Please fill all the fields", Toast.LENGTH_LONG);//or LENGTH_short
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG); // or LENGTH_short
                    //  toast.setText("ya gmalhooooo");
                    toast.show();
                } else if (!(password.equals(confirmPssword))) {
                    Toast toast = Toast.makeText(SignUpActivity.this, "Password and confirm password is not match", Toast.LENGTH_LONG);//or LENGTH_short
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG); // or LENGTH_short
                    //  toast.setText("ya gmalhooooo");
                    toast.show();
                } else if (!(check.isChecked())) {


                    Toast toast = Toast.makeText(SignUpActivity.this, "Please accept the terms ", Toast.LENGTH_LONG);//or LENGTH_short
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG); // or LENGTH_short
                    //  toast.setText("ya gmalhooooo");
                    toast.show();
                }
                else
                {

                    isLoggedIn = true;
                    isLoggedIntwo=true;
                    profileData=new ProfileData();
                 list=   profileData.getAllCustomers();
                    for(ProfileData profile:list)
                    {
                      //  if(profile.)

                        if (profile.UserName.equalsIgnoreCase(userName))
                        {

                            isLoggedIn =false;

                        }
                        else if(profile.Email.equalsIgnoreCase(Email))
                        {

                            isLoggedIntwo =false;

                        }
                    }
                    if((isLoggedIn==false)&&(isLoggedIntwo==true))
                    {

                        Toast toast=  Toast.makeText(SignUpActivity.this, "This Name is already used ", Toast.LENGTH_LONG);//or LENGTH_short
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.setDuration(Toast.LENGTH_LONG); // or LENGTH_short
                        //  toast.setText("ya gmalhooooo");
                        toast.show();
                    }
                    else if((isLoggedIn==true)&&(isLoggedIntwo==false))
                    {
                        Toast toast=  Toast.makeText(SignUpActivity.this, "This Email is already used ", Toast.LENGTH_LONG);//or LENGTH_short
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.setDuration(Toast.LENGTH_LONG); // or LENGTH_short
                        //  toast.setText("ya gmalhooooo");
                        toast.show();
                    }

                    if((isLoggedIn==true)&&(isLoggedIntwo==true))
                    {
                        phoneWithCode=CodeOfCountry+":"+phone;

                        profileData.FirstName=First_Name;
                        profileData.LastName=Last_Name;
                        profileData.Email=Email;
                        profileData.UserName=userName;
                        profileData.BirthDate=datebirth;
                        profileData.CountryName=CountryName;
                        profileData.PhoneNumber=phoneWithCode;
                        profileData.Password=password;
                        profileData.save();


                        Intent i= new Intent(SignUpActivity.this,MainApp.class);
                        i.putExtra("userNam",userName);
                        startActivity(i);
                        finish();

                    }
                }

            }
            });


    }
}
