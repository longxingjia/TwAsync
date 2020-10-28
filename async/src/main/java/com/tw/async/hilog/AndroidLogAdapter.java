package com.tw.async.hilog;


import static com.tw.async.hilog.Utils.checkNotNull;

public class AndroidLogAdapter implements LogAdapter {

     private final FormatStrategy formatStrategy;

    public AndroidLogAdapter() {
        this.formatStrategy = PrettyFormatStrategy.newBuilder().build();
    }

    public AndroidLogAdapter( FormatStrategy formatStrategy) {
        this.formatStrategy = checkNotNull(formatStrategy);
    }

    @Override public boolean isLoggable(int priority,  String tag) {
        return true;
    }

    @Override public void log(int priority, String tag,  String message) {
        formatStrategy.log(priority, tag, message);
    }

}