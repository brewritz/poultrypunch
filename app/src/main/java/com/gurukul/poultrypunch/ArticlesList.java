package com.gurukul.poultrypunch;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.IDataStore;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.gurukul.poultrypunch.common.CommonAdapter;
import com.gurukul.poultrypunch.data.DataOfUi;
import com.gurukul.poultrypunch.data.getset;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticlesList extends AppCompatActivity {

    private RecyclerView mRecyclerView = null;
    private static List<DataOfUi> dataList = new ArrayList<>();
    private CommonAdapter mAdapter = null;



    void initRes(){
        mRecyclerView = (RecyclerView)findViewById(R.id.artListRV);
    }

    void attachAdapter(){

        mAdapter = new CommonAdapter(ArticlesList.this,MainActivity.articles_dataList,
                  CommonAdapter.LIST_ARTICLES
                , new CommonAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(ArticlesList.this, NewsDetail.class);
                intent.putExtra("SELECTED_ITEM", position);
                intent.putExtra("SELECTED_TYPE", "Articles");
                startActivity(intent);

            }
        }
        );
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setAdapter(mAdapter);

    }

    private static ProgressDialog progress;

    public static void dismissProgressBar(){

        try {
            progress.dismiss();
        }catch(Exception e){

        }
    }

    public static void showProgressBar(Context contx){

        progress=new ProgressDialog(contx);
        progress.setMessage("Getting Data ..");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();

        final int totalProgressTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while(jumpTime < totalProgressTime) {
                    try {
                        sleep(20);
                        jumpTime += 5;
                        if(jumpTime < 80) {
                            progress.setProgress(jumpTime);
                        }
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.articles_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initRes();
        attachAdapter();
        getArticles();
    }

    DataQueryBuilder setWhereClause(IDataStore<Map> storage ,String clause){
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        // set where clause
        queryBuilder.setWhereClause(clause);
        queryBuilder.setPageSize(20);

        return queryBuilder;
    }

    void getArticles(){

        showProgressBar(ArticlesList.this);
            IDataStore<Map> storage = Backendless.Data.of( "articles" );
            DataQueryBuilder queryBuilder = setWhereClause(storage,"Type='Article'");
            //Backendless.Persistence.of( "articles" ).find( new AsyncCallback<List<Map>>(){
            storage.find(queryBuilder, new AsyncCallback<List<Map>>() {

                DataOfUi ObjectOfData = null;

                @Override
                public void handleResponse(List<Map> response) {
dismissProgressBar();
                    int size = response.size();
                    for (int i = 0; i < size; i++) {
                        Map item = response.get(i);
                        MainActivity.allArticlesData.add(item);
                        String title = (String) item.get("Title");
                        String thumbnailurl = (String) item.get("thumbnail_url");
                        String subTitle = (String) item.get("SubTitle");
                        String discription = (String) item.get("para1");

                        int numOfPara = (int) item.get("NumberOfPara");
                        int numOfImages = 1;//(int) item.get("NumberOfImages");

                        ObjectOfData = new getset(title, thumbnailurl, subTitle, discription, numOfPara,
                                numOfImages,"Articles");

                        MainActivity.articles_dataList.add(ObjectOfData);

                    }

                    mAdapter.notifyDataSetChanged();

                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    dismissProgressBar();
                    Toast.makeText(ArticlesList.this, "Error to get article list :Please try later", Toast.LENGTH_LONG).show();
                }
            });
    }

}
