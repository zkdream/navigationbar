package com.zk.navigationbar;

import android.content.Context;
import android.view.View;

/**
 * @author Created by zhangke
 * @filename DefaultNavigationBar
 * @date on 2017\11\29 0029 19:07
 * @email 206357792@qq.com
 * @describe 默认的导航栏
 */

public class DefaultNavigationBar extends AbsNavigationBar<DefaultNavigationBar.Builder> {
    public DefaultNavigationBar(Builder builder) {
        super(builder);
    }

    @Override
    public void attachNavigationParams() {
        super.attachNavigationParams();

//      处理特有的
        View ivViewLeft=findViewById(R.id.iv_back_baseTitle);
        ivViewLeft.setVisibility(getBuilder().mIvLeftVisible);

        View ivViewRight=findViewById(R.id.iv_default_add);
        ivViewRight.setVisibility(getBuilder().mIvRightVisible);

        View tvViewRight=findViewById(R.id.tv_menu_baseTitle);
        tvViewRight.setVisibility(getBuilder().mTvRightVisible);
    }

    /**
     * 设置状态栏的Builder
     */
    public static class Builder extends AbsNavigationBar.Builder<Builder>{

        public int mIvLeftVisible= View.VISIBLE;
        public int mIvRightVisible=View.GONE;
        public int mTvRightVisible=View.GONE;

        public Builder(Context context) {
            super(context, R.layout.ui_defualt_navigation_bar);
        }

        @Override
        public AbsNavigationBar create() {
            return new DefaultNavigationBar(this);
        }
        public Builder setLeftIcon(int imageId){
            setImageId(R.id.iv_back_baseTitle,imageId);
            return this;
        }
        public Builder setRightIcon(int imageId){
            setImageId(R.id.iv_default_add,imageId);
            return this;
        }

        public Builder setRightText(String text){
            setText(R.id.tv_menu_baseTitle,text);
            return this;
        }

        public Builder setMiddleText(String text){
            setText(R.id.tv_title_default,text);
            return this;
        }

        public Builder setIvLeftClickListener(View.OnClickListener clickListener){
            setOnClickListener(R.id.iv_back_baseTitle,clickListener);
            return this;
        }
        public Builder setIvRightClickListener(View.OnClickListener clickListener){
            setOnClickListener(R.id.iv_default_add,clickListener);
            return this;
        }

        public Builder setTvRightClickListener(View.OnClickListener clickListener){
            setOnClickListener(R.id.tv_menu_baseTitle,clickListener);
            return this;
        }

        public Builder hideLeftIcon(){
            mIvLeftVisible=View.GONE;
            return this;
        }
        public Builder showRightIcon(){
            mIvRightVisible=View.VISIBLE;
            return this;
        }
        public Builder showRightText(){
            mTvRightVisible=View.VISIBLE;
            return this;
        }
    }
}
