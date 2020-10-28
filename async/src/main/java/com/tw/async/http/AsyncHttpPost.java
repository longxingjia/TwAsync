package com.tw.async.http;


import ohos.utils.net.Uri;

public class AsyncHttpPost extends AsyncHttpRequest {
    public static final String METHOD = "POST";
    
    public AsyncHttpPost(String uri) {
        this(Uri.parse(uri));
    }

    public AsyncHttpPost(Uri uri) {
        super(uri, METHOD);
    }
}
