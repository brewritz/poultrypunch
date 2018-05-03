package com.gurukul.poultrypunch;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class readFile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_file);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button button = (Button) findViewById(R.id.bPressMe);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              String pathr = "android.resource://" + getPackageName() + "/" + "raw/article5";

                Uri video = Uri.parse(pathr);
              //  String pathr = "file:///android_asset/article5";
          //   File file = new File(video.toString());//"raw/article5.rtf");//Environment.getExternalStorageDirectory(),
                  //"Help Desk Voice Flow.doc");

              try {
             // if (file.exists())
              {
              Uri path = Uri.parse(pathr); ;//Uri.fromFile(file);
              Intent objIntent = new Intent(Intent.ACTION_VIEW);
              // replace "application/msword" with
              // "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
              // for docx files
              // objIntent.setDataAndType(path,"application/msword");
              objIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
              startActivity(objIntent);
              }
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(readFile.this,
                            "No Viewer Application Found", Toast.LENGTH_SHORT)
                            .show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }

    String asInputStream(){

        InputStream inputStream = getResources().openRawResource(R.raw.article5);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            int i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString();



    }

}
