package com.example.ramadanel_sayed.localshop.MyClasses;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Ramadan El-Sayed on 12/26/2016.
 */
@Table(name = "productdata")

public class ProductData extends Model {
    @Column(name = "productkind")
    public   String ProductKind;
    @Column(name = "prodsatate")
    public String ProductState;
    @Column(name = "productcat")
    public String ProductCategory;
    @Column(name = "proimageone")
    public  String ProductImageOne;
    @Column(name = "productimagetwo")

    public String ProductImageTwo;
    @Column(name = "productimagethree")

    public String ProductImageThree;
    @Column(name = "productdesc")

    public String ProductDesc;
    @Column(name = "productprice")

    public  String ProductPricr;
    @Column(name = "productowner")

    public String CountryOwner;
    @Column(name = "productphone")

    public String PhoneNumber;
    @Column(name = "productcity")

    public String CityOwner;
    @Column(name = "ownername")

    public String OwnerName;
    @Column(name = "owneradd")

    public String OwnerEmailAddress;
    @Column(name = "productdate")

    public String PublishDate;

    public ProductData() {
        super();
    }

    public List<ProductData> getAllProduct()
    {
        return new Select().from(ProductData.class).execute();

    }

    public List<ProductData> getoneCat(String catName)
    {
        return new Select().from(ProductData.class).where("productcat = ?",catName).execute();

    }


    public List<ProductData> itemlist(String kind)
    {
        return new Select().from(ProductData.class).where("productkind = ?",kind).execute();

    }

}
