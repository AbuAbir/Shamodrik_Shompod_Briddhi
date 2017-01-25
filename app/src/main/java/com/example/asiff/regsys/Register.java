package com.example.asiff.regsys;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextVasselNum;
    private EditText editTextPassword;
    private EditText editTextNid;
    private EditText editTextVil;
    private EditText editTextThana;
    private EditText editTextDist;
    String result;
    private Button buttonRegister;

    private static final String REGISTER_URL = "http://shayokhasif.netne.net/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("নিবন্ধন ");

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextVasselNum = (EditText) findViewById(R.id.editTextVasselNum);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextNid = (EditText) findViewById(R.id.editTextNid);
        editTextDist=(EditText)findViewById(R.id.editTextDistrict);
        editTextVil=(EditText)findViewById(R.id.editTextVillage);
        editTextThana=(EditText)findViewById(R.id.editTextThana);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(this);

        Typeface font = Typeface.createFromAsset(getAssets(),  "Siyamrupali_1_01.ttf" );
        editTextName.setTypeface(font);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

    @Override
    public void onClick(View v) {
        if(v == buttonRegister){
            registerUser();
        }
    }


    private void registerUser() {
        String name = editTextName.getText().toString();
        String vasselnum = editTextVasselNum.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String nid = editTextNid.getText().toString().trim();
        String vil = editTextVil.getText().toString().trim();
        String thana = editTextThana.getText().toString().trim();
        String dist = editTextDist.getText().toString().trim();




        register(name,password,dist,thana,vil,vasselnum,nid);
    }

    private void register(String name, String password,String dist,String thana,String vil,String vasselnum ,String nid) {
        //Toast.makeText(getApplicationContext(),"name="+ name, Toast.LENGTH_LONG).show();
        String urlSuffix = "?name="+name+"&password="+password+"&dist="+dist+"&thana="+thana+"&vil="+vil+"&vasselnum="+vasselnum+"&nid="+nid;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Register.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
               // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();



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


        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);
    }


}
