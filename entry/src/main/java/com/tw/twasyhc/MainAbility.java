package com.tw.twasyhc;

import com.tw.async.ResourceTable;
import com.tw.async.hilog.AndroidLogAdapter;
import com.tw.async.hilog.Logger;
import com.tw.twasyhc.slice.MainAbilitySlice;
import com.tw.twasyhc.slice.SecondAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.agp.components.element.ShapeElement;
import ohos.bundle.IBundleManager;

public class MainAbility extends Ability {
    private final static int MY_PERMISSIONS_REQUEST_CAMERA  = 1;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);



        Logger.addLogAdapter(new AndroidLogAdapter());
        super.setMainRoute(SecondAbilitySlice.class.getName());
      //  Logger.e(verifySelfPermission("ohos.permission.WRITE_MEDIA")+"verifySelfPermission(\"ohos.permission.WRITE_MEDIA\")");
//        if (verifySelfPermission("ohos.permission.WRITE_MEDIA") != IBundleManager.PERMISSION_GRANTED) {
//
//            Logger.e("-----------------");
//            // 应用未被授予权限
//            if (canRequestPermission("ohos.permission.WRITE_MEDIA")) {
//                // 是否可以申请弹框授权(首次申请或者用户未选择禁止且不再提示)
//                requestPermissionsFromUser(
//                        new String[] { "ohos.permission.WRITE_MEDIA","ohos.permission.MEDIA_LOCATION" } , MY_PERMISSIONS_REQUEST_CAMERA);
//            } else {
//                // 显示应用需要权限的理由，提示用户进入设置授权
//                Logger.e("-----------------");
//                requestPermissionsFromUser(
//                        new String[] { "ohos.permission.WRITE_MEDIA","ohos.permission.MEDIA_LOCATION" } , MY_PERMISSIONS_REQUEST_CAMERA);
//
//            }
//        } else {
//            // 权限已被授予
//            Logger.e("--------权限已被授予---------");
//        }


    }




}
