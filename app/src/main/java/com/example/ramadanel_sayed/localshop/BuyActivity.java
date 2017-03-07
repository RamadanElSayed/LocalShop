package com.example.ramadanel_sayed.localshop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramadanel_sayed.localshop.MyClasses.ClassAdapForSellPhone;
import com.example.ramadanel_sayed.localshop.MyClasses.ClassforsellPhone;
import com.example.ramadanel_sayed.localshop.MyClasses.ProductData;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BuyActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ClassAdapForSellPhone classAdapForSellPhone;
    ListView listView;
    List<ProductData>prdata;
    ProductData productData;
    ArrayList<ClassforsellPhone> list;
    ImageButton searchbyText;
    ImageButton searchbyVoice;
    EditText fromtexttoSearch;
    int voiceCode=1;
String UserNameTwoUse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent j=getIntent();
       UserNameTwoUse=j.getExtras().getString("usernm").toString();
        searchbyText=(ImageButton) findViewById(R.id.button2);
        searchbyVoice=(ImageButton) findViewById(R.id.button3);
         fromtexttoSearch=(EditText)findViewById(R.id.editText4);
        productData=new ProductData();
        listView=(ListView)findViewById(R.id.listView2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textdate=(TextView)view.findViewById(R.id.textView7);
                TextView textkind=(TextView)view.findViewById(R.id.textView15);
                ImageView imageView=(ImageView)view.findViewById(R.id.imageView8);

                 String date=textdate.getText().toString();
                 String KindP=textkind.getText().toString();
                 BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                 Bitmap yourBitmap = bitmapDrawable.getBitmap();

                 ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                yourBitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                 byte[]bytes=byteArrayOutputStream.toByteArray();
                String    baseofImageTwo= Base64.encodeToString(bytes,Base64.DEFAULT);

                Intent intent =new Intent(BuyActivity.this,ProductProperties.class);
                   //   intent.putExtra("datep",date);
                      intent.putExtra("kindp",KindP);
                      intent.putExtra("use",UserNameTwoUse);
                   //   intent.putExtra("imageone",baseofImageTwo);
                     startActivity(intent);





            }
        });
        list=new ArrayList<>();


        prdata=productData.getAllProduct();
        for (ProductData pro:prdata)
        {
            list.add(new ClassforsellPhone(pro.PublishDate,pro.ProductKind,pro.ProductImageOne));
        }
        classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
        listView.setAdapter(classAdapForSellPhone);


         searchbyText.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String fromText=fromtexttoSearch.getText().toString();

                 list=new ArrayList<>();


                 prdata=productData.getAllProduct();

                 for (ProductData pro:prdata)
                 {
                     if(pro.ProductKind.toLowerCase().contains(fromText.toLowerCase())) {
                         list.add(new ClassforsellPhone(pro.PublishDate, pro.ProductKind, pro.ProductImageOne));
                     }
                 }

                 classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
                 listView.setAdapter(classAdapForSellPhone);
             }
         });


        searchbyVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(intent,voiceCode);
            }
        });



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.buy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }







    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.mobiletablet) {
            String mobileCat="Mobile and Tablets";
            list=new ArrayList<>();


            prdata=productData.getoneCat(mobileCat);
            for (ProductData pro:prdata)
            {
                list.add(new ClassforsellPhone(pro.PublishDate,pro.ProductKind,pro.ProductImageOne));
            }
            classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
            listView.setAdapter(classAdapForSellPhone);


        }

        else if (id == R.id.baby) {
          String babyCat="Baby";

            list=new ArrayList<>();


            prdata=productData.getoneCat(babyCat);
            for (ProductData pro:prdata)
            {
                list.add(new ClassforsellPhone(pro.PublishDate,pro.ProductKind,pro.ProductImageOne));
            }
            classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
            listView.setAdapter(classAdapForSellPhone);

        }

        else if (id == R.id.art) {
           String artCat="Art,Crafts and Collections";


            list=new ArrayList<>();


            prdata=productData.getoneCat(artCat);
            for (ProductData pro:prdata)
            {
                list.add(new ClassforsellPhone(pro.PublishDate,pro.ProductKind,pro.ProductImageOne));
            }
            classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
            listView.setAdapter(classAdapForSellPhone);

        } else if (id == R.id.book) {
          String bookCat="Books";



            list=new ArrayList<>();


            prdata=productData.getoneCat(bookCat);
            for (ProductData pro:prdata)
            {
                list.add(new ClassforsellPhone(pro.PublishDate,pro.ProductKind,pro.ProductImageOne));
            }
            classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
            listView.setAdapter(classAdapForSellPhone);
        }


        else if (id == R.id.car) {
            String carCat="Car Electronics and Accessories";

            list=new ArrayList<>();


            prdata=productData.getoneCat(carCat);
            for (ProductData pro:prdata)
            {
                list.add(new ClassforsellPhone(pro.PublishDate,pro.ProductKind,pro.ProductImageOne));
            }
            classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
            listView.setAdapter(classAdapForSellPhone);

        }


        else if (id == R.id.clothes) {
            String clothCat="Clothing and Accessories";


            list=new ArrayList<>();


            prdata=productData.getoneCat(clothCat);
            for (ProductData pro:prdata)
            {
                list.add(new ClassforsellPhone(pro.PublishDate,pro.ProductKind,pro.ProductImageOne));
            }
            classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
            listView.setAdapter(classAdapForSellPhone);
        }
        else if (id == R.id.computers)

        {
          String compCat="Computers and Networking";


            list=new ArrayList<>();


            prdata=productData.getoneCat(compCat);
            for (ProductData pro:prdata)
            {
                list.add(new ClassforsellPhone(pro.PublishDate,pro.ProductKind,pro.ProductImageOne));
            }
            classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
            listView.setAdapter(classAdapForSellPhone);
        }

        else if (id == R.id.electronics) {
             String electCat="Electronics";

            list=new ArrayList<>();


            prdata=productData.getoneCat(electCat);
            for (ProductData pro:prdata)
            {
                list.add(new ClassforsellPhone(pro.PublishDate,pro.ProductKind,pro.ProductImageOne));
            }
            classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
            listView.setAdapter(classAdapForSellPhone);

        }

        else if (id == R.id.game) {
         String GanCat="Games and Toys";


            list=new ArrayList<>();


            prdata=productData.getoneCat(GanCat);
            for (ProductData pro:prdata)
            {
                list.add(new ClassforsellPhone(pro.PublishDate,pro.ProductKind,pro.ProductImageOne));
            }
            classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
            listView.setAdapter(classAdapForSellPhone);
        }

        else if (id == R.id.healthy) {
           String healthCat="Health and Beauty";


            list=new ArrayList<>();


            prdata=productData.getoneCat(healthCat);
            for (ProductData pro:prdata)
            {
                list.add(new ClassforsellPhone(pro.PublishDate,pro.ProductKind,pro.ProductImageOne));
            }
            classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
            listView.setAdapter(classAdapForSellPhone);
        }


        else if (id == R.id.home) {
        String RomCat="Home,Kitchen and Garden";


            list=new ArrayList<>();


            prdata=productData.getoneCat(RomCat);
            for (ProductData pro:prdata)
            {
                list.add(new ClassforsellPhone(pro.PublishDate,pro.ProductKind,pro.ProductImageOne));
            }
            classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
            listView.setAdapter(classAdapForSellPhone);
        }

        else if (id == R.id.bags) {
         String bagCat="Shoes and Bags";


            list=new ArrayList<>();


            prdata=productData.getoneCat(bagCat);
            for (ProductData pro:prdata)
            {
                list.add(new ClassforsellPhone(pro.PublishDate,pro.ProductKind,pro.ProductImageOne));
            }
            classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
            listView.setAdapter(classAdapForSellPhone);
        }

        else if (id == R.id.sport) {
         String sportCat="Sports and Outdoors";


            list=new ArrayList<>();


            prdata=productData.getoneCat(sportCat);
            for (ProductData pro:prdata)
            {
                list.add(new ClassforsellPhone(pro.PublishDate,pro.ProductKind,pro.ProductImageOne));
            }
            classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
            listView.setAdapter(classAdapForSellPhone);
        }

        else if (id == R.id.jewllery) {
             String jewleCat="Jewelry and Accessories";


            list=new ArrayList<>();


            prdata=productData.getoneCat(jewleCat);
            for (ProductData pro:prdata)
            {
                list.add(new ClassforsellPhone(pro.PublishDate,pro.ProductKind,pro.ProductImageOne));
            }
            classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
            listView.setAdapter(classAdapForSellPhone);
        }

        else if (id == R.id.watches) {
         String watchCat="Watches and Accessories";

            list=new ArrayList<>();


            prdata=productData.getoneCat(watchCat);
            for (ProductData pro:prdata)
            {
                list.add(new ClassforsellPhone(pro.PublishDate,pro.ProductKind,pro.ProductImageOne));
            }
            classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
            listView.setAdapter(classAdapForSellPhone);
        }

        else if (id == R.id.offices) {
         String officeCat="Office and Supplies";

            list=new ArrayList<>();


            prdata=productData.getoneCat(officeCat);
            for (ProductData pro:prdata)
            {
                list.add(new ClassforsellPhone(pro.PublishDate,pro.ProductKind,pro.ProductImageOne));
            }
            classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
            listView.setAdapter(classAdapForSellPhone);
        }

        else if (id==R.id.allcat)
        {
            list=new ArrayList<>();


            prdata=productData.getAllProduct();
            for (ProductData pro:prdata)
            {
                list.add(new ClassforsellPhone(pro.PublishDate,pro.ProductKind,pro.ProductImageOne));
            }
            classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
            listView.setAdapter(classAdapForSellPhone);

        }

        else if (id==R.id.mycart)
        {
            Intent i=new Intent(BuyActivity.this,YourOrders.class);
            i.putExtra("username",UserNameTwoUse);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==voiceCode&&resultCode==RESULT_OK)
        {
            ArrayList<String> arrayList=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            fromtexttoSearch.setText(arrayList.get(0));

            String fromText=fromtexttoSearch.getText().toString();

            list=new ArrayList<>();


            prdata=productData.getAllProduct();

            for (ProductData pro:prdata)
            {
                if(pro.ProductKind.toLowerCase().contains(fromText.toLowerCase())) {
                    list.add(new ClassforsellPhone(pro.PublishDate, pro.ProductKind, pro.ProductImageOne));
                }
            }

            classAdapForSellPhone=new ClassAdapForSellPhone(BuyActivity.this,list);
            listView.setAdapter(classAdapForSellPhone);

        }
    }
}
