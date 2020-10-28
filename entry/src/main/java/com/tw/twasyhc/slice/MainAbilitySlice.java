package com.tw.twasyhc.slice;

import com.tw.async.hilog.Logger;
import com.tw.async.http.TwAsyncHttpClient;
import com.tw.async.http.AsyncHttpGet;
import com.tw.async.http.AsyncHttpResponse;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;

import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.DirectionalLayout.LayoutConfig;
import ohos.agp.components.Image;
import ohos.agp.components.Text;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;
import ohos.agp.utils.TextAlignment;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;
import ohos.utils.zson.ZSONObject;

import java.io.File;

public class MainAbilitySlice extends AbilitySlice {

    private DirectionalLayout myLayout = new DirectionalLayout(this);

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);

        LayoutConfig config = new LayoutConfig(LayoutConfig.MATCH_CONTENT, LayoutConfig.MATCH_CONTENT);
        myLayout.setLayoutConfig(config);
        ShapeElement element = new ShapeElement();
        element.setRgbColor(new RgbColor(255, 255, 255));
        myLayout.setBackground(element);


        Logger.e("fffff");
        Text text = new Text(this);
        text.setLayoutConfig(config);
        text.setText("Hello World");
        text.setTextColor(new Color(0xFF000000));
        text.setTextSize(50);
        text.setTextAlignment(TextAlignment.CENTER);
//        myLayout.addComponent(text);
        super.setUIContent(myLayout);

        // String url = "https://guli-lxj.oss-cn-shenzhen.aliyuncs.com/2020/apk/app-10-26.apk";
        String url = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3356847351,3645484190&fm=26&gp=0.jpg";

             String filename = getCacheDir().getAbsolutePath()+randomFile();

        //     String filename = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath()+randomFile();

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

    }

    private String randomFile() {
        return ((Long)Math.round(Math.random() * 1000)).toString() + ".png";
    }


    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
