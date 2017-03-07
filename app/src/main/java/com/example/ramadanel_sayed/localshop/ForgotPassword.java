package com.example.ramadanel_sayed.localshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramadanel_sayed.localshop.MyClasses.ProfileData;
import com.example.ramadanel_sayed.localshop.MyClasses.SendMail;

import java.util.List;
import java.util.Random;

public class ForgotPassword extends AppCompatActivity {

    Button receiveCode;
    Button receivePassword;
    EditText phoneNumber;
    EditText codeNumber;
    String codewhichGenerated;
    String passwordFromFire;
    TextView setPassword;
    Button receiveCodetwo;
    Button receivePasswordtwo;
    EditText EmailAddress;
    EditText codeNumbertwo;
    String codewhichGeneratedtwo;
    String passwordFromFiretwo;
    TextView setPasswordtwo;
    List<ProfileData> list;
    ProfileData profileData;
    List<ProfileData> listtwo;
    ProfileData profileDatatwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        profileData=new ProfileData();
        profileDatatwo=new ProfileData();
        phoneNumber =(EditText)findViewById(R.id.editText6);
        codeNumber=(EditText)findViewById(R.id.editText9);
        receiveCode=(Button)findViewById(R.id.button);
        receivePassword=(Button)findViewById(R.id.buttontwo);
        setPassword=(TextView)findViewById(R.id.textView8);


        receiveCodetwo=(Button)findViewById(R.id.buttonemail);
        receivePasswordtwo=(Button)findViewById(R.id.buttontwopassword);
        EmailAddress=(EditText)findViewById(R.id.editTextemail);
        codeNumbertwo=(EditText)findViewById(R.id.editText9code);
        setPasswordtwo=(TextView)findViewById(R.id.textView8password);

        receiveCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isLoggedIn = false;
                String phoneNumber2=phoneNumber.getText().toString();
                list=profileData.getAllCustomers();
                for (ProfileData pro:list)
                {

                    int numberofchar = pro.PhoneNumber.indexOf(":");
                    String valueFromFire = pro.PhoneNumber.substring(numberofchar + 1);
                    if (valueFromFire.equalsIgnoreCase(phoneNumber2)) {
                        passwordFromFire=pro.Password;
                        isLoggedIn = true;

                    }
                }


                if (isLoggedIn == false) {
                    Toast toast=  Toast.makeText(ForgotPassword.this, "invalid Phone Number", Toast.LENGTH_LONG);//or LENGTH_short
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.setDuration(Toast.LENGTH_LONG); // or LENGTH_short
                    //  toast.setText("ya gmalhooooo");
                    toast.show();
                }

                else if (isLoggedIn==true)
                {

                    Random r = new Random();
                    int Low = 10000;
                    int High = 80000;
                    int Result = r.nextInt(High-Low) + Low;
                    codewhichGenerated = String.valueOf(Result);
                    //   String phoneNumber2=phoneNumber.getText().toString();
//                String  codeNumber2=codeNumber.getText().toString();
                    try {

                        SmsManager smsManager = SmsManager.getDefault();
                        String Message = "Your Code Number is" + codewhichGenerated;
                        smsManager.sendTextMessage(phoneNumber2, null, Message, null, null);
                        Toast.makeText(ForgotPassword.this, "Message sent", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(ForgotPassword.this, "sms Failed", Toast.LENGTH_SHORT).show();
                    }
                }




            }
        });

        receivePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codeN=codeNumber.getText().toString();
                if(codeN.equalsIgnoreCase(codewhichGenerated))
                {

                    setPassword.setText("Your Password is >>"+"\n"+passwordFromFire);
                }
            }
        });



        receiveCodetwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isLoggedIn = false;
                String Emailaddres=EmailAddress.getText().toString();

                listtwo=profileDatatwo.getAllCustomers();
                for (ProfileData pr:listtwo)
                {
                    String valueFromFire = pr.Email;
                    if (valueFromFire.equalsIgnoreCase(Emailaddres)) {
                        passwordFromFiretwo=pr.Password;
                        isLoggedIn = true;

                    }

                }


                if (isLoggedIn == false) {
//                            Toast toast=  Toast.makeText(ForgatPassword.this, "invalid Email Address", Toast.LENGTH_LONG);//or LENGTH_short
//                            toast.setGravity(Gravity.CENTER,0,0);
//                            toast.setDuration(Toast.LENGTH_LONG); // or LENGTH_short
//                            //  toast.setText("ya gmalhooooo");
//                            toast.show();
                    Toast.makeText(ForgotPassword.this, "invalid Email Address", Toast.LENGTH_SHORT).show();
                }

                else if (isLoggedIn==true)
                {

                    Random r = new Random();
                    int Low = 10000;
                    int High = 80000;
                    int Result = r.nextInt(High-Low) + Low;
                    codewhichGeneratedtwo = String.valueOf(Result);
                    //   String phoneNumber2=phoneNumber.getText().toString();
//                String  codeNumber2=codeNumber.getText().toStrin

                    //Creating SendMail object
                    SendMail sm = new SendMail(ForgotPassword.this,Emailaddres, "About Forgotting Password", "please take this code and enter it in text in App Mobile Shop to " +
                            "get your password code is "+codewhichGeneratedtwo);

                    //Executing sendmail to send email
                    sm.execute();



                }

            }
        });
        receivePasswordtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String codeN=codeNumbertwo.getText().toString();
                if(codeN.equalsIgnoreCase(codewhichGeneratedtwo))
                {

                    setPasswordtwo.setText("Your Password is >>"+"\n"+passwordFromFiretwo);
                }
                else
                {
                    Toast.makeText(ForgotPassword.this, "Failed Code Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
