package com.tw.twasyhc.slice;

import com.tw.async.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.*;
import ohos.agp.components.element.ShapeElement;

public class SecondAbilitySlice extends AbilitySlice {


    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);

        setUIContent(ResourceTable.Layout_Layout_main_layout);

        Button button = (Button) findComponentById(ResourceTable.Id_button);
        Text text = (Text) findComponentById(ResourceTable.Id_text);
        if (button != null) {
            // 设置组件的属性
            ShapeElement background = new ShapeElement();
            background.setRgbColor(new RgbColor(0,125,255));
            background.setCornerRadius(25);
            button.setBackground(background);

            button.setClickedListener(new Component.ClickedListener() {
                @Override
                // 在组件中增加对点击事件的检测
                public void onClick(Component Component) {
                    // 此处添加按钮被点击需要执行的操作
//                      text.setText(text.getText() + "T");
//                    setMainRoute(MainAbilitySlice.class.getName());
//                    present(new TargetSlice(), new Intent())
//                    getContext().pre
                    AbilitySlice targetSlice = new MainAbilitySlice();
                    Intent intent = new Intent();
                    intent.setParam("value", 10);
                    present(targetSlice, intent);
                }
            });


        }

    }


    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
