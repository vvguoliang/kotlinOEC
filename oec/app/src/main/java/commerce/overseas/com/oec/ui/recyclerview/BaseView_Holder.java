package commerce.overseas.com.oec.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import commerce.overseas.com.oec.R;

/**
 * @Time : 2018/3/23 no 下午5:18
 * @USER : vvguoliang
 * @File : BaseView_Holder.java
 * @Software: Android Studio
 * code is far away from bugs with the god animal protecting
 * I love animals. They taste delicious.
 * ***┏┓   ┏ ┓
 * **┏┛┻━━━┛ ┻┓
 * **┃   ☃   ┃
 * **┃ ┳┛  ┗┳ ┃
 * **┃    ┻   ┃
 * **┗━┓    ┏━┛
 * ****┃    ┗━━━┓
 * ****┃ 神兽保佑 ┣┓
 * ****┃ 永无BUG！┏┛
 * ****┗┓┓┏━┳┓┏┛┏┛
 * ******┃┫┫  ┃┫┫
 * ******┗┻┛  ┗┻┛
 */

public abstract class BaseView_Holder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected OnItemClickListener mOnItemClickListener;

    public BaseView_Holder(View itemView, int itemType, OnItemClickListener listener) {
        super(itemView);
        mOnItemClickListener = listener;
        itemView.setOnClickListener(this);
    }

    public abstract void bindViewHolder(T t, View itemView);

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null)
            mOnItemClickListener.onItemClick(view, getAdapterPosition());
    }
}
