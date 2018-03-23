package commerce.overseas.com.oec.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commerce.overseas.com.oec.R;
import commerce.overseas.com.oec.base.BaseFragment;
import commerce.overseas.com.oec.ui.recyclerview.ClassRecyclerViewAdapter;
import commerce.overseas.com.oec.ui.recyclerview.OnItemClickListener;

/**
 * @Time : 2018/3/23 no 上午11:07
 * @USER : vvguoliang
 * @File : MainTabClassification.java
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

@SuppressWarnings("ALL")
public class MainTabClassification extends BaseFragment implements  OnItemClickListener {//SwipeRefreshLayout.OnRefreshListener,

//    private SwipeRefreshLayout swipe_container;

    private RecyclerView recyclerView;

    private ClassRecyclerViewAdapter mClassRecyclerViewAdapter;

    private int mShowingFragments_time = 3000;

    private List<Map<String, Object>> mLeftRvData = new ArrayList<>();

    private FragmentManager mFragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_maintabclass, container, false);
//        swipe_container = view.findViewById(R.id.swipe_container);
        recyclerView = view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        swipe_container.setOnRefreshListener(this);

//        设置颜色
//        swipe_container.setColorSchemeResources(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light, android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);

        initData();

        mClassRecyclerViewAdapter = new ClassRecyclerViewAdapter(getActivity(), mLeftRvData);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        mClassRecyclerViewAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mClassRecyclerViewAdapter);

        createFragment(0);
    }

    private void createFragment(int position) {
        mFragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        for (int i = 0; mLeftRvData.size() > i; i++) {
            if (position == i && position == 0) {
                fragmentTransaction.add(R.id.fragment, new ClassSelectionFragment());
                fragmentTransaction.commit();
                break;
            } else if (position == i && position == 1) {
                fragmentTransaction.add(R.id.fragment, new ClassBrandFragment());
                fragmentTransaction.commit();
                break;
            } else if (position > 1) {
                fragmentTransaction.add(R.id.fragment, new ClassificationDetailFragment());
                fragmentTransaction.commit();
                break;
            }
        }
    }

//    @Override
//    public void onRefresh() {
//        new Handler().postDelayed(() -> {
//            if (swipe_container.isRefreshing()) {
//                swipe_container.setRefreshing(false);
//            }
//        }, mShowingFragments_time);
//
//    }

    @Override
    public void onItemClick(View itemView, int position) {
        mClassRecyclerViewAdapter.setThisPosition(position);
        createFragment(position);
        mClassRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void initData() {
        String[] leftClasses = {"精选大牌", "品牌", "女士", "男士", "美妆", "家居", "婴童"};
        for (int i = 0; leftClasses.length > i; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("type", i);
            map.put("name", leftClasses[i]);
            mLeftRvData.add(map);
        }
    }
}
