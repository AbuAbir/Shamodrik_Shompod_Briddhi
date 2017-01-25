package com.example.asiff.regsys;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public static final String MY_JSON ="MY_JSON";

    private static final String JSON_URL = "http://shayokhasif.netne.net/CurrentPrice.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Button GetCP=(Button)findViewById(R.id.buttonCP);

         setTitle("প্রথম পাতা");

    }
public void IMG(View v)
{

    Intent intent=new Intent(MainActivity.this,AndroidUploadImage.class);
    startActivity(intent);
}

    public void CurrentP(View v)
    {
       // Toast.makeText(MainActivity.this, "Check ppppn", Toast.LENGTH_LONG).show();
      // if(isNetworkAvailable())
    {
        getJSON(JSON_URL);
    }
    //else
       {
         //  Toast.makeText(MainActivity.this, "Check your Internet connection", Toast.LENGTH_LONG).show();


      // Alert();


    }
    }






    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit")
                .setMessage("Are you sure you want to Exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME));
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }





    private void showParseActivity(String s) {
        if(s.length()!=0)
        {
            Intent intent = new Intent(this, ParseJson.class);
            // intent.putExtra(MY_JSON,textViewJSON.getText().toString());
            intent.putExtra("s", s);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "START", Toast.LENGTH_SHORT).show();
        }

    }



    private void getJSON(String url) {
        class GetJSON extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //  Toast.makeText(getApplicationContext(), "onPreExecute()", Toast.LENGTH_SHORT).show();
                loading = ProgressDialog.show(MainActivity.this, "Please Wait...",null,true,true);

            }

            @Override
            protected String doInBackground(String... params) {
                //  Toast.makeText(getApplicationContext(), " doInBackground", Toast.LENGTH_SHORT).show();

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }


                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }

            }

            @Override
            protected void onPostExecute(String s) {
                // if(s.length()!=0)
                {
                    super.onPostExecute(s);
                    loading.dismiss();
                    // textViewJSON.setText(s);
                    showParseActivity(s);
                }
                // else
                {
                    // loading.dismiss();
                }

            }
        }
        GetJSON gj = new GetJSON();
        gj.execute(url);

    }







    public void Registration (View v) {

        Context context = getApplicationContext();
        CharSequence text = "??? ";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intent=new Intent(MainActivity.this,Register.class);
        startActivity(intent);
    }


    public void Login (View v) {

        Context context = getApplicationContext();
        CharSequence text = "LOG!";
        int duration = Toast.LENGTH_LONG;

//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();

        Intent intent=new Intent(MainActivity.this,Login.class);
        startActivity(intent);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public  void next(MenuItem item)
    {
        int a=item.getItemId();
        switch(a)
        {
            case R.id.item1:
                Intent intent1 = new Intent(MainActivity.this,InformationShow.class);
                startActivity(intent1);
                break;

            case R.id.item2:
                Intent intent2 = new Intent(MainActivity.this,InformationShow.class);
                startActivity(intent2);
                break;

            case R.id.item3:
                Intent intent3 = new Intent(MainActivity.this,Manual.class);
                startActivity(intent3);
                break;
            default:
                break;

        }

    }

}
