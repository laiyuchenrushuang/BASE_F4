package demo.hc.com.f4;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import butterknife.ButterKnife;

/**
 * Created by ly on 2019/5/23.
 */

public class Myapplication extends Application {
    static Myapplication myapplication;
    private static Context mContext;

    public static Myapplication getIncatence(){
        if (myapplication == null){
            myapplication = new Myapplication();
        }
        return myapplication;
    }
    public static Context getContext(){
        return mContext;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Log.d("lylog","Myapplication");
    }
}
