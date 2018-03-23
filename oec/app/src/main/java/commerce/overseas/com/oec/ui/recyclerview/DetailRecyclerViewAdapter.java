package commerce.overseas.com.oec.ui.recyclerview;

import android.content.Context;
import android.view.View;

import java.util.List;

import commerce.overseas.com.oec.R;
import commerce.overseas.com.oec.ui.Entity.DetailBean;

/**
 * @Time : 2018/3/23 no 下午1:02
 * @USER : vvguoliang
 * @File : DetailRecyclerViewAdapter.java
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

public class DetailRecyclerViewAdapter extends BaseRecyclerAdapter<DetailBean> {

    public DetailRecyclerViewAdapter(Context context, List<DetailBean> list) {
        super(context, list);
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= 0 && position < mList.size()) {
            return mList.get(position).isTitle() ? ITEM_VIEW_TYPE_TITLE : ITEM_VIEW_TYPE_NORMAL;
        }
        return ITEM_VIEW_TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public BaseViewHolder getHolder(View itemView, int viewType, OnItemClickListener listener) {
        return new DetailViewHolder(itemView, viewType, listener);
    }

    @Override
    public int getLayoutId(int viewType) {
        return viewType == ITEM_VIEW_TYPE_TITLE ? R.layout.recyclerview_item_detail_title : R.layout.recyclerview_common_item;
    }

    public class DetailViewHolder extends BaseViewHolder<DetailBean> {

        public DetailViewHolder(View itemView, int viewType, OnItemClickListener listener) {
            super(itemView, viewType, listener);
        }

        @Override
        public void bindViewHolder(DetailBean detailBean, View itemView) {
            mTextView.setText(detailBean.getName());
        }
    }
}
