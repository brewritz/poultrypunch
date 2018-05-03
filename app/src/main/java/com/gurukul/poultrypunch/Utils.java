package com.gurukul.poultrypunch;

import android.app.Activity;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Ritika on 11/4/2017.
 */
public class Utils {

    public static int themeChangeNumber = 0;

    public static int THEME_GREEN = 0;
    public static int THEME_BLUE = 1;
    public static int THEME_BROWN = 3;
    public static int THEME_ORANGE = 2;

    public void changeTheme(View view) {
        if(themeChangeNumber == THEME_GREEN){
            //setTheme(R.style.AppTheme_Blue);
            themeChangeNumber++;
        }else if(themeChangeNumber == THEME_BLUE){
            //setTheme(R.style.AppTheme_Brown);
            themeChangeNumber++;
        }else if(themeChangeNumber == THEME_BROWN){
            //setTheme(R.style.AppTheme_Orange);
            themeChangeNumber++;
        }else if(themeChangeNumber == THEME_ORANGE){
            //setTheme(R.style.AppTheme);
            themeChangeNumber =0;
        }

    }


    public static void setSelectedTheme(Activity act) {
        if(themeChangeNumber == THEME_GREEN){

            act.setTheme(R.style.AppTheme_Blue);
        }else if(themeChangeNumber == THEME_BLUE){
            act.setTheme(R.style.AppTheme_Brown);

        }else if(themeChangeNumber == THEME_BROWN){
            act.setTheme(R.style.AppTheme_Orange);

        }else if(themeChangeNumber == THEME_ORANGE){
            act.setTheme(R.style.AppTheme_Green);
        }
    }

    private int fetchThemeBackgroundColor(Activity act) {
        TypedValue typedValue = new TypedValue();

        TypedArray a = act.obtainStyledAttributes(typedValue.data, new int[] { R.attr.customBackgroundAttributeColor });
        int color = a.getColor(0, 0);

        a.recycle();

        return color;
    }
}
