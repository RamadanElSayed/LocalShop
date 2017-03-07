package com.example.ramadanel_sayed.localshop;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ramadanel_sayed.localshop.MyClasses.ProductData;
import com.example.ramadanel_sayed.localshop.MyClasses.UserPicture;
import com.hbb20.CountryCodePicker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SellActivity extends AppCompatActivity {

    CountryCodePicker ccp;
    EditText NameOfProduct;
    EditText Desc;
    EditText Price;
    EditText phoneNumber;
    EditText City;
    EditText SellerName;
    EditText EmailSeller;
    EditText PublishDate;
    Spinner spinner;
    Spinner spinner2;
    String   ProductState;
    String   ProductCatName;
    String KindProduct;
    String DescProduct;
    String PriceOfProduct;
    String NumberPhone;
    String CityOfSell;
    String SellName;
    String EmailSell;
    String PublishDateSell;
    ImageView imageOne;
    ImageView imageTwo;
    ImageView imagethree;
    ImageButton dateBTn;
    Button sell;
    String  CountryName;
    String  CodeOfCountry;
    String baseofImageOne;
    String baseofImageTwo;
    String baseofImageThree;
    String phonewithcode;
    String UserNameToUseIt;
ProductData productData;
    ProgressDialog progressDialog;
    Bitmap bitmap;
    private static final int SELECT_SINGLE_PICTUREone = 101;
    private static final int SELECT_SINGLE_PICTUREtwo = 102;
    private static final int SELECT_SINGLE_PICTUREthree = 103;
    public static final String IMAGE_TYPE = "image/*";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        Intent i=getIntent();
        UserNameToUseIt=i.getExtras().getString("usernm").toString();
        productData=new ProductData();
        baseofImageOne="one";
        baseofImageTwo="two";
        baseofImageThree="three";

        progressDialog=new ProgressDialog(SellActivity.this,R.style.MyTheme);
        progressDialog.setTitle("Selling Product");
        progressDialog.setMessage("Uploading.....Plz wait...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        NameOfProduct=(EditText)findViewById(R.id.editText7);

        Desc=(EditText)findViewById(R.id.editText8) ;
        Price=(EditText)findViewById(R.id.editText9);
        phoneNumber= (EditText) findViewById(R.id.phone);
        City= (EditText) findViewById(R.id.editTextcity);
        SellerName=(EditText)findViewById(R.id.editTextcityname);
        EmailSeller=(EditText)findViewById(R.id.editTextcityemail);
        PublishDate=(EditText)findViewById(R.id.editTextdate);
        spinner=(Spinner)findViewById(R.id.spinner);
        spinner2=(Spinner) findViewById(R.id.spinnersp);
        imageOne=(ImageView)findViewById(R.id.imageone);
        imageTwo=(ImageView)findViewById(R.id.imagetwo);
        imagethree=(ImageView)findViewById(R.id.imagethree);
        dateBTn=(ImageButton)findViewById(R.id.datefromcal);
        sell=(Button)findViewById(R.id.BTPublish);
        ccp=(CountryCodePicker)findViewById(R.id.ccp);


        dateBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final java.util.Calendar c= java.util.Calendar.getInstance();
                int year=c.get(java.util.Calendar.YEAR);
                int month=c.get(java.util.Calendar.MONTH);
                int day=c.get(java.util.Calendar.DAY_OF_MONTH);
                DatePickerDialog dp=new DatePickerDialog(SellActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        //    Toast.makeText(MainActivity.this, year+ "/"+(month+1)+"/"+day, Toast.LENGTH_SHORT).show();
                        // java.util.Calendar dateofdaynow= java.util.Calendar.getInstance();
                        // java.text.DateFormat ddd=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ssZZZZZ", Locale.ENGLISH) ;
                        //String date2=ddd.format(new Date())  ;

                        PublishDate.setText(day+ "/"+(month+1)+"/"+year);

                    }
                },year,month,day);
                dp.show();
            }
        });

        imageOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType(IMAGE_TYPE);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        getString(R.string.select_pictureone)), SELECT_SINGLE_PICTUREone);
            }
        });

        imageTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType(IMAGE_TYPE);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        getString(R.string.select_picturetwo)), SELECT_SINGLE_PICTUREtwo);
            }
        });

        imagethree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType(IMAGE_TYPE);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        getString(R.string.select_picturethree)), SELECT_SINGLE_PICTUREthree);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ProductState=spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ProductCatName=spinner2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        CountryName= ccp.getSelectedCountryName();
        CodeOfCountry =  ccp.getSelectedCountryCodeWithPlus();
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                CountryName= ccp.getSelectedCountryName();
                CodeOfCountry=  ccp.getSelectedCountryCodeWithPlus();
            }
        });






         sell.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {



                 KindProduct=NameOfProduct.getText().toString();
                 DescProduct=Desc.getText().toString();
                 PriceOfProduct=Price.getText().toString();
                 NumberPhone=phoneNumber.getText().toString();
                 CityOfSell=City.getText().toString();
                 SellName=SellerName.getText().toString();
                 EmailSell=EmailSeller.getText().toString();
                 PublishDateSell=PublishDate.getText().toString();
                 phonewithcode=CodeOfCountry+":"+NumberPhone;


                 if(KindProduct.equals("")||DescProduct.equals("")||PriceOfProduct.equals("")||NumberPhone.equals("")||
                         CityOfSell.equals("")||SellName.equals("")||EmailSell.equals("")||PublishDateSell.equals("")||
                         baseofImageOne.equals("one")||baseofImageTwo.equals("two")||baseofImageThree.equals("three"))
                 {

                     Toast.makeText(SellActivity.this, "Please Fill all Fileds", Toast.LENGTH_SHORT).show();
                     // Toast.makeText(SellActivity.this, "phone state is ...."+PhoneState, Toast.LENGTH_SHORT).show();
                 }


               else
                 {
                     progressDialog.show();

                     /*
                       selldifferencatfirebase selldifferencatfirebase=new selldifferencatfirebase
                       (KindProduct,ProductState,baseofImageOne,baseofImageTwo
                        ,baseofImageThree,DescProduct,CountryName,phonewithcode,PublishDateSell,SellName,
                        CityOfSell,EmailSell,ProductCatName);

                      */


                     productData.ProductKind=KindProduct;
                     productData.ProductState=ProductState;
                     productData.ProductImageOne=baseofImageOne;
                     productData.ProductImageTwo=baseofImageTwo;
                     productData.ProductImageThree=baseofImageThree;
                     productData.ProductDesc=DescProduct;
                     productData.CountryOwner=CountryName;
                     productData.PhoneNumber=phonewithcode;
                     productData.PublishDate=PublishDateSell;
                     productData.OwnerName=SellName;
                     productData.CityOwner=CityOfSell;
                     productData.OwnerEmailAddress=EmailSell;
                     productData.ProductPricr=PriceOfProduct;
                     productData.ProductCategory=ProductCatName;

                      productData.save();


                     progressDialog.dismiss();

                     Toast.makeText(SellActivity.this, "Thank You For Selling", Toast.LENGTH_SHORT).show();
                     Intent i=new Intent(SellActivity.this,MainApp.class);
                     i.putExtra("userNam",UserNameToUseIt);
                     startActivity(i);
                 }


             }
         });

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_SINGLE_PICTUREone) {

                Uri selectedImageUri = data.getData();
                try {
                    imageOne.setImageBitmap(new UserPicture(selectedImageUri, getContentResolver()).getBitmap());
                    Bitmap bitmap=new UserPicture(selectedImageUri, getContentResolver()).getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                    byte[]bytes=byteArrayOutputStream.toByteArray();
                    baseofImageOne= Base64.encodeToString(bytes,Base64.DEFAULT);

//                    byte[]arrayofByte=Base64.decode(baseofImage,Base64.DEFAULT);
//                    Bitmap bitmap1= BitmapFactory.decodeByteArray(arrayofByte,0,arrayofByte.length);
//                    selectedImagePreview.setImageBitmap(bitmap1);
//                    Toast.makeText(MainActivity.this, ""+baseofImage, Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    Log.e(SellActivity.class.getSimpleName(), "Failed to load image", e);
                }

            }

            else if (requestCode == SELECT_SINGLE_PICTUREtwo)
            {
                Uri selectedImageUri = data.getData();
                try {
                    imageTwo.setImageBitmap(new UserPicture(selectedImageUri, getContentResolver()).getBitmap());
                    bitmap=new UserPicture(selectedImageUri, getContentResolver()).getBitmap();

                    ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                    byte[]bytes=byteArrayOutputStream.toByteArray();
                    baseofImageTwo= Base64.encodeToString(bytes,Base64.DEFAULT);

//                    byte[]arrayofByte=Base64.decode(baseofImage,Base64.DEFAULT);
//                    Bitmap bitmap1= BitmapFactory.decodeByteArray(arrayofByte,0,arrayofByte.length);
//                    selectedImagePreview.setImageBitmap(bitmap1);
//                    Toast.makeText(MainActivity.this, ""+baseofImage, Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    Log.e(SellActivity.class.getSimpleName(), "Failed to load image", e);
                }

            }
            else if(requestCode == SELECT_SINGLE_PICTUREthree)

            {

                Uri selectedImageUri = data.getData();
                try {
                    imagethree.setImageBitmap(new UserPicture(selectedImageUri, getContentResolver()).getBitmap());
                    Bitmap bitmap=new UserPicture(selectedImageUri, getContentResolver()).getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                    byte[]bytes=byteArrayOutputStream.toByteArray();
                    baseofImageThree= Base64.encodeToString(bytes,Base64.DEFAULT);

//                    byte[]arrayofByte=Base64.decode(baseofImage,Base64.DEFAULT);
//                    Bitmap bitmap1= BitmapFactory.decodeByteArray(arrayofByte,0,arrayofByte.length);
//                    selectedImagePreview.setImageBitmap(bitmap1);
//                    Toast.makeText(MainActivity.this, ""+baseofImage, Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    Log.e(SellActivity.class.getSimpleName(), "Failed to load image", e);
                }
            }


        }


    }
}
