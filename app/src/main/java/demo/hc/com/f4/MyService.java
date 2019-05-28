package demo.hc.com.f4;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by ly on 2019/5/28.
 */

public class MyService extends Service implements APPUtil {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        Bundle bundle = intent.getExtras();
        int op = bundle.getInt("lyc");
        showToast(op + "");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(Myapplication.getContext(), msg, Toast.LENGTH_LONG).show();
    }
}
