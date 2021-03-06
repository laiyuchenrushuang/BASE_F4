package demo.hc.com.f4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ly on 2019/5/23.
 *
 *
 *
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(APPUtil.LYGB_ACTION_OWNER)) {
            showToast("owner gb is OK");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(Myapplication.getContext(), msg, Toast.LENGTH_LONG).show();
    }
}
