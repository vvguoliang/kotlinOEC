package commerce.overseas.com.oec.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import commerce.overseas.com.oec.R;
import commerce.overseas.com.oec.base.BaseFragment;
import commerce.overseas.com.oec.ui.Entity.DetailBean;
import commerce.overseas.com.oec.ui.recyclerview.DetailRecyclerViewAdapter;
import commerce.overseas.com.oec.ui.recyclerview.GridDividerItemDecoration;
import commerce.overseas.com.oec.ui.recyclerview.ItemHeaderDecoration;
import commerce.overseas.com.oec.ui.recyclerview.OnItemClickListener;

/**
 * @Time : 2018/3/23 no 下午1:01
 * @USER : vvguoliang
 * @File : ClassificationDetailFragment.java
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
@SuppressLint("ValidFragment")
public class ClassificationDetailFragment extends BaseFragment implements OnItemClickListener {
    private RecyclerView mRecyclerView;
    private DetailRecyclerViewAdapter mAdapter;
    private List<DetailBean> mList;
    private GridLayoutManager mLayoutManager;

    public ClassificationDetailFragment() {
        initData();
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            DetailBean bean = new DetailBean();
            bean.setTitle(true);
            bean.setTag(i + "");
            bean.setName("分类" + i);
            mList.add(bean);
            for (int j = 0; j < 10; j++) {
                bean = new DetailBean();
                bean.setTitle(false);
                bean.setTag(i + "");
                bean.setName(j + "");
                mList.add(bean);
            }
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView(inflater, container);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.detail_recycler);
        mAdapter = new DetailRecyclerViewAdapter(getActivity(), mList);
        mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mList.get(position).isTitle() ? 3 : 1;
            }
        });
        mRecyclerView.addItemDecoration(new GridDividerItemDecoration(getActivity(), mList));
        mRecyclerView.addItemDecoration(new ItemHeaderDecoration(getActivity(), mList));
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.recyclerview_fragment_layout, container, false);
        return view;
    }

    @Override
    public void onItemClick(View itemView, int position) {
        Toast.makeText(getActivity(), "点击了" + position, Toast.LENGTH_LONG).show();
    }
}
