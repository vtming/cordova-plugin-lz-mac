# cordova-plugin-lz-mac
获本机网络mac、本机蓝牙mac cordova插件

## Example

```javascript
LZmac.getWIFIMac(
      function (msg) {
        console.log("====WIFI mac==" + msg);
 });

LZmac.getBLEMac(
      function (msg) {
        console.log("====BLE mac==" + msg);
      });

```



