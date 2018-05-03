package com.gurukul.poultrypunch.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.gurukul.poultrypunch.R;
import com.gurukul.poultrypunch.data.DataOfUi;
import com.gurukul.poultrypunch.data.getset;
import com.gurukul.poultrypunch.volley.volleySingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ritika on 2/10/2017.
 */
public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.PoultryPunchViewHolder> implements Filterable {

    List<DataOfUi> mListOfData = null;
    List<DataOfUi> mListOfDataOrginal = null;
    private static final String TAG = "CommonAdapter";
    int mScrnNumber = -1;

    public static final int LIST_NEWS = 100;
    public static final int LIST_ARTICLES = 101;
    public static final int LIST_EVENTS = 102;

    public static final int ON_BOARD = 103;

    public static final int SINGLE_STUDENT_REPORTCARD_DETAIL = 0;
    public static final int TEACHER_VIEW_REPORTCARD = 1;
    public static final int CREATE_REPORT_CARD = 2;
    public static final int NB_STATICS = 3;
    public static final int NB_LIST = 4;
    public static final int SCHOOL_GALLERY = 5;
    public static final int FEES_DETAILS = 6;
    public static final int FEES_PENDING = 7;
    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    @Override
    public PoultryPunchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        if(mScrnNumber == ON_BOARD){
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.onboard, parent, false);
        }else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_layout, parent, false);
        }
        return new PoultryPunchViewHolder(itemView);
    }
int row_index = -1;

    @Override
    public void onBindViewHolder(PoultryPunchViewHolder holder,final int position) {

        holder.row_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index=position;

                int absPost = 0;
                try {

                    int orgSize = mListOfDataOrginal.size();
                    int newSize = mListOfData.size();
                    if (orgSize != newSize){
                        for (DataOfUi item : mListOfDataOrginal) {
                            if (item.equals(mListOfData.get(position))) {
                                break;
                            }
                            absPost++;
                        }
                    }else{
                        absPost = position;
                    }
                }catch (Exception e){
                    absPost = position;
                    Log.d(TAG, "absPost error");
                }finally {
                    mOnItemClickListener.onItemClick(view,absPost);
                }
             //    notifyDataSetChanged();
            }
        });

