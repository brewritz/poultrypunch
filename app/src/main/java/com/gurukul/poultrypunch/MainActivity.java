package com.gurukul.poultrypunch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.gurukul.poultrypunch.data.DataOfUi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    final static String app_id = "0A6DF43B-EFA2-0188-FFA0-5C9F3711C900";
    final static String app_key = "AE67DCB1-9AE4-98AC-FF6F-ABC5B5A0BD00";
    public static final String SERVER_URL = "https://api.backendless.com";

    public static List<DataOfUi> dataList = new ArrayList<>(); //data of News

    public static List<DataOfUi> articles_dataList = new ArrayList<>(); //data of Articles

    public static List<String> adv_dataList = new ArrayList<>();

    public static List<Map>  allNewsData = new ArrayList<Map>();

    public static List<Map>  allArticlesData = new ArrayList<Map>();

    void initBackendLess(){

        Backendless.setUrl(SERVER_URL);
        Backendless.initApp( getApplicationContext(),app_id,app_key );
    }

    public static int number_of_adv = 0;

    void getAdvertisements() {

        Backendless.Persistence.of("Advertisments").find(new AsyncCallback<List<Map>>() {
            DataOfUi ObjectOfData = null;

            @Override
            public void handleResponse(List<Map> response) {

                number_of_adv = response.size();
                for (int i = 0; i < number_of_adv; i++) {
                    Map item = response.get(i);
                    String adv_url = (String) item.get("adv_url");
                    MainActivity.adv_dataList.add(adv_url);
                }
                Intent it = new Intent(MainActivity.this, startOnBoard.class);
                startActivity(it);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(MainActivity.this, "Error :Please try later", Toast.LENGTH_LONG).show();
                Intent it = new Intent(MainActivity.this, Newslist.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initBackendLess();

        getAdvertisements();

        //Intent it = new Intent(MainActivity.this, Dashboard.class);//Dashboard.class);
       // Intent it = new Intent(MainActivity.this, LoginActivity.class);//Dashboard.class);
        //Intent it = new Intent(MainActivity.this, readFile.class);//Dashboard.class);
       // Intent it = new Intent(MainActivity.this, navigate.class);
       // startActivity(it);
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
}
