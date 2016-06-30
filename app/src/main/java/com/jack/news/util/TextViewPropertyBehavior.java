package com.jack.news.util;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/6/6 0006.
 */
public class TextViewPropertyBehavior extends CoordinatorLayout.Behavior<TextView> {
    /**
     * 运行时通过这个构造函数获取Behavior对象
     * @param context
     * @param attrs
     */
    public TextViewPropertyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 依赖条件，true表示绑定关系成立
     * 判断child的布局是否依赖dependency
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        //返回false表示child不依赖dependency，ture表示依赖
        return dependency instanceof TextView;
    }

    /**
     * 属性依赖逻辑，返回true表示要执行
     * 当dependency发生改变时（位置、宽高等），执行这个函数
     * 返回true表示child的位置或者是宽高要发生改变，否则就返回false
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        int offset = dependency.getTop() - child.getTop();
        ViewCompat.offsetTopAndBottom(child,offset);//纵向移动
        ViewCompat.offsetLeftAndRight(child,offset);
        return true;
    }

}
