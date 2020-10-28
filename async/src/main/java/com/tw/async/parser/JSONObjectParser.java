package com.tw.async.parser;

import com.tw.async.DataEmitter;
import com.tw.async.DataSink;
import com.tw.async.callback.CompletedCallback;
import com.tw.async.future.Future;
import com.tw.async.future.ThenCallback;
import ohos.utils.zson.ZSONObject;

import java.lang.reflect.Type;

/**
 * Created by lxj on 5/27/13.
 */
public class JSONObjectParser implements AsyncParser<ZSONObject> {
    @Override
    public Future<ZSONObject> parse(DataEmitter emitter) {
        return new StringParser().parse(emitter).thenConvert(new ThenCallback<ZSONObject, String>() {
            @Override
            public ZSONObject then(String from) throws Exception {
                return ZSONObject.stringToZSON(from);
            }
        });
    }

    @Override
    public void write(DataSink sink, ZSONObject value, CompletedCallback completed) {
        new StringParser().write(sink, value.toString(), completed);
    }

    @Override
    public Type getType() {
        return ZSONObject.class;
    }

    @Override
    public String getMime() {
        return "application/json";
    }
}
