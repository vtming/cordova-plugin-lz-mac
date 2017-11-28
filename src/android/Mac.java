package com.longzheng.mac;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * wyf
 */
public class Mac extends CordovaPlugin {

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if (action.equals("WIFI")) {
      String wifiMAC = WlanMacUtil.getMacAddress();
      callbackContext.success(wifiMAC);
      return true;
    }
    if (action.equals("BLE")) {
      String bleMac = BleMacUtil.getBleMac();
      callbackContext.success(bleMac);
      return true;
    }
    return false;
  }

}
