package com.gurukul.poultrypunch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.webkit.URLUtil;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.gurukul.poultrypunch.data.getset;
import com.gurukul.poultrypunch.volley.volleySingleton;

import java.util.Map;

public class NewsDetail extends AppCompatActivity {

    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

     //   TextView toolTv = (TextView)findViewById(R.id.titleNews);

        int position = getIntent().getIntExtra("SELECTED_ITEM",0);
        String typeOfData = getIntent().getStringExtra("SELECTED_TYPE");

        getset obj;

        Map dataFromList;


        if(typeOfData.equals("News")) {
            dataFromList  = MainActivity.allNewsData.get(position);
            obj = (getset)MainActivity.dataList.get(position);
        }else{
            dataFromList  = MainActivity.allArticlesData.get(position);
            obj = (getset)MainActivity.articles_dataList.get(position);
        }

        TextView titleTv = (TextView)findViewById(R.id.newsDetailHeader);
        String title = obj.getTitle();
        if(title != null) {
            titleTv.setText(title);
        }

        TextView subHeaderTv = (TextView)findViewById(R.id.subHeaderNews);
        String subHeader = obj.getSubTitle();
        if(subHeader != null) {
            subHeaderTv.setText(subHeader);
        }

        LinearLayout rl =  (LinearLayout)findViewById(R.id.relView);

        int numberOfPara = obj.getNumOfPara();

        if(numberOfPara > 0){

            imageLoader =  volleySingleton.getInstance(this).getImageLoader();

            try{
            for(int i =0 ;i < numberOfPara;i++){
                int pos = i+1;
                String suffix = Integer.toString(pos);
                String para = "para";
                String fetchPara = para.concat(suffix);

                String valueOfPara = (String) dataFromList.get(fetchPara);
                boolean isUrl = URLUtil.isValidUrl(valueOfPara);

                if(isUrl == true){
                    NetworkImageView networkImageView = new NetworkImageView(this);
                    networkImageView.setImageUrl(valueOfPara, imageLoader);
                    networkImageView.setDefaultImageResId(R.drawable.newsedited);
                    networkImageView.setErrorImageResId(R.drawable.newsedited);
                    rl.addView(networkImageView);
                }else{
                     TextView tv = new TextView(this);
                     tv.setText('\n' + valueOfPara + '\n');
                     tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,21);
                     rl.addView(tv);

                    NetworkImageView networkImageView = new NetworkImageView(this);

                    String urlAdv = obj.getAdvUrl();

                    if(urlAdv != null && URLUtil.isValidUrl(urlAdv) == true) {

                        networkImageView.setImageUrl(urlAdv, imageLoader);

                        networkImageView.setDefaultImageResId(R.drawable.newsedited);
                        networkImageView.setErrorImageResId(R.drawable.newsedited);
                        rl.addView(networkImageView);
                    }
                }
            }

        }catch (Exception e){
            }
        }
    }
}
