package com.weixin.menu;

/**
 * Created by Thomas on 2016/2/1.
 * 有子菜单项的一级菜单
 */
public class ComplexButton extends Button {
    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
