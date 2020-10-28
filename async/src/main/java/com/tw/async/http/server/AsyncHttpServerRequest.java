package com.tw.async.http.server;

import com.tw.async.AsyncSocket;
import com.tw.async.DataEmitter;
import com.tw.async.http.Headers;
import com.tw.async.http.Multimap;
import com.tw.async.http.body.AsyncHttpRequestBody;

import java.util.Map;
import java.util.regex.Matcher;

public interface AsyncHttpServerRequest extends DataEmitter {
    Headers getHeaders();
    Matcher getMatcher();
    void setMatcher(Matcher matcher);
    <T extends AsyncHttpRequestBody> T getBody();
    AsyncSocket getSocket();
    String getPath();
    Multimap getQuery();
    String getMethod();
    String getUrl();

    String get(String name);
    Map<String, Object> getState();
}
