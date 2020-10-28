package com.tw.async.http.body;

import com.tw.async.DataEmitter;
import com.tw.async.DataSink;
import com.tw.async.Util;
import com.tw.async.callback.CompletedCallback;
import com.tw.async.future.FutureCallback;
import com.tw.async.http.AsyncHttpRequest;
import com.tw.async.parser.JSONObjectParser;
import ohos.utils.zson.ZSONObject;


public class JSONObjectBody implements AsyncHttpRequestBody<ZSONObject> {
    public JSONObjectBody() {
    }
    
    byte[] mBodyBytes;
    ZSONObject json;
    public JSONObjectBody(ZSONObject json) {
        this();
        this.json = json;
    }

    @Override
    public void parse(DataEmitter emitter, final CompletedCallback completed) {
        new JSONObjectParser().parse(emitter).setCallback(new FutureCallback<ZSONObject>() {
            @Override
            public void onCompleted(Exception e, ZSONObject result) {
                json = result;
                completed.onCompleted(e);
            }
        });
    }

    @Override
    public void write(AsyncHttpRequest request, DataSink sink, final CompletedCallback completed) {
        Util.writeAll(sink, mBodyBytes, completed);
    }

    @Override
    public String getContentType() {
        return CONTENT_TYPE;
    }

    @Override
    public boolean readFullyOnRequest() {
        return true;
    }

    @Override
    public int length() {
        mBodyBytes = json.toString().getBytes();
        return mBodyBytes.length;
    }

    public static final String CONTENT_TYPE = "application/json";

    @Override
    public ZSONObject get() {
        return json;
    }
}

