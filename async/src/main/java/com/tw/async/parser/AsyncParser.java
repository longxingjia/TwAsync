package com.tw.async.parser;

import com.tw.async.DataEmitter;
import com.tw.async.DataSink;
import com.tw.async.callback.CompletedCallback;
import com.tw.async.future.Future;

import java.lang.reflect.Type;

/**
 * Created by lxj on 5/27/13.
 */
public interface AsyncParser<T> {
    Future<T> parse(DataEmitter emitter);
    void write(DataSink sink, T value, CompletedCallback completed);
    Type getType();
    String getMime();
}
