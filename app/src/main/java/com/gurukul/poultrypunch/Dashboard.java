package com.gurukul.poultrypunch;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

public class Dashboard extends AppCompatActivity {
    CircleMenu circleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.mipmap.icon_home, R.mipmap.icon_cancel);

        circleMenu.addSubMenu(Color.parseColor("#258CFF"), R.drawable.newshens)
                .addSubMenu(Color.parseColor("#30A400"), R.drawable.articlehens)
                .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.eventshens)
                .addSubMenu(Color.parseColor("#8A39FF"), R.drawable.editormessage)
                .addSubMenu(Color.parseColor("#FF6A00"), R.drawable.contactushens)
                .addSubMenu(Color.parseColor("#FF1A00"), R.drawable.aboutushens);


        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {

                                                 @Override
                                                 public void onMenuSelected(int index) {
                                                     switch (index) {
                                                         case 0:
                                                             openWebView(OPEN_NEWS);
                                                             break;
                                                         case 1:
                                                             openWebView(OPEN_ARTICLE);
                                                             break;
                                                         case 2:
                                                             openWebView(OPEN_EVENTS);
                                                             break;
                                                         case 3:
                                                             openWebView(OPEN_EDITOR_MESSGAE);
                                                            // startActivity(new Intent(Dashboard.this, ThankYouActivity.class));
                                                             break;
                                                         case 4:
                                                             openWebView(OPEN_ARTICLE);
                                                             break;
                                                         case 5:
                                                             openWebView(OPEN_ABOUTUS);
                                                             break;
                                                         case 6:
                                                             openWebView(OPEN_CONTACTUS);
                                                             break;
                                                     }
                                                 }
                                             }

        );

        circleMenu.setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

                                                     @Override
                                                     public void onMenuOpened() {

                                                     }

                                                     @Override
                                                     public void onMenuClosed() {

                                                     }
                                                 }
        );
    }

    @Override
    public void onBackPressed() {
        if (circleMenu.isOpened())
            circleMenu.closeMenu();
        else
            finish();
    }

    final int OPEN_NEWS = 0;
    final  int OPEN_ARTICLE = 1;
    final int OPEN_EDITOR_MESSGAE = 3;
    final  int OPEN_EVENTS = 2;
    final int OPEN_ABOUTUS = 4;
    final int OPEN_CONTACTUS = 5;

    void openWebView(int pageType){

        WebView webview = new WebView(this);
        setContentView(webview);

        switch(pageType){

            case OPEN_NEWS:
                // Simplest usage: note that an exception will NOT be thrown
                // if there is an error loading this page (see below).
                webview.loadUrl("http://www.poultrypunch.com/dist/news");

                break;

            case OPEN_ARTICLE:
                // Simplest usage: note that an exception will NOT be thrown
                // if there is an error loading this page (see below).
                webview.loadUrl("http://www.poultrypunch.com/dist");

                break;
            case OPEN_EDITOR_MESSGAE:
                // Simplest usage: note that an exception will NOT be thrown
                // if there is an error loading this page (see below).
                webview.loadUrl("http://www.poultrypunch.com/dist/");

                break;
            case OPEN_EVENTS:
                // Simplest usage: note that an exception will NOT be thrown
                // if there is an error loading this page (see below).
                webview.loadUrl("http://www.poultrypunch.com/dist/");

                break;
            case OPEN_ABOUTUS:
                // Simplest usage: note that an exception will NOT be thrown
                // if there is an error loading this page (see below).
                webview.loadUrl("http://www.poultrypunch.com/dist/");

                break;
            case OPEN_CONTACTUS:
                // Simplest usage: note that an exception will NOT be thrown
                // if there is an error loading this page (see below).
                webview.loadUrl("http://www.poultrypunch.com/dist/");

                break;

        }

    }

    public void changeThemeDashBoard(View view) {

        setTheme(R.style.AppTheme_Blue);

    }
}


