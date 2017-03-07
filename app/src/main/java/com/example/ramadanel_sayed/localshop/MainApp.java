package com.example.ramadanel_sayed.localshop;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainApp extends AppCompatActivity {
    String UserNameToUseIt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        Intent i=getIntent();
        UserNameToUseIt=i.getExtras().getString("userNam").toString();
        Button Buy=(Button)findViewById(R.id.buttonBuy);
        final Button Sell=(Button)findViewById(R.id.buttonSell);
          Button order=(Button)findViewById(R.id.showorders);


        final int[]array={R.drawable.s,R.drawable.c,R.drawable.t,R.drawable.u,R.drawable.v,R.drawable.d};
        final ImageView imageView=(ImageView)findViewById(R.id.imageView6);
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i=0;
            public void run() {
                imageView.setImageResource(array[i]);
                i++;
                if(i>array.length-1)
                {
                    i=0;
                }
                handler.postDelayed(this, 2000);  //for interval...
            }
        };
        handler.postDelayed(runnable, 700); //for initial delay..



        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i=new Intent(MainApp.this,BuyActivity.class);
                i.putExtra("usernm",UserNameToUseIt);
                startActivity(i);
            }
        });

       Sell.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(MainApp.this,SellActivity.class);
               i.putExtra("usernm",UserNameToUseIt);

               startActivity(i);

           }
       });


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainApp.this,YourOrders.class);
                i.putExtra("username",UserNameToUseIt);
                startActivity(i);
            }
        });




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menuone, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id)

        {
            case R.id.action_settings:
            {
                Intent i=new Intent(MainApp.this,AboutMe.class);
                startActivity(i);
                return true;
        }
            case R.id.action_user:
            {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "AndroidSolved");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "https://androidsolved.wordpress.com/");
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                return true;
            }

        }

        return super.onOptionsItemSelected(item);
    }
}
