package com.example.asiff.regsys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextLogVasselNum;
    private EditText editTextLogPassword;
    private EditText editTextLogNid;
    private Button buttonLogins;
    String vasselnum,nid,password;
    String result;
    private static final String REGISTER_URL = "http://shayokhasif.netne.net/Login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         setTitle("লগ ইন");

        editTextLogVasselNum = (EditText) findViewById(R.id.editTextLogVasselNum);
        editTextLogPassword = (EditText) findViewById(R.id.editTextLogPassword);
        editTextLogNid = (EditText) findViewById(R.id.editTextLogNid);


        buttonLogins = (Button) findViewById(R.id.buttonLogin);

        buttonLogins.setOnClickListener(this);



    }





    public void onClick(View v) {
        if(v == buttonLogins){

            vasselnum = editTextLogVasselNum.getText().toString().trim();
            password = editTextLogPassword.getText().toString().trim();
            nid = editTextLogNid.getText().toString().trim();
            boolean correct = "admin".equals(password);
            if(correct)
            {
                Intent intent = new Intent(Login.this, Admin.class);
                startActivity(intent);
                finish();

            }
            else

            loginUser();
        }
    }


    private void loginUser() {





        login( nid,vasselnum,password);
    }

    private void login(final String nid, final String vasselnum, final String password) {
        String urlSuffix = "?nid="+nid+"&vasselnum="+vasselnum+"&password="+password;
        class loginUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Login.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                String k=s.trim();
             //   Toast.makeText(getApplicationContext(), "\nkKKK"+result, Toast.LENGTH_LONG).show();
                boolean correct = "success".equals(k);

                if(correct)
                {



                    Intent intent = new Intent(Login.this, AfterLogin.class);
                    intent.putExtra("PASS",password);
                    intent.putExtra("VASS",vasselnum);
                    intent.putExtra("NID",nid);
                  //  intent.putExtra(USER_NAME, username);
                    finish();
                    startActivity(intent);
                }else {
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

        loginUser lu = new loginUser();
        lu.execute(urlSuffix);
    }








}
