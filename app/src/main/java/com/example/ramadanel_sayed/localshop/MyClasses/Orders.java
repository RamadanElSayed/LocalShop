package com.example.ramadanel_sayed.localshop.MyClasses;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Ramadan El-Sayed on 12/27/2016.
 */
@Table(name = "orderscustomers")
public class Orders extends Model {
    @Column(name = "username")
  public  String userName;
    @Column(name = "imageorders")
  public  String Imageorder;
    @Column(name = "date")
  public  String dateOrder;
    @Column(name = "ordername")
  public  String kindItem;

    public Orders() {
        super();
    }
    public List<Orders> getoneCat(String name)
    {
        return new Select().from(Orders.class).where("username = ?",name).execute();

    }
    public  void deleteItem(String Itemdata)
    {
        try {
            new Delete()
                    .from(Orders.class)
                    .where("imageorders = ?",
                            Itemdata).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
