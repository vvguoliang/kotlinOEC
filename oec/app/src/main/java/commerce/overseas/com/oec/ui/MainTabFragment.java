package commerce.overseas.com.oec.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import commerce.overseas.com.oec.R;
import commerce.overseas.com.oec.RecyclerView.BottomListener;
import commerce.overseas.com.oec.RecyclerView.FooterBind;
import commerce.overseas.com.oec.RecyclerView.HeaderBind;
import commerce.overseas.com.oec.RecyclerView.ItemBind;
import commerce.overseas.com.oec.RecyclerView.ItemView;
import commerce.overseas.com.oec.RecyclerView.SlideAdapter;
import commerce.overseas.com.oec.base.BaseFragment;
import commerce.overseas.com.oec.ui.Entity.Bean;
import commerce.overseas.com.oec.ui.viewpaget.CardFragmentPagerAdapter;
import commerce.overseas.com.oec.ui.viewpaget.CardItem;
import commerce.overseas.com.oec.ui.viewpaget.CardPagerAdapter;
import commerce.overseas.com.oec.ui.viewpaget.ShadowTransformer;

/**
 * @Time : 2018/3/21 no 下午3:46
 * @USER : vvguoliang
 * @File : MainTabFragment.java
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

@SuppressWarnings("ConstantConditions")
public class MainTabFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private View view;
    private SwipeRefreshLayout swipe_container;
    private RecyclerView recyclerView;

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;

    private int mShowingFragments_time = 3000;

    private Handler handler = new Handler();

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (swipe_container.isRefreshing()) {
                    swipe_container.setRefreshing(false);
                }
            }
        }, mShowingFragments_time);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fra_maintab, container, false);
        swipe_container = view.findViewById(R.id.swipe_container);
        recyclerView = view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipe_container.setOnRefreshListener(this);
        //设置颜色
        swipe_container.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final List<Bean> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add(new Bean("我是第" + i + "个item"));
        }


        final List<Bean> data2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            data2.add(new Bean("我是一个新item"));
        }


        ItemBind<Bean> itemBind = new ItemBind<Bean>() {
            @Override
            public void onBind(final ItemView itemView, Bean data, int position) {
                itemView.setText(R.id.textView, data.getInfo())
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getActivity(), "click" + position, Toast.LENGTH_SHORT).show();
                            }
                        });
//                        .setOnClickListener(R.id.textView, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Toast.makeText(getActivity(), "textView click" + position, Toast.LENGTH_SHORT).show();
//                            }
//                        });
            }
        };


        SlideAdapter.load(data)
                .item(R.layout.item)//, 0, 0, R.layout.menu, 0.35f)
                .padding(1)
                .header(R.layout.head, 0.5f)
                .footer(R.layout.foot, 0.1f)
                .bind(itemBind)
                .bind(new HeaderBind() {
                    @Override
                    public void onBind(ItemView header, int order) {
                        ViewPager viewPager = header.getView(R.id.viewpager);
                        TextView head_text = header.getView(R.id.head_text);
                        TextView head_text1 = header.getView(R.id.head_text1);

                        mCardAdapter = new CardPagerAdapter();
                        mCardAdapter.addCardItem(new CardItem("你1","https://cache.net-a-porter.com/images/products/1041734/1041734_in_sl.jpg","你2","http://cache.net-a-porter.com/images/products/1039276/1039276_in_sl.jpg"));
                        mCardAdapter.addCardItem(new CardItem("你2","https://cache.net-a-porter.com/images/products/1045417/1045417_in_sl.jpg","","http://cache.net-a-porter.com/images/products/1039276/1039276_in_sl.jpg"));
                        mCardAdapter.addCardItem(new CardItem("你3","https://cache.net-a-porter.com/images/products/1042821/1042821_in_sl.jpg","","http://cache.net-a-porter.com/images/products/1039276/1039276_in_sl.jpg"));
                        mCardAdapter.addCardItem(new CardItem("你4","https://cache.net-a-porter.com/images/products/1045907/1045907_in_sl.jpg","","https://cache.net-a-porter.com/images/products/1042821/1042821_in_sl.jpg"));
                        mCardAdapter.addCardItem(new CardItem("你5","https://cache.net-a-porter.com/images/products/1045503/1045503_in_sl.jpg","","http://cache.net-a-porter.com/images/products/1039276/1039276_in_sl.jpg"));
                        mCardAdapter.addCardItem(new CardItem("你6","https://cache.net-a-porter.com/images/products/1045398/1045398_in_sl.jpg","","https://cache.net-a-porter.com/images/products/1045398/1045398_in_sl.jpg"));
                        mCardAdapter.addCardItem(new CardItem("你1","https://cache.net-a-porter.com/images/products/1041734/1041734_in_sl.jpg","你2","http://cache.net-a-porter.com/images/products/1039276/1039276_in_sl.jpg"));
                        mFragmentCardAdapter = new CardFragmentPagerAdapter(getChildFragmentManager(),
                                dpToPixels(2, getActivity()), mCardAdapter.getCount());

                        mCardShadowTransformer = new ShadowTransformer(viewPager, mCardAdapter);
                        mFragmentCardShadowTransformer = new ShadowTransformer(viewPager, mFragmentCardAdapter);

                        mCardShadowTransformer.enableScaling(true);
                        mFragmentCardShadowTransformer.enableScaling(true);

                        viewPager.setAdapter(mCardAdapter);
                        viewPager.setPageTransformer(false, mCardShadowTransformer);
                        viewPager.setOffscreenPageLimit(3);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                int curItem = viewPager.getCurrentItem();
                                viewPager.setCurrentItem(curItem + 1);
                                if (handler != null) {
                                    handler.postDelayed(this, mShowingFragments_time);
                                }
                            }
                        }, mShowingFragments_time);

                        head_text.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "foot head_text", Toast.LENGTH_SHORT).show();
                            }
                        });
                        head_text1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "foot head_text1", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).bind(new FooterBind() {
            @Override
            public void onBind(ItemView footer, int order) {
                footer.setOnClickListener(R.id.footerText, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), "foot click", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).listen(new BottomListener() {
            @Override
            public void onBottom(final ItemView footer,
                                 final SlideAdapter slideAdapter) {
                footer.setText(R.id.footerText, "正在加载，请稍后...");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                slideAdapter.loadMore(data2);
                                footer.setText(R.id.footerText, "正在加载");
                            }
                        });

                    }
                }).start();
            }
        }).into(recyclerView);
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
}
