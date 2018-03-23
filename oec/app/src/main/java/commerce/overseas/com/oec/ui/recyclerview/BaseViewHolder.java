package commerce.overseas.com.oec.ui.recyclerview;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import commerce.overseas.com.oec.R;

/**
 * @Time : 2018/3/23 no 下午12:52
 * @USER : vvguoliang
 * @File : BaseViewHolder.java
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

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected TextView mTextView;
    protected OnItemClickListener mOnItemClickListener;

    public BaseViewHolder(View itemView, int itemType, OnItemClickListener listener) {
        super(itemView);
        mOnItemClickListener = listener;
        mTextView = itemView.findViewById(R.id.textview);
        if (itemType == BaseRecyclerAdapter.ITEM_VIEW_TYPE_TITLE) {
            mTextView = itemView.findViewById(R.id.title_textview);
        }
        itemView.setOnClickListener(this);
    }


    public abstract void bindViewHolder(T t,View itemView);

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null)
            mOnItemClickListener.onItemClick(view, getAdapterPosition());
    }
}
