package com.tw.async.callback;

public interface ValueFunction<T> {
    T getValue() throws Exception;
}
