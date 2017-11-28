package com.longzheng.mac;

import android.bluetooth.BluetoothAdapter;

/**
 * Created by wyfei on 2017/11/27.
 */

public class BleMacUtil {

  public static String getBleMac() {
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    String mac = mBluetoothAdapter.getAddress();
    return mac;
  }
}
