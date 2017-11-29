package com.longzheng.mac;

import android.bluetooth.BluetoothAdapter;

/**
 * Created by wyfei on 2017/11/27.
 */

public class BleMacUtil {

  public static String getBleMac() {
    return android.provider.Settings.Secure.getString(context.getContentResolver(), "bluetooth_address");
  }
}
