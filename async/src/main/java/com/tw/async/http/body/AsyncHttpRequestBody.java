package com.tw.async.http.body;

import com.tw.async.DataEmitter;
import com.tw.async.DataSink;
import com.tw.async.callback.CompletedCallback;
import com.tw.async.http.AsyncHttpRequest;

public interface AsyncHttpRequestBody<T> {
    public void write(AsyncHttpRequest request, DataSink sink, CompletedCallback completed);
    public void parse(DataEmitter emitter, CompletedCallback completed);
    public String getContentType();
    public boolean readFullyOnRequest();
    public int length();
    public T get();
}
