package commerce.overseas.com.oec.ui.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import commerce.overseas.com.oec.R;

/**
 * @Time : 2018/3/23 no 下午12:51
 * @USER : vvguoliang
 * @File : ClassRecyclerViewAdapter.java
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

public class ClassRecyclerViewAdapter extends BaseRecyclerAdapter<Map<String, Object>> {

    private int thisPosition = 0;

    public int getThisPosition() {
        return thisPosition;
    }

    public void setThisPosition(int thisPosition) {
        this.thisPosition = thisPosition;
    }

    public ClassRecyclerViewAdapter(Context context, List<Map<String, Object>> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder getHolder(View itemView, int itemType, OnItemClickListener listener) {
        return new ClassViewHolder(itemView, itemType, listener);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.left_recyclerview_item_layout;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private class ClassViewHolder extends BaseViewHolder<Map<String, Object>> {

        public ClassViewHolder(View itemView, int itemType, OnItemClickListener listener) {
            super(itemView, itemType, listener);
        }

        @Override
        public void bindViewHolder(Map<String, Object> s, View itemView) {
            mTextView.setText(s.get("name").toString());
            if (Integer.parseInt(s.get("type").toString()) == getThisPosition()) {
                mTextView.setBackgroundColor(Color.WHITE);
                itemView.findViewById(R.id.view_image).setBackgroundColor(Color.RED);
            } else {
                mTextView.setBackgroundColor(Color.rgb(248, 248, 255));
                itemView.findViewById(R.id.view_image).setBackgroundColor(Color.rgb(248, 248, 255));
            }
        }
    }
}
