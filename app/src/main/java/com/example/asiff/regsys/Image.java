package com.example.asiff.regsys;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Image extends AppCompatActivity implements View.OnClickListener{


    String result;
    Button buttonSubmit;
    private static final String REGISTER_URL = "http://shayokhasif.netne.net/ovi.php";

    private EditText location;
    private EditText description;
    String loc,des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
         location=(EditText)findViewById(R.id.editTextLocation);
        description=(EditText)findViewById(R.id.editTextDescription);
        buttonSubmit = (Button) findViewById(R.id.buttonSUBMIT);

        buttonSubmit.setOnClickListener(this);

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image, menu);
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
        if(v == buttonSubmit){

            loc = location.getText().toString().trim();
            des = description.getText().toString().trim();
            Toast.makeText(this, "des="+des, Toast.LENGTH_LONG).show();


                submit();
        }
    }


    private void submit() {





        submitall(loc, des);
    }

    private void submitall(final String loc, final String des) {
        String urlSuffix = "?loc="+loc+"&des="+des;
        class submit extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Image.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                String k=s.trim();
                Toast.makeText(getApplicationContext(), "\nkKKK"+result, Toast.LENGTH_LONG).show();


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

        submit lu = new submit();
        lu.execute(urlSuffix);
    }













}