//        if(row_index==position){
//            holder.row_view.setBackgroundColor(Color.parseColor("#567845"));
//        } else
//        {
//            holder.row_view.setBackgroundColor(Color.parseColor("#ffffff"));
//        }

        if(mScrnNumber == LIST_NEWS){

            getset fdata = (getset) mListOfData.get(position);
            holder.titlePoultry.setText(fdata.getTitle());
            String subTitle = fdata.getSubTitle();
            if(subTitle != null) {
                holder.subHeadingPoultry.setText(subTitle);
            }

            holder.imageLoader = volleySingleton.getInstance(context).getImageLoader();
            String thumbnail_url = fdata.getTumbnailUrl();
            if(thumbnail_url  != null) {

                holder.imageLoader.get(thumbnail_url,
                        ImageLoader.getImageListener(holder.thumbnailImage, R.drawable.newshen, R.drawable.newshen));
                //Setting the image url to load
                holder.thumbnailImage.setImageUrl(fdata.getTumbnailUrl(), holder.imageLoader);
            }

            holder.thumbnailImage.setDefaultImageResId(R.drawable.newshen);
            holder.thumbnailImage.setErrorImageResId(R.drawable.newshen);
            {
                holder.advImageView.setVisibility(View.VISIBLE);

                String adv_url = getset.getAdvUrl(position);

                holder.imageLoader_adv = volleySingleton.getInstance(context).getImageLoader();
                if(adv_url != null) {

                    holder.imageLoader_adv.get(adv_url,
                            ImageLoader.getImageListener(holder.advImageView, R.drawable.newsedited, R.drawable.newsedited));
                    //Setting the image url to load
                    holder.advImageView.setImageUrl(adv_url, holder.imageLoader_adv);
                }
                holder.advImageView.setDefaultImageResId(R.drawable.newsedited);
                holder.advImageView.setErrorImageResId(R.drawable.newsedited);
            }
        }else if(mScrnNumber == LIST_ARTICLES){

            getset fdata = (getset) mListOfData.get(position);
            holder.titlePoultry.setText(fdata.getTitle());
            String subTitle = fdata.getSubTitle();
            if(subTitle != null) {
                holder.subHeadingPoultry.setText(subTitle);
            }

            holder.imageLoader = volleySingleton.getInstance(context).getImageLoader();
            String thumbnail_url = fdata.getTumbnailUrl();
            if(thumbnail_url  != null) {

                holder.imageLoader.get(thumbnail_url,
                        ImageLoader.getImageListener(holder.networkImageView, R.drawable.newshen, R.drawable.newshen));
                //Setting the image url to load
                holder.thumbnailImage.setImageUrl(fdata.getTumbnailUrl(), holder.imageLoader);
            }

            holder.thumbnailImage.setDefaultImageResId(R.drawable.newshen);
            holder.thumbnailImage.setErrorImageResId(R.drawable.newshen);
            {
                holder.advImageView.setVisibility(View.VISIBLE);

                String adv_url = getset.getAdvUrl(position);

                holder.imageLoader_adv = volleySingleton.getInstance(context).getImageLoader();
                if(adv_url != null) {

                    holder.imageLoader_adv.get(adv_url,
                            ImageLoader.getImageListener(holder.networkImageView, R.drawable.newsedited, R.drawable.newsedited));
                    //Setting the image url to load
                    holder.advImageView.setImageUrl(adv_url, holder.imageLoader_adv);
                }
                holder.advImageView.setDefaultImageResId(R.drawable.newsedited);
                holder.advImageView.setErrorImageResId(R.drawable.newsedited);
            }

        }else if(mScrnNumber == LIST_EVENTS){

        }else if(mScrnNumber == ON_BOARD){

            holder.onBoardImageView.setVisibility(View.VISIBLE);
            String onboard_url = getset.getAdvUrl(position);
            holder.imageLoader_onboard = volleySingleton.getInstance(context).getImageLoader();
            if(onboard_url != null) {

                holder.imageLoader_onboard.get(onboard_url,
                        ImageLoader.getImageListener(holder.networkImageView, R.drawable.newshen, R.drawable.newshen));
                //Setting the image url to load
                holder.onBoardImageView.setImageUrl(onboard_url, holder.imageLoader_onboard);
                holder.onBoardImageView.setDefaultImageResId(R.drawable.newshen);
                holder.onBoardImageView.setErrorImageResId(R.drawable.newshen);
            }

        }

