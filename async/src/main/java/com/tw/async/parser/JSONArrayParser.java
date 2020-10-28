package com.tw.async.parser;

import com.tw.async.DataEmitter;
import com.tw.async.DataSink;
import com.tw.async.callback.CompletedCallback;
import com.tw.async.future.Future;
import com.tw.async.future.ThenCallback;
import ohos.utils.zson.ZSONArray;

import java.lang.reflect.Type;

/**
 * Created by lxj on 5/27/13.
 */
public class JSONArrayParser implements AsyncParser<ZSONArray> {
    @Override
    public Future<ZSONArray> parse(DataEmitter emitter) {
        return new StringParser().parse(emitter)
//        .thenConvert(ZSONArray::new);
        .thenConvert(new ThenCallback<ZSONArray, String>() {
            @Override
            public ZSONArray then(String from) throws Exception {
                return ZSONArray.stringToZSONArray(from);
            }
        });
    }

    @Override
    public void write(DataSink sink, ZSONArray value, CompletedCallback completed) {
        new StringParser().write(sink, value.toString(), completed);
    }

    @Override
    public Type getType() {
        return ZSONArray.class;
    }

    @Override
    public String getMime() {
        return "application/json";
    }
}
