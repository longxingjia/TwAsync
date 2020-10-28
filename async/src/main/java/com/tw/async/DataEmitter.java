package com.tw.async;


import com.tw.async.callback.CompletedCallback;
import com.tw.async.callback.DataCallback;

public interface DataEmitter {
    void setDataCallback(DataCallback callback);
    DataCallback getDataCallback();
    boolean isChunked();
    void pause();
    void resume();
    void close();
    boolean isPaused();
    void setEndCallback(CompletedCallback callback);
    CompletedCallback getEndCallback();
    AsyncServer getServer();
    String charset();
}
