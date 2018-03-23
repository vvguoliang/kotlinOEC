package commerce.overseas.com.oec.ui.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
import java.util.Map;

import commerce.overseas.com.oec.R;

/**
 * @Time : 2018/3/23 no 下午5:11
 * @USER : vvguoliang
 * @File : ClassSelectionAdapter.java
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

public class ClassSelectionAdapter extends BaseRecycler_Adapter<Map<String, Object>> {

    public ClassSelectionAdapter(Context context, List<Map<String, Object>> list) {
        super(context, list);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public BaseView_Holder getHolder(View itemView, int itemType, OnItemClickListener listener) {
        return new ClassViewHolder(itemView, itemType, listener);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.view_image;
    }

    private class ClassViewHolder extends BaseView_Holder<Map<String, Object>> {

        public ClassViewHolder(View itemView, int itemType, OnItemClickListener listener) {
            super(itemView, itemType, listener);
        }

        @Override
        public void bindViewHolder(Map<String, Object> s, View itemView) {
            SimpleDraweeView simpleDraweeView = itemView.findViewById(R.id.new_image1);
            simpleDraweeView.setImageURI(s.get("name").toString());
        }
    }
}
