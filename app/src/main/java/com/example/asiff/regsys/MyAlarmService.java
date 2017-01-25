package com.example.asiff.regsys;

/**
 * Created by asiff on 21/12/2015.
 */

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import com.example.asiff.regsys.GPSTracker;


public class MyAlarmService extends Service {


    MediaPlayer mm;
    double longi1, lati1, currentlongi, currentlati, radd,radd1, x, y,ln11,ln22,lt11,lt22;
    final double PI = 3.14159265;
    final double deg2radians = PI / 180.0;
    // public MediaPlayer p=MediaPlayer.create(this, R.raw.a);
    String str;
    String w;
    String rx;
    String city;
    int kk=0;

    // AudioManager mode = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

    LocationManager manager;

    GPSTracker gps;


    @Override

    public void onCreate() {

        //Toast.makeText(getApplicationContext(),"on crerte ", Toast.LENGTH_SHORT).show();




        mm= MediaPlayer.create(this, R.raw.mm);
        manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);


//        Toast.makeText(getBaseContext(), "Service activated", Toast.LENGTH_LONG).show();

    }




    public IBinder onBind(Intent intent) {
  //      Toast.makeText(this, "MyAlarmService.onBind()", Toast.LENGTH_LONG).show();

        return null;

    }




    public void onDestroy() {

// TODO Auto-generated method stub


        super.onDestroy();

        //Toast.makeText(this, "MyAlarmService.onDestroy()", Toast.LENGTH_LONG).show();

    }




    public void onStart(Intent intent, int startId) {

        super.onStart(intent, startId);//Toast.makeText(this, "MyAlarmService.onStart()", Toast.LENGTH_LONG).show();


        LocationListener listener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location)
            {
                //  Toast.makeText(getApplicationContext(), "nLocationChang", Toast.LENGTH_SHORT).show();
                //currentlati = location.getLatitude();
                //currentlongi = location.getLongitude();

                //str = "lat: " + location.getLatitude() + "longi: " + location.getLongitude();
                //  Toast.makeText(getApplicationContext(), "current lati=  "+currentlati, Toast.LENGTH_SHORT).show();
                // Toast.makeText(getApplicationContext(), "current lati=  "+currentlati+"current longii=  "+currentlongi, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
                Toast.makeText(getApplicationContext(), "GPS is Enabled", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onProviderDisabled(String provider) {
                Toast.makeText(getApplicationContext(), "GPS is Disabled", Toast.LENGTH_SHORT).show();

            }


        };


        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);

      //  String TableName = "myTables";
        //SQLiteDatabase myDBs  ;

        try {

            gps = new GPSTracker(MyAlarmService.this);
            double clat=0;
            double clong=0;
            int var=0;
            while(clat==0.0&&clong==0.0)
            {
                clat = gps.getLatitude();
                clong = gps.getLongitude();
                var++;
                if(var==500)
                    break;
            }

            //Toast.makeText(getApplicationContext(), "current lati=  "+clat+"current longii=  "+clong, Toast.LENGTH_SHORT).show();


          //  myDBs = openOrCreateDatabase("agps_db", MODE_PRIVATE, null);
            //Cursor c =myDBs.rawQuery("SELECT location ,rimtion ,longi ,lati ,cityname FROM " + TableName, null);
            //if (c != null)
            {
                boolean  match=false;
              //  c.moveToFirst();
                /*currentlongi=currentlongi * deg2radians;
                currentlati=currentlati* deg2radians;*/
                currentlongi=clong * deg2radians;
                currentlati=clat* deg2radians;

                //int yr,mn,dte,yrcal,yrmn,yrdte;
                // yr=c.getInt(c.getColumnIndex("year"));
                //  mn=c.getInt(c.getColumnIndex("month"));
                //  dte=c.getInt(c.getColumnIndex("date"));

                // final Calendar cal = Calendar.getInstance();
                // int pYear = cal.get(Calendar.YEAR);
                //int pMonth = cal.get(Calendar.MONTH);
                //int pDay = cal.get(Calendar.DAY_OF_MONTH);
                // Toast.makeText(getApplicationContext(), "current month= "+pMonth, Toast.LENGTH_SHORT).show();
              //  do
                {
                    radd=0;

                    //  Log.d("x", "Do while loop");
                    ln11=21.45;
                    lt11=70.89;

                    longi1 = 22.45;
                    x=longi1;
                    lati1=87.45;
                    y=lati1;
                    // yr=c.getInt(c.getColumnIndex("year"));
                    //mn=c.getInt(c.getColumnIndex("month"));
                    //dte=c.getInt(c.getColumnIndex("date"));
                    //w = c.getString(c.getColumnIndex("location"));
                    //rx = c.getString(c.getColumnIndex("rimtion"));
                    //city=c.getString(c.getColumnIndex("cityname"));
                    longi1 = longi1 *  deg2radians;
                    lati1 = lati1  * deg2radians;
                    radd = 2*Math.asin(Math.sqrt(Math.pow(Math.sin((lati1-currentlati)/2),2.0) +  Math.cos(lati1)*Math.cos(currentlati)*Math.pow(Math.sin((longi1-currentlongi)/2),2.0)));
                    radd=(radd*6371)*3280.839;//FEET


                    radd1 = 2*Math.asin(Math.sqrt(Math.pow(Math.sin((lt11-currentlati)/2),2.0) +  Math.cos(lt11)*Math.cos(currentlati)*Math.pow(Math.sin((ln11-currentlongi)/2),2.0)));
                    radd1=(radd1*6371)*3280.839;//FEET






                    // Log.d("x=radd", "radd="+radd)
                    // Toast.makeText(getApplicationContext(), longi1+" "+lati1+" "+yr+" "+pYear+" "+mn+" "+pMonth+" "+dte+" "+pDay, Toast.LENGTH_LONG).show();
                    //Log.d("x=rad", "rad="+radd);


                    //    Toast.makeText(getApplicationContext(), " radious= "+radd+"city= "+city, Toast.LENGTH_LONG).show();



                    if(radd<=500 || radd1<=500)
                    {
                        // Log.d("x", "before match=true");
                        match=true;
                        mm.start();
                        //	Log.d("x", "after match=true");
                        // Toast.makeText(getApplication(), "AAAAAAAAA  Locatin Matched", Toast.LENGTH_LONG);
                  //      break;


                    }
                }
                //while(c.moveToNext());
                if(match==true)
                {
                    //Log.d("x", "before if match=true");

                    //Toast.makeText(getApplicationContext(), "Location Matched CURRENT IS "+clat+clong, Toast.LENGTH_LONG).show();



                    // Toast.makeText(getApplicationContext(), "Location Matched", Toast.LENGTH_LONG).show();



                    // final MediaPlayer mp=MediaPlayer.create(this, R.raw.m);
                    //mp.start();
                    //	     final MediaPlayer p=MediaPlayer.create(this, R.raw.a);
                    //p.start();

                    //Toast.makeText(getApplication(), "Locatin Matched", Toast.LENGTH_LONG);


                    silencer(kk);


                    //Toast.makeText(getBaseContext(), "Now Your Location Is  "+w, Toast.LENGTH_LONG).show();
                    //Toast.makeText(getBaseContext(), " "+rx, Toast.LENGTH_LONG).show();
                    //Toast.makeText(getBaseContext(), " "+rx, Toast.LENGTH_LONG).show();
                    //  if(kk!=0)
                    //mode.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    //else
                    //  kk++;
                    //p.stop();



                    kk++;



                }
                else
                {
                    //Toast.makeText(getApplicationContext(), "Location not Matched ", Toast.LENGTH_LONG).show();
                    AudioManager mode;
                    mode = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
                    mode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

                    //Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                    ;//

                    //Toast.makeText(getApplicationContext(), "is not Matched with "+clat+clong, Toast.LENGTH_LONG).show();
                    ;//
                }
            }
            //else
            {
               // Toast.makeText(getBaseContext(), "DB Empty",
                 //       Toast.LENGTH_LONG).show();
            }

        }
        catch(Exception ex)
        {
            //mode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

        }
    }



    @Override

    public boolean onUnbind(Intent intent) {

///
   //     Toast.makeText(this, "MyAlarmService.onUnbind()", Toast.LENGTH_LONG).show();

        return super.onUnbind(intent);

    }

    public  void stopService()
    {


    }
    public void silencer(int xx)
    {
        // if(xx!=0)
        {
            //Toast.makeText(getApplicationContext(), "silencer", Toast.LENGTH_SHORT).show();
            AudioManager myAudioManager;
            myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        }
        //  xx++;
    }
}

