package com.example.ramadanel_sayed.localshop.MyClasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ramadanel_sayed.localshop.R;

import java.util.ArrayList;

/**
 * Created by Ramadan El-Sayed on 12/26/2016.
 */
    public class ClassAdapForSellPhone extends BaseAdapter {

        final private LayoutInflater inf;


        Context context;
        ArrayList<ClassforsellPhone> list;

        public ClassAdapForSellPhone(Context context, ArrayList<ClassforsellPhone> list) {
            this.context = context;
            this.list = list;
            inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {


            View v = inf.inflate(R.layout.sellphonelayout, null);

            ClassforsellPhone classforsellPhone=list.get(i);
            TextView textView= (TextView) v.findViewById(R.id.textView7);
            TextView textView2= (TextView) v.findViewById(R.id.textView15);

            ImageView imageView=(ImageView)v.findViewById(R.id.imageView8);

            textView.setText(classforsellPhone.getPublishDate());
            textView2.setText(classforsellPhone.getNameofPhone());

            String FirtsImage=classforsellPhone.getFirstImage();
            byte[]arrayofByte= Base64.decode(FirtsImage,Base64.DEFAULT);
            Bitmap bitmap1= BitmapFactory.decodeByteArray(arrayofByte,0,arrayofByte.length);
            imageView.setImageBitmap(bitmap1);

            return v;
        }
    }

