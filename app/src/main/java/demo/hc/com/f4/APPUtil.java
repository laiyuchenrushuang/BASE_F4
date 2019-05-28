package demo.hc.com.f4;

import android.os.ParcelUuid;

/**
 * Created by ly on 2019/5/24.
 * <p>
 * 注意权限的名字用.形式，不要使用其他的_，可能加不上权限解析，我嘞吗
 */

public interface APPUtil {
    String LYGB_ACTION = "laiyu_broatcast";
    String LYGB_ACTION_OWNER = "laiyu_broatcast_owner";

    String P_LYGB_RECEIVE_OWNER = "laiyu.receive.owner";
    String P_LYGB_SEND_OWNER = "laiyu.owner.send";


    void showToast(String msg);
}
