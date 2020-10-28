package com.tw.async.http.server;

import com.tw.async.AsyncSocket;
import com.tw.async.ByteBufferList;
import com.tw.async.DataSink;
import com.tw.async.callback.CompletedCallback;
import com.tw.async.http.AsyncHttpResponse;
import com.tw.async.http.Headers;
import com.tw.async.parser.AsyncParser;
import ohos.utils.zson.ZSONArray;
import ohos.utils.zson.ZSONObject;

import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;

public interface AsyncHttpServerResponse extends DataSink, CompletedCallback {
    void end();
    void send(String contentType, byte[] bytes);
    void send(String contentType, ByteBufferList bb);
    void send(String contentType, ByteBuffer bb);
    void send(String contentType, String string);
    void send(String string);
    void send(ZSONObject json);
    void send(ZSONArray jsonArray);
    void sendFile(File file);
    void sendStream(InputStream inputStream, long totalLength);
    <T> void sendBody(AsyncParser<T> body, T value);
    AsyncHttpServerResponse code(int code);
    int code();
    Headers getHeaders();
    void writeHead();
    void setContentType(String contentType);
    void redirect(String location);
    AsyncHttpServerRequest getRequest();
    String getHttpVersion();
    void setHttpVersion(String httpVersion);

    // NOT FINAL
    void proxy(AsyncHttpResponse response);

    /**
     * Alias for end. Used with CompletedEmitters
     */
    void onCompleted(Exception ex);
    AsyncSocket getSocket();
    void setSocket(AsyncSocket socket);
}