/*
        if(mScrnNumber == SINGLE_STUDENT_REPORTCARD_DETAIL) {
            ReportCardModel data = (ReportCardModel) mListOfData.get(position);
            holder.subject.setText(data.getSubject());
            holder.marksObtained.setText(data.getMarksObtained());
            holder.total.setText(data.getTotal());
            holder.percentage.setText(data.getPercentage());
        }else if(mScrnNumber == TEACHER_VIEW_REPORTCARD) {
            ReportCardModel data = (ReportCardModel) mListOfData.get(position);
            holder.name.setText(data.getName());
            int rollNumber = data.getRollNumber();
            holder.rollnumber.setText(Integer.toString(rollNumber));
        }else if(mScrnNumber == CREATE_REPORT_CARD){
            ReportCardModel data = (ReportCardModel) mListOfData.get(position);
            holder.subject.setText(data.getSubject());
            holder.marksObtained.setText(data.getMarksObtained());
            holder.total.setText(data.getTotal());
        }else if(mScrnNumber == NB_STATICS){
            NoticeBoardModel data = (NoticeBoardModel) mListOfData.get(position);
            holder.nbName.setText(data.getStatsName());
            holder.nbRollNum.setText(data.getStatsRolNum());
            holder.nbClass.setText(data.getStatsClass());
            holder.nbResponse.setText(data.getStatsResponse());
        }else if(mScrnNumber == NB_LIST){
            NoticeBoardModel data = (NoticeBoardModel) mListOfData.get(position);
            holder.nbTitle.setText(data.getListTitle());
            holder.nbDiscriptioin.setText(data.getListDiscription());

        }else if(mScrnNumber == SCHOOL_GALLERY){
            schoolGalleryModel data = (schoolGalleryModel)mListOfData.get(position);
           // holder.sg_title.setText(data.getImage_title());
            holder.sg_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder.sg_img.setImageResource((data.getImage_ID()));
        }else if(mScrnNumber == FEES_DETAILS){
            FeesListModel data = (FeesListModel)mListOfData.get(position);
            holder.feesName.setText(data.getName());
            holder.feesStudentRegId.setText(data.getRegId());


        }else if(mScrnNumber == FEES_PENDING){
            FeesPendingModel data = (FeesPendingModel)mListOfData.get(position);
            holder.feesPendingName.setText(data.getName());
            holder.feesPendingRollNum.setText(data.getRollNumber());
            holder.feesPendingClass.setText(data.getClassName());
        }
*/

    }

    @Override
    public int getItemCount() {

        int size = mListOfData.size();
        return size;
    }

    private Context context;
    public CommonAdapter(Context context, List<DataOfUi> listOfData, int scrnNumber, OnItemClickListener onItemClickListener){
        mListOfData = listOfData;
        mListOfDataOrginal = listOfData;
        mScrnNumber = scrnNumber;
        mOnItemClickListener = onItemClickListener;

        this.context = context;

    }


    @Override
    public Filter getFilter() {
    return null;
    }

    protected List<DataOfUi> getFilteredResults(String constraint) {
        List<DataOfUi> results = new ArrayList<>();
        for (DataOfUi item : mListOfDataOrginal) {
            if (item.getFilterableObject(mScrnNumber).toLowerCase().contains(constraint)) {
                results.add(item);
            }
        }
        if(results.size() <=0){
            return mListOfDataOrginal;
        }

        return results;
    }


    public class PoultryPunchViewHolder extends RecyclerView.ViewHolder {// implements View.OnClickListener, View.OnLongClickListener {


        public TextView subject,marksObtained , total,percentage;
        public TextView name,rollnumber;
        public TextView nbName,nbRollNum,nbClass,nbResponse;
        public TextView nbTitle,nbDiscriptioin;
        private TextView sg_title;
        private ImageView sg_img;

        public TextView feesName,feesStudentRegId;

        public TextView feesPendingName,feesPendingRollNum,feesPendingClass;

        public TextView titlePoultry,subHeadingPoultry;
        NetworkImageView thumbnailImage;
        NetworkImageView networkImageView;

        NetworkImageView advImageView;
        private ImageLoader imageLoader,imageLoader_adv,imageLoader_onboard;

        NetworkImageView onBoardImageView;

        public View row_view;

        public PoultryPunchViewHolder(View view) {
            super(view);

           //view.setOnClickListener(this);
           //view.setOnLongClickListener(this);

            row_view = view;

            thumbnailImage = (NetworkImageView)view.findViewById(R.id.thumbnail);
            titlePoultry = (TextView)view.findViewById(R.id.TitlePoultry);
            subHeadingPoultry = (TextView)view.findViewById(R.id.subHeadingPoultry);
            advImageView =  (NetworkImageView)view.findViewById(R.id.advertisment);
            onBoardImageView =  (NetworkImageView)view.findViewById(R.id.onBoardImageView);

            networkImageView = new NetworkImageView(context);
            imageLoader = volleySingleton.getInstance(context).getImageLoader();
            imageLoader_adv =  volleySingleton.getInstance(context).getImageLoader();

                    }
    }
}
