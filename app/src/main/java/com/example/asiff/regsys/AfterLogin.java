package com.example.asiff.regsys;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class AfterLogin extends AppCompatActivity implements View.OnClickListener{

    MediaPlayer ma;
    MediaPlayer mb;
    String result;
    private static final String REGISTER_URL = "http://shayokhasif.netne.net/LoonEG.php";
    public  String PASS,VASS,NID;
    Button buttonLoanEg;
    Button buttonTR;
    public PendingIntent pendingIntent;

    public    Intent myIntent;// = new Intent(MainActivity.this, MyAlarmService.class);
    public   AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

setTitle("লগ ইন পরবর্তী ধাপ ");

        ma= MediaPlayer.create(this, R.raw.a);
mb= MediaPlayer.create(this, R.raw.b);

        Bundle bundle = getIntent().getExtras();
         VASS = bundle.getString("VASS");
         PASS = bundle.getString("PASS");
        NID =bundle.getString("NID");


        buttonLoanEg = (Button) findViewById(R.id.buttonLoanEg);
        buttonTR=(Button)findViewById(R.id.buttonTR);

        buttonLoanEg.setOnClickListener(this);
        buttonTR.setOnClickListener(this);



    }









    public  void stop(View v)
    {
        myIntent = new Intent(AfterLogin.this, MyAlarmService.class);
//////////////////////////////////////////
        // pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);
        //AlarmManager

        //////new



        pendingIntent = PendingIntent.getService(AfterLogin.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        ///////////new

        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        alarmManager.cancel(pendingIntent);


        ////////////////new///////////////////////////////////


        //  PendingIntent.getService(MainActivity.this, 0, myIntent,
        //        PendingIntent.FLAG_UPDATE_CURRENT).cancel();

        ////////////////new////////////////////////////////////


        // Intent myIntent = new Intent(MainActivity.this, MyAlarmService.class);
        stopService(myIntent);




        // Tell the user about what we did.
        Toast.makeText(AfterLogin.this, "Service Stopped", Toast.LENGTH_LONG).show();


        //Intent h=new Intent(MainActivity.this,HelpActivity.class);
        //startActivity(h);
    }

    public  void start(View v)
    {
      //  Toast.makeText(AfterLogin.this, "Check ppppn", Toast.LENGTH_LONG).show();
        myIntent = new Intent(AfterLogin.this, MyAlarmService.class);
        //myIntent.putExtra("PASS",password);
        myIntent.putExtra("VASS", VASS);
/////////////////////////////////////

        pendingIntent = PendingIntent.getService(AfterLogin.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //AlarmManager
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 1);//the time interval between first button press & start of service (directly in second unit)
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 60 * 1000, pendingIntent);

        startService(myIntent);



    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_after_login, menu);
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


    public void onClick(View v) {

        if(v ==  buttonLoanEg){




                Getloan();
        }


        else if(v==buttonTR)
        {
            GetTR();
        }
    }

    private  void GetTR()
    {
        tl(NID);
    }


    private void Getloan() {





        gl(NID);
    }


    private void gl(String nid) {
        String urlSuffix = "?nid="+nid;
        class Getloan extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AfterLogin.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
               // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                String k=s.trim();
               // Toast.makeText(getApplicationContext(), "\nkKKK"+result, Toast.LENGTH_LONG).show();
                boolean correct = "success".equals(k);

                if(correct)
                {
                    ma.start();

                    Toast.makeText(getApplicationContext(), "আপনি ঋণ  পাওয়ার  যোগ্য । ", Toast.LENGTH_LONG).show();

                }else {
                    mb.start();
                    Toast.makeText(getApplicationContext(), "No loan for you", Toast.LENGTH_LONG).show();
                }



            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTER_URL+s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));



                    result = bufferedReader.readLine();

                    return result;
                }catch(Exception e){
                    return null;
                }
            }
        }

        Getloan lu = new Getloan();
        lu.execute(urlSuffix);
    }







    private void tl(String nid) {
        String urlSuffix = "?nid="+nid;
        class GetTR extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AfterLogin.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                String k=s.trim();
                // Toast.makeText(getApplicationContext(), "\nkKKK"+result, Toast.LENGTH_LONG).show();
                boolean correct = "success".equals(k);

                if(correct)
                {
                   // ma.start();

                    Toast.makeText(getApplicationContext(), "আপনি প্রশিক্ষণের জন্য নির্বাচিত হয়েছেন  ", Toast.LENGTH_LONG).show();

                }else {
                    //mb.start();
                    Toast.makeText(getApplicationContext(), "\n" +
                            "আপনি প্রশিক্ষণের জন্য নির্বাচিত হননি   ", Toast.LENGTH_LONG).show();
                }



            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTER_URL+s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));



                    result = bufferedReader.readLine();

                    return result;
                }catch(Exception e){
                    return null;
                }
            }
        }

        GetTR ku = new GetTR();
        ku.execute(urlSuffix);
    }








}
