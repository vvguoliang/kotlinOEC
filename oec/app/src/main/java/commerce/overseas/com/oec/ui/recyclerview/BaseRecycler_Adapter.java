package commerce.overseas.com.oec.ui.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Time : 2018/3/23 no 下午5:19
 * @USER : vvguoliang
 * @File : BaseRecycler_Adapter.java
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

public abstract class BaseRecycler_Adapter<T> extends RecyclerView.Adapter<BaseView_Holder> {

    protected List<T> mList = new ArrayList<>();
    protected OnItemClickListener mListener;
    protected Context mContext;
    protected LayoutInflater mInflater;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public BaseRecycler_Adapter(Context context, List<T> list) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        if (list != null)
            this.mList = list;
    }

    public abstract BaseView_Holder getHolder(View itemView, int itemType, OnItemClickListener listener);

    public abstract int getLayoutId(int viewType);

    @Override
    public BaseView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(getLayoutId(viewType), parent, false);
        return getHolder(itemView, viewType, mListener);
    }

    @Override
    public void onBindViewHolder(BaseView_Holder holder, int position) {
        holder.bindViewHolder(mList.get(position), holder.itemView);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

}
