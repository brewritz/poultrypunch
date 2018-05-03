package com.gurukul.poultrypunch.data;

import com.gurukul.poultrypunch.MainActivity;

/**
 * Created by Ritika on 11/11/2017.
 */
public class getset extends DataOfUi{

     private String title,tumbnailUrl,subTitle,discription;
     int numOfImages = 0,numOfPara = 1;
     String type ;

   public getset(String title,String tumbnailUrl,String subTitle,String discription,int numOfPara
           ,int numOfImages , String type){

      this.title = title;
       this.tumbnailUrl = tumbnailUrl;
       this.subTitle = subTitle ;
       this.discription = discription;
       this.numOfImages = numOfImages;
       this.numOfPara = numOfPara;
       this.type = type;
  }

   public void setTypeOfData (String type){
        this.type = type;
    }

    public String getTypeOfData (){
        return type;
    }

   public void setNumOfImages (int num){
       numOfImages = num;
   }

   public void setNumOfPara (int num){
       numOfPara = num;
    }

    public int getNumOfImages (){
        return numOfImages;
    }

    public int getNumOfPara (){
        return numOfPara;
    }

    public  void setTitle(String title){
         this.title = title;
     }

    public   void setSubTitle(String subtitle){
        this.subTitle = subtitle;
    }

    public  void setTumbnailUrl(String tumbnailUrl){
        this.tumbnailUrl = tumbnailUrl;
    }

    public  void setDiscription(String discription){
        this.discription = discription;
    }

    public  String getTitle(){
        return this.title;
    }

    public  String getSubTitle(){
        return this.subTitle;
    }

    public  String getTumbnailUrl(){
        return this.tumbnailUrl;
    }

    public  String getDiscription(){
        return this.discription;
    }

    static int presentAdv = 0;
    public static String getAdvUrl(){

        String url = null;
        if(MainActivity.number_of_adv-1 >= presentAdv){
            presentAdv++;
            url = MainActivity.adv_dataList.get(presentAdv);

        }else{
            url = MainActivity.adv_dataList.get(0);
            presentAdv = 0;
        }

        return url;
    }

    public static String getAdvUrl(int id){

        String url = null;
       try {
            url = MainActivity.adv_dataList.get(id);
       }catch (Exception e){

       }
        return url;
    }

    @Override
    public String getFilterableObject(int screenName) {
        return null;
    }
}
