package com.longzheng.mac;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by wyfei on 2017/11/29.
 */

public class WlanMacUtil {

  public static String getMacAddress(Context context) {
    String wlanMac = "";
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {//android6.0以上
      wlanMac = getWlanMacM();
    } else {
      wlanMac = getWlanMacL(context);
    }
    return TextUtils.isEmpty(wlanMac) || "02:00:00:00:00:00".equals(wlanMac) ? "" : wlanMac;
  }

  /**
   * android 5.0 及以下
   * 获取wifi mac
   *
   * @return
   */
  private static String getWlanMacL(Context context) {
    WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    WifiInfo info = wifi.getConnectionInfo();

    return info.getMacAddress() == null ? "" : info.getMacAddress();
  }

  /**
   * 适用于android7.0
   * 通过ip地址来获取绑定的mac地址
   *
   * 只能获取WIFI下的mac !! 必须连接到WiFi
   * 使用手机网络获取到的不一样！
   *
   * @return
   */
  private static String getWlanMacM() {
    String strMacAddr = null;
    try {//获得IpD地址
      InetAddress ip = getLocalInetAddress();
      byte[] b = NetworkInterface.getByInetAddress(ip).getHardwareAddress();
      StringBuffer buffer = new StringBuffer();
      for (int i = 0; i < b.length; i++) {
        if (i != 0) {
          buffer.append(':');
        }
        String str = Integer.toHexString(b[i] & 0xFF);
        buffer.append(str.length() == 1 ? 0 + str : str);
      }
      strMacAddr = buffer.toString().toUpperCase();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return strMacAddr;
  }

  /**
   * 获取移动设备本地IP
   *
   * @return
   */
  private static InetAddress getLocalInetAddress() {
    InetAddress ip = null;
    try {//列举
      Enumeration<NetworkInterface> en_netInterface = NetworkInterface.getNetworkInterfaces();
      while (en_netInterface.hasMoreElements()) {//是否还有元素
        NetworkInterface ni = (NetworkInterface) en_netInterface.nextElement();//得到下一个元素
        Enumeration<InetAddress> en_ip = ni.getInetAddresses();//得到一个ip地址的列举
        while (en_ip.hasMoreElements()) {
          ip = en_ip.nextElement();
          if (!ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
            break;
          } else {
            ip = null;
          }
        }
        if (ip != null) {
          break;
        }
      }
    } catch (SocketException e) {
      e.printStackTrace();
    }
    return ip;
  }


  private boolean isWifiConnection(Context context) {
    boolean haveConnectedWifi = false;
    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
    for (NetworkInfo ni : netInfo) {
      if (ni.getTypeName().equalsIgnoreCase("WIFI"))
        if (ni.isConnected()){
          haveConnectedWifi = true;
          break;
        }
    }
    return haveConnectedWifi;
  }
}
