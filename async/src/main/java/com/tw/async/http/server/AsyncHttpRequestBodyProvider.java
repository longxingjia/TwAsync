package com.tw.async.http.server;

import com.tw.async.http.Headers;
import com.tw.async.http.body.AsyncHttpRequestBody;

public interface AsyncHttpRequestBodyProvider {
    AsyncHttpRequestBody getBody(Headers headers);
}
