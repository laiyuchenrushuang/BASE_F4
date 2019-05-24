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

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.bt_1)
    Button bt_gb;
    @BindView(R.id.bt_2)
    Button bt_gbo;
    @BindView(R.id.bt_3)
    Button bt_intent;

    private String LYGB_ACTION = "laiyu_broatcast";
    private String PERMISSION_OWNER = "permission_owner";
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
        filter.addAction(LYGB_ACTION);
        registerReceiver(mBroadcastReceiver, filter);

        //registerReceiver(myReceiver,filter,PERMISSION_OWNER,null);//handler?
    }

    private void initEvent() {
        bt_gb.setOnClickListener(this);
        bt_gbo.setOnClickListener(this);
        bt_intent.setOnClickListener(this);
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
        }
    }

    private void intentSystemUI() {
        //https://blog.csdn.net/w690333243/article/details/78082176  上面很多指令
        Intent intent = new Intent();
        String pcgName = "com.android.settings";
//        String clsName = "com.android.settings.Settings$SecuritySettingsActivity"; // skip OK
        String clsName = "com.android.settings.Settings";

        ComponentName componentName = new ComponentName(pcgName, clsName);
        intent.setComponent(componentName);
        intent.setAction("android.intent.action.VIEW");
        startActivity(intent);
    }

    private void sendGBowner() {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(LYGB_ACTION);
        this.sendBroadcast(intent, PERMISSION_OWNER);
    }

    private void sendGB() {

        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(LYGB_ACTION);
        this.sendBroadcast(intent);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) //onReceive函数不能做耗时的事情，参考值：10s以内
        {
            String action = intent.getAction();
            if (action.equals(LYGB_ACTION)) {
                showToast("GB receive");
            }
        }
    };
}
