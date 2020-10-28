# TwAsyn

  TwAsync是一个低级别的网络协议库。如果你在找一个容易使用，高级别，Hi软件，http请求库.
  项目参考AndroidAsync

特点：
1. 基于NIO。一个线程，有回调驱动。高性能。

2. 所有的操作都返回一个能取消的Future。

3. Socket 客户端+ Socket 服务端。

4. HTTP 客户端+服务端。

5. WebSocket 客户端+服务端。

6. Socket.IO 客户端。

   

# 使用步骤(Setup)

### 下载源码(Download)

```
 导入async 模块新项目根目录下
```

Gradle配置：

```
dependencies {
      implementation project(':async')
}
```



下载url地址返回字符串类型

```java
 String strUrl= "https://api.binstd.com/idcard/query?appkey=yourappkey&idcard=41272519800102067x";
        TwAsyncHttpClient.getDefaultInstance().executeString(new AsyncHttpGet(strUrl), new TwAsyncHttpClient.StringCallback() {
            @Override
            public void onCompleted(Exception e, AsyncHttpResponse source, String result) {
                if (e != null) {
                    e.printStackTrace();
                    return;
                }
                Logger.e("String is : " + result);
            }
        });
```



从url地址下载得到JSON串。

```java
TwAsyncHttpClient.getDefaultInstance().executeJSONObject(new AsyncHttpGet(strUrl), new TwAsyncHttpClient.JSONObjectCallback() {
            @Override
            public void onCompleted(Exception e, AsyncHttpResponse source, ZSONObject result) {
                if (e != null) {
                    e.printStackTrace();
                    return;

                }
                Logger.e("ZSONObject is : " + result.toString());
            }
        });
```


或者得到JSONArrays。

```java
TwAsyncHttpClient.getDefaultInstance().executeJSONArray(new AsyncHttpGet(strUrl), new TwAsyncHttpClient.JSONArrayCallback() {
            @Override
            public void onCompleted(Exception e, AsyncHttpResponse source, ZSONArray result) {
                if (e != null) {
                    e.printStackTrace();
                    return;

                }
                Logger.e("ZSONObject is : " + result.toString());
            }
        })
```



下载url地址得到一个文件。

```java
 TwAsyncHttpClient.getDefaultInstance().executeFile(new AsyncHttpGet(url), filename, new TwAsyncHttpClient.FileCallback() {
            @Override
            public void onCompleted(Exception e, AsyncHttpResponse response, File result) {
                if (e != null) {
                    e.printStackTrace();
                    return;
                }
                Logger.e("my file is available at: " + result.getAbsolutePath());
                Logger.e("my file size is  " + result.getFreeSpace());
                Logger.e("my file size is  " + result.getUsableSpace());
                Image image = new Image(getContext());
                image.setLayoutConfig(config);
//                PixelMap pixelMap = PixelMap.create()

                ImageSource.SourceOptions opts = new ImageSource.SourceOptions();

                ImageSource imageSource = ImageSource.create(result, opts);
                ImageSource.DecodingOptions decodingOptions = new ImageSource.DecodingOptions();

                PixelMap pixelmap = imageSource.createPixelmap(decodingOptions);

                image.setPixelMap(pixelmap);

                myLayout.addComponent(image);

            }
        });
```



### 与原库功能对比 (contrast)

| 功能                         | HiCompressor | Compressor |
| ---------------------------- | ------------ | ---------- |
| Socket 客户端+ Socket 服务端 | 支持         | 支持       |
| HTTP 客户端+服务端           | 支持         | 支持       |
| WebSocket 客户端+服务端。    | 支持         | 支持       |
| Socket.IO 客户端。           | 支持         | 支持       |