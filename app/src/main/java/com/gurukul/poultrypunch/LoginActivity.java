package com.gurukul.poultrypunch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setSelectedTheme(this);
        setContentView(R.layout.login);
    }

    public void changeTheme(View view) {
        if(Utils.themeChangeNumber == Utils.THEME_GREEN){
            //setTheme(R.style.AppTheme_Blue);
            Utils.themeChangeNumber++;
        }else if(Utils.themeChangeNumber == Utils.THEME_BLUE){
            //setTheme(R.style.AppTheme_Brown);
            Utils.themeChangeNumber++;
        }else if(Utils.themeChangeNumber == Utils.THEME_BROWN){
            //setTheme(R.style.AppTheme_Orange);
            Utils.themeChangeNumber++;
        }else if(Utils.themeChangeNumber == Utils.THEME_ORANGE){
            //setTheme(R.style.AppTheme);
            Utils.themeChangeNumber =0;
        }
        recreate();
    }

    public void openSignUp(View view) {

        Intent signUp = new Intent(LoginActivity.this,SignUp.class);
        startActivity(signUp);

    }
}
