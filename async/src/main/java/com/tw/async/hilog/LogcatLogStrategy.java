package com.tw.async.hilog;

import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import static com.tw.async.hilog.Utils.checkNotNull;


public class LogcatLogStrategy implements LogStrategy {

    static final String DEFAULT_TAG = "NO_TAG";

    @Override
    public void log(int priority,  String tag,   String message) {
        checkNotNull(message);

        if (tag == null) {
            tag = DEFAULT_TAG;
        }

        HiLogLabel label = new HiLogLabel(HiLog.LOG_APP,  0xAAABB, tag); //MY_MODULE=0x00201
        HiLog.warn(label,message);
//        Log.println(priority, tag, message);
    }
}
