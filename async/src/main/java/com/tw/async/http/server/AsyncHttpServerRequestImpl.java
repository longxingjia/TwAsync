package com.tw.async.http.server;

import com.tw.async.AsyncSocket;
import com.tw.async.DataEmitter;
import com.tw.async.FilteredDataEmitter;
import com.tw.async.LineEmitter;
import com.tw.async.LineEmitter.StringCallback;
import com.tw.async.callback.CompletedCallback;
import com.tw.async.callback.DataCallback;
import com.tw.async.http.Headers;
import com.tw.async.http.HttpUtil;
import com.tw.async.http.Multimap;
import com.tw.async.http.Protocol;
import com.tw.async.http.body.AsyncHttpRequestBody;

import java.io.IOException;
import java.util.HashMap;

public abstract class AsyncHttpServerRequestImpl extends FilteredDataEmitter implements AsyncHttpServerRequest, CompletedCallback {
    private String statusLine;
    private Headers mRawHeaders = new Headers();
    AsyncSocket mSocket;
    private HashMap<String, Object> state = new HashMap<>();

    @Override
    public HashMap<String, Object> getState() {
        return state;
    }

    public String getStatusLine() {
        return statusLine;
    }

    private CompletedCallback mReporter = new CompletedCallback() {
        @Override
        public void onCompleted(Exception error) {
            AsyncHttpServerRequestImpl.this.onCompleted(error);
        }
    };

    @Override
    public void onCompleted(Exception e) {
//        if (mBody != null)
//            mBody.onCompleted(e);
        report(e);
    }

    abstract protected void onHeadersReceived();
    
    protected void onNotHttp() {
        System.out.println("not http!");
    }

    protected AsyncHttpRequestBody onUnknownBody(Headers headers) {
        return null;
    }
    protected AsyncHttpRequestBody onBody(Headers headers) {
        return null;
    }

    
    StringCallback mHeaderCallback = new StringCallback() {
        @Override
        public void onStringAvailable(String s) {
            if (statusLine == null) {
                statusLine = s;
                if (!statusLine.contains("HTTP/")) {
                    onNotHttp();
                    mSocket.setDataCallback(new NullDataCallback());
                    report(new IOException("data/header received was not not http"));
                }

                return;
            }
            if (!"\r".equals(s)){
                mRawHeaders.addLine(s);
                return;
            }

            DataEmitter emitter = HttpUtil.getBodyDecoder(mSocket, Protocol.HTTP_1_1, mRawHeaders, true);
            mBody = onBody(mRawHeaders);
            if (mBody == null) {
                mBody = HttpUtil.getBody(emitter, mReporter, mRawHeaders);
                if (mBody == null) {
                    mBody = onUnknownBody(mRawHeaders);
                    if (mBody == null)
                        mBody = new UnknownRequestBody(mRawHeaders.get("Content-Type"));
                }
            }
            mBody.parse(emitter, mReporter);
            onHeadersReceived();
        }
    };

    String method;
    @Override
    public String getMethod() {
        return method;
    }
    
    void setSocket(AsyncSocket socket) {
        mSocket = socket;

        LineEmitter liner = new LineEmitter();
        mSocket.setDataCallback(liner);
        liner.setLineCallback(mHeaderCallback);
        mSocket.setEndCallback(new NullCompletedCallback());
    }
    
    @Override
    public AsyncSocket getSocket() {
        return mSocket;
    }

    @Override
    public Headers getHeaders() {
        return mRawHeaders;
    }

    @Override
    public void setDataCallback(DataCallback callback) {
        mSocket.setDataCallback(callback);
    }

    @Override
    public DataCallback getDataCallback() {
        return mSocket.getDataCallback();
    }

    @Override
    public boolean isChunked() {
        return mSocket.isChunked();
    }

    AsyncHttpRequestBody mBody;
    @Override
    public AsyncHttpRequestBody getBody() {
        return mBody;
    }

    @Override
    public void pause() {
        mSocket.pause();
    }

    @Override
    public void resume() {
        mSocket.resume();
    }

    @Override
    public boolean isPaused() {
        return mSocket.isPaused();
    }

    @Override
    public String toString() {
        if (mRawHeaders == null)
            return super.toString();
        return mRawHeaders.toPrefixString(statusLine);
    }

    @Override
    public String get(String name) {
        Multimap query = getQuery();
        String ret = query.getString(name);
        if (ret != null)
            return ret;
        AsyncHttpRequestBody body = getBody();
        Object bodyObject = body.get();
        if (bodyObject instanceof Multimap) {
            Multimap map = (Multimap)bodyObject;
            return map.getString(name);
        }
        return null;
    }
}
