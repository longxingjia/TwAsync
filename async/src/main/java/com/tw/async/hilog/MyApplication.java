package com.tw.async.hilog;

import ohos.aafwk.ability.AbilityPackage;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class MyApplication extends AbilityPackage {
    @Override
    public void onInitialize() {
        super.onInitialize();
        HiLog.error(new HiLogLabel(1,2,"tg"),"ss","2");
    }
}
