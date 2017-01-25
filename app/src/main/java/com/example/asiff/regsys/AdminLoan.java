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

public class AdminLoan extends AppCompatActivity  implements View.OnClickListener {

    private EditText editTextLA;
    private EditText editTextNIDL;

    private Button buttonDone;
    String NID,Lamount;
    String result;
    private static final String REGISTER_URL = "http://shayokhasif.netne.net/LoanDone.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_loan);

        editTextNIDL = (EditText) findViewById(R.id.editTextNIDL);
        editTextLA = (EditText) findViewById(R.id.editTextLA);
       // editTextLogNid = (EditText) findViewById(R.id.editTextLogNid);


        buttonDone = (Button) findViewById(R.id.buttonDoneLoan);

        buttonDone.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin_loan, menu);
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
        if(v == buttonDone){

           NID = editTextNIDL.getText().toString().trim();
            Lamount= editTextLA.getText().toString().trim();
            //nid = editTextLogNid.getText().toString().trim();


                loandone();
        }
    }


    private void loandone() {





        ld(NID, Lamount);
    }

    private void ld(String NID,String Lamount ) {
        String urlSuffix = "?NID="+NID+"&Lamount="+Lamount;
        class loandone extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AdminLoan.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                String k=s.trim();
                Toast.makeText(getApplicationContext(), "\nkKKK"+result, Toast.LENGTH_LONG).show();
                boolean correct = "success".equals(k);

                if(correct)
                {




                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Invalid User Name or Password", Toast.LENGTH_LONG).show();
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

        loandone ldu = new loandone();
        ldu.execute(urlSuffix);
    }










}
