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

## 接口分为“注册”和“验证”连个接口

用户需要先注册才能使用验证接口。

+ 注册：    用户读一串文字，讯飞平台会分析用户的声音，获取一些声音特征。完成注册。
+ 验证：    用户读一串文字，讯飞根据用户注册时的的声纹特征判断是不是用户本人。

**插件实时返回讯飞sdk的数据.**
## 数据返回格式  

```json
{
    "code": 1200,         //返回码---数字类型
    "msg": "当前正在说话，音量大小：8",    //提示信息----字符串
    "pwd": "密码",        //需要用户读的文字,仅在----字符串
    "volume": 8            //实时返回用户声音的大小--数字
}
```


