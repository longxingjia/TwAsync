package com.tw.async.http;

import com.tw.async.AsyncSocket;
import com.tw.async.DataEmitter;

public interface AsyncHttpResponse extends DataEmitter {
    public String protocol();
    public String message();
    public int code();
    public Headers headers();
    public AsyncSocket detachSocket();
    public AsyncHttpRequest getRequest();
}
