package com.example.asiff.regsys;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Marketing extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextIlish;
    private EditText editTextRui;
    private EditText editTextKtla;
    private EditText editTextPangash;

    private EditText KeditTextIlish;
    private EditText KeditTextRui;
    private EditText KeditTextKtla;
    private EditText KeditTextPangash;

    private EditText CeditTextIlish;
    private EditText CeditTextRui;
    private EditText CeditTextKtla;
    private EditText CeditTextPangash;
    String result;
    private static final String REGISTER_URL = "http://shayokhasif.netne.net/Update.php";

    String Ilish;
    String Rui;
    String Ktla;
    String Pangash;

    String KIlish;
    String KRui;
    String KKtla;
    String KPangash;

    String CIlish;
    String CRui;
    String CKtla;
    String CPangash;


    private Button buttonUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketing);

        editTextIlish = (EditText) findViewById(R.id.editTextIlish);
        editTextRui = (EditText) findViewById(R.id.editTextRui);
        editTextKtla = (EditText) findViewById(R.id.editTextKtla);
        editTextPangash = (EditText) findViewById(R.id.editTextPangash);


        KeditTextIlish = (EditText) findViewById(R.id.KeditTextIlish);
        KeditTextRui = (EditText) findViewById(R.id.KeditTextRui);
        KeditTextKtla = (EditText) findViewById(R.id.KeditTextKtla);
        KeditTextPangash = (EditText) findViewById(R.id.KeditTextPangash);

        CeditTextIlish = (EditText) findViewById(R.id.CeditTextIlish);
        CeditTextRui = (EditText) findViewById(R.id.CeditTextRui);
        CeditTextKtla = (EditText) findViewById(R.id.CeditTextKtla);
        CeditTextPangash = (EditText) findViewById(R.id.CeditTextPangash);


        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);

        buttonUpdate.setOnClickListener(this);


         setTitle("বাজারের অবস্থা ");

    }


    public void onClick(View v) {
        if(v == buttonUpdate){
            UpdatePrice();
        }
    }

    private void UpdatePrice() {
         Ilish = editTextIlish.getText().toString();
         Rui = editTextRui.getText().toString().trim();
        Ktla = editTextKtla.getText().toString().trim();
        Pangash =  editTextPangash.getText().toString().trim();


        KIlish = KeditTextIlish.getText().toString();
        KRui = KeditTextRui.getText().toString().trim();
        KKtla = KeditTextKtla.getText().toString().trim();
        KPangash =  KeditTextPangash.getText().toString().trim();


        CIlish = CeditTextIlish.getText().toString();
        CRui = CeditTextRui.getText().toString().trim();
        CKtla = CeditTextKtla.getText().toString().trim();
        CPangash =  CeditTextPangash.getText().toString().trim();





        update(Ilish, Rui, Ktla, Pangash, KIlish,KRui,KKtla,KPangash,CIlish,CRui,CKtla,CPangash);
    }


    private void update(String Ilish,String Rui,String Ktla,String Pangash,String KIlish,String KRui,String KKtla,String KPangash ,String CIlish,String CRui,String CKtla,String CPangash) {
        //Toast.makeText(getApplicationContext(), "Ilish=" + Ilish, Toast.LENGTH_LONG).show();
        String urlSuffix = "?Ilish="+Ilish+"&Rui="+Rui+"&Ktla="+Ktla+"&Pangash="+Pangash+"&KIlish="+KIlish+"&KRui="+KRui+"&KKtla="+KKtla+"&KPangash="+KPangash+"&CIlish="+CIlish+"&CRui="+CRui+"&CKtla="+CKtla+"&CPangash="+CPangash;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Marketing.this, "Please Wait", null, true, true);
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
