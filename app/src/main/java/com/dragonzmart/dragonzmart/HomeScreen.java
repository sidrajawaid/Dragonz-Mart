package com.dragonzmart.dragonzmart;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.MailTo;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class HomeScreen extends AppCompatActivity {
    EditText keyword_edittext;
    Button search_button;
    Button request_more_quotes;
    android.support.v7.widget.Toolbar toolbar;
    Firebase FirebaseRef;
    GetData getDataFromPermission;
    Location location;
    String cityCountry = "";
    private static final int PERMISSION_REQUEST_LOCATION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(this);
        keyword_edittext=findViewById(R.id.keyword);
        request_more_quotes=findViewById(R.id.rmq_button);
        search_button=findViewById(R.id.search_button);
        request_more_quotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab=new AlertDialog.Builder(HomeScreen.this);
                ab.setMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut" +
                        " labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi" +
                        " ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum " +
                        "dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia" +
                        " deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod" +
                        " tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation" +
                        " ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate" +
                        " velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in" +
                        " culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut" +
                        " labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi" +
                        " ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum " +
                        "dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia" +
                        " deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod" +
                        " tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation" +
                        " ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate" +
                        " velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in" +
                        " culpa qui officia deserunt mollit anim id est laborum.\"\"" )
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                //ab.show();
                AlertDialog alert=ab.create();
                alert.setTitle("Dragonz Mart");
                alert.setIcon(R.drawable.dragonzmartlogo);
                alert.show();

            }
        });

        FirebaseRef=new Firebase("https://dragonz-mart.firebaseio.com/user");

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    AfterEditTextChecked();

            }
        });
    }
    public String getIP(){
        WifiManager wm = (WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        return ip;
    }
    public boolean checkEditText() {
        int flag=0;
        for(int i=0;i<=keyword_edittext.length()-1;i++)
        {
            if(keyword_edittext.getText().charAt(i)!=' ')
            {
                flag=0;
                break;
            }
            else{
                flag=1;
            }
        }
        if(keyword_edittext.length()==0)
        {
            return true;
        }
        else if(flag==1)
        {
            return true;
        }

        else
        {
            return false;
        }
    }
    public void AfterEditTextChecked()
    {
        if(checkEditText())
        {
            AlertDialog.Builder ab=new AlertDialog.Builder(HomeScreen.this);
            ab.setMessage("You haven't entered any item!" )
                    .setCancelable(true)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            //ab.show();
            AlertDialog alert=ab.create();
            alert.setTitle("Dragonz Mart");
            alert.setIcon(R.drawable.dragonzmartlogo);
            alert.show();
            alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorAccent));
        }
        else if(!checkInternet()){
            AlertDialog.Builder ab=new AlertDialog.Builder(HomeScreen.this);
            ab.setMessage("You have no Internet connection!" )
                    .setCancelable(true)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            //ab.show();
            AlertDialog alert=ab.create();
            alert.setTitle("Dragonz Mart");
            alert.setIcon(R.drawable.dragonzmartlogo);
            alert.show();
            alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorAccent));
            keyword_edittext.setText("");
            keyword_edittext.setHint("Type your item here");

        }
        else
        {
            WorkingWithFirebase();
            ThanksLAlert();

        }

    }
    public  void ThanksLAlert()
    {
        AlertDialog.Builder ab=new AlertDialog.Builder(HomeScreen.this);
        ab.setMessage("  Thanks for your order!"+"\n"+"We will get back to you soon" )
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        //ab.show();
        AlertDialog alert=ab.create();
        alert.setTitle("Dragonz Mart");
        alert.setIcon(R.drawable.dragonzmartlogo);
        alert.show();
        alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.themecolor));
        AtLast();

    }
    public String getCityCountry(double lat, double lon)
    {

        //ArrayList<String> cityCountry_list=new ArrayList<String>();
        Geocoder geocoder=new Geocoder(HomeScreen.this, Locale.getDefault());
        List<Address> addressList;
        String cucity="";
        try{
            addressList=geocoder.getFromLocation(lat,lon,1);
            if(addressList.size()>0)
            {
                cucity=addressList.get(0).getLocality();
                //cityCountry_list.add(addressList.get(0).getLocality());
                //cityCountry_list.add(addressList.get(0).getCountryName());
                //System.out.print(cityCountry);

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return cucity;

    }
    public void WorkingWithFirebase(){
        /*if(location != null)
        {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            Log.d(HomeScreen.class.getName(),"latitude:"+latitude);
            Log.d(HomeScreen.class.getName(),"longitude:"+longitude);
            ArrayList<String> cityCountryList = new ArrayList<>();

            if(latitude != 0.0 && longitude != 0.0)
            {
               cityCountryList = getCityCountry(latitude,longitude);
            }
            else if(latitude==0.0&&longitude==0.0)
            {
                Toast.makeText(HomeScreen.this,"Both location 0",Toast.LENGTH_LONG).show();
            }
            if(cityCountryList.size() > 1)
            {
                cityCountry = cityCountryList.get(0) + cityCountryList.get(1);
                System.out.print("This is your country"+cityCountry);
            }

        }*/
        getDataFromPermission=new GetData(getIP(),00000000,keyword_edittext.getText().toString()," karachi,Pakistan",new ContactList("abc",0000000));
        FirebaseRef.push().setValue(getDataFromPermission);
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case PERMISSION_REQUEST_LOCATION:
            {
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(HomeScreen.this, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
                    {
                        LocationManager locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
                        location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        try{
                            //tv1.setText(getCityCountry(location.getLatitude(),location.getLongitude()));
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            Toast.makeText(HomeScreen.this,"Not Found",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(HomeScreen.this,"Not Permission granted",Toast.LENGTH_SHORT).show();
                    }

                }
            }

        }
    }
    public void AtLast()
    {
        keyword_edittext.setText("");
        keyword_edittext.setHint("Type your item to search");
    }
    public boolean gettingPermission(){
        if (ContextCompat.checkSelfPermission(HomeScreen.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(HomeScreen.this, android.Manifest.permission.ACCESS_COARSE_LOCATION))
            {
                ActivityCompat.requestPermissions(HomeScreen.this,new String[] {android.Manifest.permission.ACCESS_COARSE_LOCATION},PERMISSION_REQUEST_LOCATION);
            }
            else
            {
                ActivityCompat.requestPermissions(HomeScreen.this,new String[] {android.Manifest.permission.ACCESS_COARSE_LOCATION},PERMISSION_REQUEST_LOCATION);

            }
        }
        else{
            LocationManager locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
            location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            try{
                //tv1.setText(getCityCountry(location.getLatitude(),location.getLongitude()));
                return true;

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return true;

    }

    public boolean checkInternet() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] ni = connectivityManager.getAllNetworkInfo();
        for(NetworkInfo networkInfo:ni)
        {
            if (networkInfo.getTypeName().equalsIgnoreCase("WIFI"))
                if (networkInfo.isConnected())
                    haveConnectedWifi = true;
            if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                if (networkInfo.isConnected())
                    haveConnectedMobile = true;
        }

        return haveConnectedMobile || haveConnectedWifi;
    }


}




