package com.tw.async.http;


import ohos.utils.net.Uri;

/**
 * Created by lxj on 8/25/13.
 */
public class AsyncHttpHead extends AsyncHttpRequest {
    public AsyncHttpHead(Uri uri) {
        super(uri, METHOD);
    }

    @Override
    public boolean hasBody() {
        return false;
    }

    public static final String METHOD = "HEAD";
}
