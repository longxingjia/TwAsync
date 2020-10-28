package com.tw.async.future;

public interface TypeConverter<T, F> {
    Future<T> convert(F from, String fromMime) throws Exception;
}
