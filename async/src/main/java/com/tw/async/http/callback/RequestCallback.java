package com.tw.async.http.callback;

import com.tw.async.callback.ResultCallback;
import com.tw.async.http.AsyncHttpResponse;

public interface RequestCallback<T> extends ResultCallback<AsyncHttpResponse, T> {
    public void onConnect(AsyncHttpResponse response);
    public void onProgress(AsyncHttpResponse response, long downloaded, long total);
}
