package com.zk.navigationbar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by zhangke
 * @filename AbsNavigationBar
 * @date on 2017\11\29 0029 19:01
 * @email 206357792@qq.com
 * @describe 导航栏的基类
 */

public class AbsNavigationBar<B extends AbsNavigationBar.Builder> implements INavigation{

    private B mBuilder;
    private View mNavigationBar;

    public AbsNavigationBar(B builder){
        this.mBuilder=builder;
        createNavigationBar();
    }

    @Override
    public void createNavigationBar() {
        mNavigationBar= LayoutInflater.from(mBuilder.mContext)
                .inflate(mBuilder.mLayoutId,mBuilder.mParent,false);
//        添加到父布局里面
        attachParent(mNavigationBar,mBuilder.mParent);
//        绑定参数
        attachNavigationParams();
    }
    /**
     * 绑定参数
     */
    @Override
    public void attachNavigationParams() {
        Map<Integer,CharSequence> textMaps=mBuilder.mTextMaps;
        for (Map.Entry<Integer,CharSequence> entry:textMaps.entrySet()) {
            TextView textView=findViewById(entry.getKey());
            textView.setText(entry.getValue());
        }
//        设置点击事件
        Map<Integer,View.OnClickListener> clickListenerMaps=mBuilder.mCLickListenerMaps;
        for (Map.Entry<Integer,View.OnClickListener> entry:clickListenerMaps.entrySet()) {
            View view=findViewById(entry.getKey());
            view.setOnClickListener(entry.getValue());
        }
        Map<Integer,Integer> imageMaps=mBuilder.mImageMaps;
        for (Map.Entry<Integer,Integer> entry:imageMaps.entrySet()) {
            ImageView imageView=findViewById(entry.getKey());
            imageView.setImageResource(entry.getValue());
        }
    }

    /**
     * findViewById
     * @param ViewId
     * @param <T>
     * @return
     */
    public  <T extends View> T findViewById(int  ViewId) {
        return mNavigationBar.findViewById(ViewId);
    }
    /**
     * 将NavigationBar 添加到父布局里面
     * @param navigationBar
     * @param parent
     */
    @Override
    public void attachParent(View navigationBar, ViewGroup parent) {
        parent.addView(navigationBar,0);
    }
    /**
     * 返回Builder
     * @return
     */
    public B getBuilder(){
        return mBuilder;
    }

    /**
     * Builder构建类
     * 构建NavigationBar 还有参数存储
     */
    public static abstract class Builder<B extends Builder>{
        public Context mContext;
        public int mLayoutId;
        public ViewGroup mParent;
        public Map<Integer, CharSequence> mTextMaps;
        public Map<Integer,View.OnClickListener> mCLickListenerMaps;
        public Map<Integer,Integer> mImageMaps;

        public Builder(Context context, int layoutId) {
            this.mContext = context;
            this.mLayoutId = layoutId;
            ViewGroup rootView = (ViewGroup) ((Activity)context).getWindow().getDecorView();
            this.mParent = (ViewGroup) rootView.getChildAt(0);
            mTextMaps = new HashMap<>();
            mCLickListenerMaps = new HashMap<>();
            mImageMaps=new HashMap<>();
        }
        /**
         * 用来创建 NavigationBar
         *
         * @return
         */
        public abstract AbsNavigationBar create();


        /**
         * 设置文本
         * @param viewId
         * @param text
         * @return
         */
        public B setText(int viewId,String text){
            mTextMaps.put(viewId,text);
            return (B) this;
        }
        /**
         * 设置图片
         * @param viewId
         * @param imageId
         * @return
         */
        public B setImageId(int viewId,int imageId){
            mImageMaps.put(viewId,imageId);
            return (B) this;
        }
        /**
         * 设置点击事件
         * @param viewId
         * @param clickListener
         * @return
         */
        public B setOnClickListener(int viewId,View.OnClickListener clickListener){
            mCLickListenerMaps.put(viewId,clickListener);
            return (B) this;
        }
    }
}
