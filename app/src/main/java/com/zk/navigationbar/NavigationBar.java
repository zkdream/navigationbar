package com.zk.navigationbar;

import android.content.Context;

/**
 * @author Created by zhangke
 * @filename NavigationBar
 * @date on 2017\12\4 0004 14:32
 * @email 206357792@qq.com
 * @describe 处理不一样的NavigationBar
 */

public class NavigationBar extends AbsNavigationBar{


    public NavigationBar(Builder builder) {
        super(builder);
    }

    public static class Builder extends AbsNavigationBar.Builder{

        public Builder(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public AbsNavigationBar create() {
            return new NavigationBar(this);
        }
    }
}
