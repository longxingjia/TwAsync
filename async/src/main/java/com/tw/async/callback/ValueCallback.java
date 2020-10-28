package com.tw.async.callback;

/**
 * Created by tw.lxj.
 */
public interface ValueCallback<T> {
    void onResult(T value);
}
