package com.tw.async.http.body;

import com.tw.async.DataSink;
import com.tw.async.callback.CompletedCallback;
import com.tw.async.http.NameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public abstract class StreamPart extends Part {
    public StreamPart(String name, long length, List<NameValuePair> contentDisposition) {
        super(name, length, contentDisposition);
    }
    
    @Override
    public void write(DataSink sink, CompletedCallback callback) {
        try {
            InputStream is = getInputStream();
            com.tw.async.Util.pump(is, sink, callback);
        }
        catch (Exception e) {
            callback.onCompleted(e);
        }
    }
    
    protected abstract InputStream getInputStream() throws IOException;
}
