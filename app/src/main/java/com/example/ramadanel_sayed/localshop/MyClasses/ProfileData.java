package com.example.ramadanel_sayed.localshop.MyClasses;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Ramadan El-Sayed on 12/26/2016.
 */
@Table(name = "profiledata")

public class ProfileData extends Model {
    @Column(name = "fname")
  public   String FirstName;
    @Column(name = "lname")
    public  String LastName;
    @Column(name = "email")
    public  String Email;
    @Column(name = "username")
    public String UserName;
    @Column(name = "birthdate")
    public  String BirthDate;
    @Column(name = "countryname")
    public  String CountryName;
    @Column(name = "phonenum")
    public String PhoneNumber;
    @Column(name = "password")
    public String Password;

    public ProfileData() {
      super();
    }



    public List<ProfileData> getAllCustomers()
    {
        return new Select().from(ProfileData.class).execute();

    }







}
