package com.example.ramadanel_sayed.localshop;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ramadanel_sayed.localshop.MyClasses.ClassAdapForSellPhone;
import com.example.ramadanel_sayed.localshop.MyClasses.ClassforsellPhone;
import com.example.ramadanel_sayed.localshop.MyClasses.Orders;
import com.example.ramadanel_sayed.localshop.MyClasses.ProductData;

import java.util.ArrayList;
import java.util.List;

public class YourOrders extends AppCompatActivity {

    ClassAdapForSellPhone classAdapForSellPhone;
    ListView listView;
    List<Orders> prdata;
    Orders productData;
    ArrayList<ClassforsellPhone> list;
    String UserNameToUse;
    int numberofcontextMenu;
    Dialog dialogDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_orders);
        listView=(ListView)findViewById(R.id.listVieworders);
        registerForContextMenu(listView);

        Intent i=getIntent();
        productData=new Orders();
        UserNameToUse=i.getExtras().getString("username").toString();


        list=new ArrayList<>();


        prdata=productData.getoneCat(UserNameToUse);
        for (Orders pro:prdata)
        {
            list.add(new ClassforsellPhone(pro.dateOrder,pro.kindItem,pro.Imageorder));
        }
        classAdapForSellPhone=new ClassAdapForSellPhone(YourOrders.this,list);
        listView.setAdapter(classAdapForSellPhone);



        AlertDialog.Builder builderconfirm=new AlertDialog.Builder(this);
        builderconfirm.setTitle("Delete:?");
        builderconfirm.setMessage("Are you sure you want to  delete  this Note");
        builderconfirm.setIcon(android.R.drawable.ic_dialog_alert);
        builderconfirm.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                productData=prdata.get(numberofcontextMenu);
                String nameofemp=productData.Imageorder;

                list.remove(numberofcontextMenu);
                classAdapForSellPhone.notifyDataSetChanged();
                productData.deleteItem(nameofemp);

            }
        });
        builderconfirm.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialogDelete=builderconfirm.create();
    }






    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.menccon,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        numberofcontextMenu=info.position;
        if(item.getItemId()==R.id.Delete)
        {

                dialogDelete.show();

                return true;
        }
        return false;
    }













}
