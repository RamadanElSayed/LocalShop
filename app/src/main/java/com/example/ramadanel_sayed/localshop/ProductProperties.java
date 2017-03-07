package com.example.ramadanel_sayed.localshop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramadanel_sayed.localshop.MyClasses.Orders;
import com.example.ramadanel_sayed.localshop.MyClasses.ProductData;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ProductProperties extends AppCompatActivity {

    List<ProductData>list;
    TextView kind;
    TextView  state;
    TextView category;
    TextView desc;
    TextView price;
    TextView city;
    TextView country;
    TextView ownerName;
    TextView ownerEmail;
    TextView publishDate;
    TextView phonenumber;
   ImageView imgeOne;
    ImageView imgTwo;
    ImageView iMgthree;

    String   ProductState;
    String   ProductCatName;
    String KindProducttwo;
    String DescProduct;
    String PriceOfProduct;
    String NumberPhone;
    String CityOfSell;
    String SellName;
    String EmailSell;
    String PublishDateSell;
    String  CountryName;
    String baseofImageOne;
    String baseofImageTwo;
    String baseofImageThree;
    Button addtoyourcart;
    String UserNmaeToUse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_properties);

        kind=(TextView)findViewById(R.id.nameproduct);
        state=(TextView)findViewById(R.id.productState);
        category=(TextView)findViewById(R.id.productcat);
        desc=(TextView)findViewById(R.id.productDesc);
        price=(TextView)findViewById(R.id.productPrice);
        city=(TextView)findViewById(R.id.produCity);
        country=(TextView)findViewById(R.id.productCountry);
        ownerName=(TextView)findViewById(R.id.ownerName);
        ownerEmail=(TextView)findViewById(R.id.ownerEmail);
        publishDate=(TextView)findViewById(R.id.ownerDate);
        phonenumber=(TextView)findViewById(R.id.produPhoneNumber);
        imgeOne=(ImageView)findViewById(R.id.textiMAgeone);
        imgTwo=(ImageView)findViewById(R.id.textiMAgetwo);
        iMgthree=(ImageView)findViewById(R.id.textiMAgethree);
         addtoyourcart=(Button)findViewById(R.id.addtocart);

        Intent i=getIntent();

        ProductData productData=new ProductData();
       // String Dateone=i.getExtras().getString("datep").toString();
        String KindProduct=i.getExtras().getString("kindp").toString();
        UserNmaeToUse=i.getExtras().getString("use").toString();
        Toast.makeText(ProductProperties.this, ""+UserNmaeToUse, Toast.LENGTH_SHORT).show();
     //  String ImageOne=i.getExtras().getString("imageone").toString();


      // Toast.makeText(ProductProperties.this, ""+KindProduct, Toast.LENGTH_SHORT).show();

        list=productData.itemlist(KindProduct);

       // Toast.makeText(ProductProperties.this, ""+list.size(), Toast.LENGTH_SHORT).show();
        for (ProductData pr:list)
        {
              ProductState=pr.ProductState;
              ProductCatName=pr.ProductCategory;
            KindProducttwo=pr.ProductKind;
             DescProduct=pr.ProductDesc;
             PriceOfProduct=pr.ProductPricr;
             NumberPhone=pr.PhoneNumber;
             CityOfSell=pr.CityOwner;
            SellName=pr.OwnerName;
            EmailSell=pr.OwnerEmailAddress;
             PublishDateSell=pr.PublishDate;
              CountryName=pr.CountryOwner;
             baseofImageOne=pr.ProductImageOne;
             baseofImageTwo=pr.ProductImageTwo;
            baseofImageThree=pr.ProductImageThree;
        }

        addtoyourcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Orders orders=new Orders();
                 orders.kindItem=KindProducttwo;
                orders.Imageorder=baseofImageOne;

                final java.util.Calendar c= java.util.Calendar.getInstance();
                int year=c.get(java.util.Calendar.YEAR);
                int month=c.get(java.util.Calendar.MONTH);
                int day=c.get(java.util.Calendar.DAY_OF_MONTH);
                String date2=day+"/"+month+"/"+year;
                orders.dateOrder=date2;
                orders.userName=UserNmaeToUse;
                orders.save();
                Toast.makeText(ProductProperties.this, "Done", Toast.LENGTH_SHORT).show();
                //Toast.makeText(ProductProperties.this, ""+date2, Toast.LENGTH_SHORT).show();

            }
        });

        Toast.makeText(ProductProperties.this, ""+ProductState, Toast.LENGTH_SHORT).show();
        kind.setText(KindProducttwo);
        state.setText(ProductState);
        category.setText(ProductCatName);
        desc.setText(DescProduct);
        price.setText(PriceOfProduct);
        city.setText(CityOfSell);
        country.setText(CountryName);
        ownerName.setText(SellName);
        ownerEmail.setText(EmailSell);
        publishDate.setText(PublishDateSell);
        phonenumber.setText(NumberPhone);
        byte[]arrayofByte= Base64.decode(baseofImageOne,Base64.DEFAULT);
        Bitmap bitmap1= BitmapFactory.decodeByteArray(arrayofByte,0,arrayofByte.length);

        imgeOne.setImageBitmap(bitmap1);
        byte[]arrayofBytetwo= Base64.decode(baseofImageTwo,Base64.DEFAULT);
        Bitmap bitmaptwo= BitmapFactory.decodeByteArray(arrayofBytetwo,0,arrayofBytetwo.length);

        imgTwo.setImageBitmap(bitmaptwo);
        byte[]arrayofBytethree= Base64.decode(baseofImageThree,Base64.DEFAULT);
        Bitmap bitmapthree= BitmapFactory.decodeByteArray(arrayofBytethree,0,arrayofBytethree.length);
        iMgthree.setImageBitmap(bitmapthree);

    }
}
