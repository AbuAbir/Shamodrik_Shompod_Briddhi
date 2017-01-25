package com.example.asiff.regsys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseJson extends AppCompatActivity implements View.OnClickListener {


    private String myJSONString;


    private static final String JSON_ARRAY ="result";

    String s;
    private static final String LOCATION = "Location";
    private static final String ILISH= "Ilish";
    private static final String RUI = "Rui";
    private static final String KTLA = "Ktla";
    private static final String PANGASH= "Pangash";


    private JSONArray res = null;
    double aLat,aLong;

    private int TRACK = 0;


    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;

    String Location,Ilish,Ktla,Rui,Pangash;



    Button btnPrev;
    Button btnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_json);

        Bundle bundle = getIntent().getExtras();
        myJSONString  = bundle.getString("s");


        textView=(TextView)findViewById(R.id.textView);
        textView1=(TextView)findViewById(R.id.textView1);
        textView2=(TextView)findViewById(R.id.textView2);
        textView3=(TextView)findViewById(R.id.textView3);
        textView4=(TextView)findViewById(R.id.textView4);
        // textView5=(TextView)findViewById(R.id.textView5);

        btnPrev = (Button) findViewById(R.id.buttonPrev);
        btnNext = (Button) findViewById(R.id.buttonNext);


        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);


        extractJSON();

        showData();





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_parse_json, menu);
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




    private void extractJSON(){
        try {
            JSONObject jsonObject = new JSONObject(myJSONString);
            res = jsonObject.optJSONArray("result");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void moveNext(){
        if(TRACK<res.length()){
            TRACK++;
        }
        showData();
    }

    private void movePrev(){
        if(TRACK>0){
            TRACK--;
        }
        showData();
    }

    private void showData(){
        try {



            JSONObject jsonObject = res.getJSONObject(TRACK);

            // editTextLname.setText(jsonObject.optString(NAME));
            //editTextLcity.setText(jsonObject.optString(CITY));


            Location=jsonObject.optString(LOCATION);

            Ilish=jsonObject.optString(ILISH);
            Rui=jsonObject.optString(RUI);
            Ktla=jsonObject.optString(KTLA);
            Pangash=jsonObject.optString(PANGASH);




            textView.setText("Location: "+Location);
            textView1.setText("Ilish Fish: "+Ilish);
            textView2.setText("Riu Fish: "+Rui);
            textView3.setText("Katla Fish: "+Ktla);
            textView4.setText("Pangash Fish: "+Pangash);








           /* editTextLcity.setText(jsonObject.getString(CITY));
            editTextLrem.setText(jsonObject.getString(REM));
            editTextLLongi.setText((int) jsonObject.getDouble(LONGI));
            editTextLLati.setText((int) jsonObject.getDouble(LATI));
            editTextLid.setText(jsonObject.getInt(ID));

*/

            // s=jsonObject.optString("Lname").toString();
            // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




    @Override
    public void onClick(View v) {
        if(v == btnNext){

            moveNext();
        }
      else  if(v == btnPrev){

            movePrev();
        }

    }




}
