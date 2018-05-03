package com.gurukul.poultrypunch;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.gurukul.poultrypunch.volley.volleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ritika on 11/20/2017.
 */
public class slideShowAdapter  extends PagerAdapter {

    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;
    List<String> advertisement_imagesUrl;

    private ImageLoader imageLoader;

    public slideShowAdapter(Context context,List<String> advertisement_imagesUrl){
        this.context = context;
        this.advertisement_imagesUrl = advertisement_imagesUrl;
        imageLoader = volleySingleton.getInstance(context).getImageLoader();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int len = advertisement_imagesUrl.size();
        return len;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.slide, view, false);
        NetworkImageView myImage = (NetworkImageView) myImageLayout
                .findViewById(R.id.slideShow);
        //myImage.setImageResource(images.get(position));

        myImage.setImageUrl(advertisement_imagesUrl.get(position), imageLoader);
        myImage.setDefaultImageResId(R.drawable.newshen);
        myImage.setErrorImageResId(R.drawable.newshen);
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }
}
