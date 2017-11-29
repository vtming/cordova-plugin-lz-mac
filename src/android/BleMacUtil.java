package com.longzheng.mac;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

/**
 * Created by wyfei on 2017/11/29.
 */
public class BleMacUtil {

  public static String getBleMac(Context context) {
    String bleMac = "";
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
      bleMac = getBleMacM(context);
    } else {
      bleMac = getBleMacL();
    }

    return TextUtils.isEmpty(bleMac)||"02:00:00:00:00:00".equals(bleMac) ? "" : bleMac;
  }

  /**
   * android 5.0 及以下获取蓝牙地址
   * @return
   */
  private static String getBleMacL() {
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    String mac = mBluetoothAdapter.getAddress();
    return mac;
  }

  /**
   * 小米max 获取到的不正确！
   * android 6.0 及以上获取蓝牙地址
   */
  private static String getBleMacM(Context context) {
    return android.provider.Settings.Secure.getString(context.getContentResolver(), "bluetooth_address");
  }
}
