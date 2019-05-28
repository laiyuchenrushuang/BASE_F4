package demo.hc.com.f4;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.bt_1)
    Button bt_gb;
    @BindView(R.id.bt_2)
    Button bt_gbo;
    @BindView(R.id.bt_3)
    Button bt_intent;
    @BindView(R.id.bt_4)
    Button bt_service;

    //private MyReceiver myReceiver; 想静态注册

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //myReceiver = new MyReceiver();
        initEvent();
        registerbroatcast();
    }

    private void registerbroatcast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(APPUtil.LYGB_ACTION);
        registerReceiver(mBroadcastReceiver, filter);

        //registerReceiver(myReceiver,filter,PERMISSION_OWNER,null);//handler?


//        MyReceiver myReceiver = new MyReceiver();
//        IntentFilter filtermy = new IntentFilter();
//        filtermy.addAction(APPUtil.LYGB_ACTION_OWNER);
//        registerReceiver(myReceiver, filtermy,APPUtil.P_LYGB_RECEIVE_OWNER,null);  //静态注册 注意Category一致
    }

    private void initEvent() {
        bt_gb.setOnClickListener(this);
        bt_gbo.setOnClickListener(this);
        bt_intent.setOnClickListener(this);
        bt_service.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_1://GB_normal
                sendGB();
                break;
            case R.id.bt_2:
                sendGBowner();//GB_owner
                break;
            case R.id.bt_3://INTENT
                intentSystemUI();
                break;
            case R.id.bt_4://service
                startservrice();
                break;
        }
    }

    private void startservrice() {
        Intent intent = new Intent(this,MyService.class);
        intent.setAction("myservice");
        Bundle bundle  = new Bundle();
        bundle.putInt("lyc", 219);
        intent.putExtras(bundle);
        startService(intent);
    }

    private void intentSystemUI() {
        //https://blog.csdn.net/w690333243/article/details/78082176  面很多指令
        //指令查询  1.adb shell am start -n demo.hc.com.f4/demo.hc.com.f4.MainActivity
        //         2.查看当前的activity ： adb shell dumpsys activity | grep -i run
        Intent intent = new Intent();
        String pcgName = null;
        String clsName = null;
        Log.d("lylog","  BAND = "+android.os.Build.BRAND);
        if (android.os.Build.BRAND.equals("HUAWEI") || android.os.Build.BRAND.equals("HONOR")) {
            pcgName = "com.android.settings";
//        String clsName = "com.android.settings.Settings$SecuritySettingsActivity"; // skip OK
            clsName = "com.android.settings.fingerprint.FingerprintSettingsActivity";


        ComponentName componentName = new ComponentName(pcgName, clsName);
        intent.setComponent(componentName);
        intent.setAction("android.intent.action.VIEW");
        startActivity(intent);
        }
    }

    private void sendGBowner() {
        Intent intent = new Intent(APPUtil.LYGB_ACTION_OWNER);
        intent.addCategory("receiver");//静态文件 注意Category一致
        intent.putExtra("message", "haha");
        sendOrderedBroadcast(intent, APPUtil.P_LYGB_RECEIVE_OWNER);
    }

    private void sendGB() {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(APPUtil.LYGB_ACTION);
        this.sendBroadcast(intent);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) //onReceive函数不能做耗时的事情，参考值：10s以内
        {
            String action = intent.getAction();
            if (action.equals(APPUtil.LYGB_ACTION)) {
                showToast("GB receive");
            }
        }
    };
}
