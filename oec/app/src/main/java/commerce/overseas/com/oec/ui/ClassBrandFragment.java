package commerce.overseas.com.oec.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import commerce.overseas.com.oec.R;
import commerce.overseas.com.oec.base.BaseFragment;
import commerce.overseas.com.oec.ui.Entity.SortModel;
import commerce.overseas.com.oec.ui.recyclerview.SortAdapter;
import commerce.overseas.com.oec.ui.util.PinyinUtils;
import commerce.overseas.com.oec.ui.view.PinyinComparator;
import commerce.overseas.com.oec.ui.view.TitleItemDecoration;
import commerce.overseas.com.oec.ui.view.WaveSideBar;

/**
 * @Time : 2018/3/23 no 下午5:48
 * @USER : vvguoliang
 * @File : ClassBrandFragment.java
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

public class ClassBrandFragment extends BaseFragment implements WaveSideBar.OnTouchLetterChangeListener {

    /**
     * 根据拼音来排列RecyclerView里面的数据类
     */
    private PinyinComparator mComparator;

    private WaveSideBar mSideBar;

    private RecyclerView mRecyclerView;

    private SortAdapter mAdapter;

    private LinearLayoutManager manager;

    private List<SortModel> mDateList;

    private TitleItemDecoration mDecoration;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_class_brand, container, false);
        mSideBar = view.findViewById(R.id.sideBar);
        mRecyclerView = view.findViewById(R.id.detail_recycler);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mComparator = new PinyinComparator();

        mDateList = filledData(getResources().getStringArray(R.array.date));

        mSideBar.setOnTouchLetterChangeListener(this);

        // 根据a-z进行排序源数据
        Collections.sort(mDateList, mComparator);

        //RecyclerView设置manager
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new SortAdapter(getActivity(), mDateList);
        mRecyclerView.setAdapter(mAdapter);
        mDecoration = new TitleItemDecoration(getActivity(), mDateList);
        //如果add两个，那么按照先后顺序，依次渲染。
        mRecyclerView.addItemDecoration(mDecoration);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onLetterChange(String letter) {
//该字母首次出现的位置
        int position = mAdapter.getPositionForSection(letter.charAt(0));
        if (position != -1) {
            manager.scrollToPositionWithOffset(position, 0);
        }
    }

    /**
     * 为RecyclerView填充数据
     *
     * @param date
     * @return
     */
    private List<SortModel> filledData(String[] date) {
        List<SortModel> mSortList = new ArrayList<>();
        for (int i = 0; i < date.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
            //汉字转换成拼音
            String pinyin = PinyinUtils.getPingYin(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setLetters(sortString.toUpperCase());
            } else {
                sortModel.setLetters("#");
            }
            mSortList.add(sortModel);
        }
        return mSortList;

    }
}
