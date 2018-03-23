package commerce.overseas.com.oec.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commerce.overseas.com.oec.R;
import commerce.overseas.com.oec.base.BaseFragment;
import commerce.overseas.com.oec.ui.recyclerview.ClassRecyclerViewAdapter;
import commerce.overseas.com.oec.ui.recyclerview.ClassSelectionAdapter;
import commerce.overseas.com.oec.ui.recyclerview.OnItemClickListener;

/**
 * @Time : 2018/3/23 no 下午5:06
 * @USER : vvguoliang
 * @File : ClassSelectionFragment.java
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

public class ClassSelectionFragment extends BaseFragment implements OnItemClickListener {

    public ClassSelectionFragment() {
        initData();
    }

    private RecyclerView recyclerView;

    private ClassSelectionAdapter classSelectionAdapter;

    private List<Map<String, Object>> mLeftRvData = new ArrayList<>();

    @Override
    public void onItemClick(View itemView, int position) {
        Toast.makeText(getActivity(), "第" + position + "个", Toast.LENGTH_LONG).show();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_fragment_layout, container, false);
        recyclerView = view.findViewById(R.id.detail_recycler);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        classSelectionAdapter = new ClassSelectionAdapter(getActivity(), mLeftRvData);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.INVALID_OFFSET));

        classSelectionAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(classSelectionAdapter);
    }


    private void initData() {
        String[] leftClasses = {"https://cache.net-a-porter.com/images/products/1041734/1041734_in_sl.jpg",
                "https://cache.net-a-porter.com/images/products/1045417/1045417_in_sl.jpg",
                "https://cache.net-a-porter.com/images/products/1042821/1042821_in_sl.jpg",
                "https://cache.net-a-porter.com/images/products/1045907/1045907_in_sl.jpg",
                "https://cache.net-a-porter.com/images/products/1045503/1045503_in_sl.jpg",
                "https://cache.net-a-porter.com/images/products/1045398/1045398_in_sl.jpg",
                "https://cache.net-a-porter.com/images/products/1041734/1041734_in_sl.jpg"};
        for (int i = 0; leftClasses.length > i; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("type", i);
            map.put("name", leftClasses[i]);
            mLeftRvData.add(map);
        }
    }


}
