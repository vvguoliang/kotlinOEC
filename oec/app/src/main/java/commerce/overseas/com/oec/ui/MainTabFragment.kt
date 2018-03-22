//package commerce.overseas.com.oec.ui
//
//import android.os.Bundle
//import android.os.Handler
//import android.support.v4.widget.SwipeRefreshLayout
//import android.support.v7.widget.LinearLayoutManager
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import commerce.overseas.com.oec.R
//import commerce.overseas.com.oec.RecyclerView.*
//import commerce.overseas.com.oec.base.BaseFragment
//import commerce.overseas.com.oec.ui.Entity.Bean
//import kotlinx.android.synthetic.main.fra_maintab.*
//import java.util.ArrayList
//
///**
// * @Time : 2018/3/22 no 上午10:16
// * @USER : vvguoliang
// * @File : MainTabFragment.java
// * @Software: Android Studio
// *code is far away from bugs with the god animal protecting
// *   I love animals. They taste delicious.
// * ***┏┓   ┏ ┓
// * **┏┛┻━━━┛ ┻┓
// * **┃   ☃   ┃
// * **┃ ┳┛  ┗┳ ┃
// * **┃    ┻   ┃
// * **┗━┓    ┏━┛
// * ****┃    ┗━━━┓
// * ****┃ 神兽保佑 ┣┓
// * ****┃ 永无BUG！┏┛
// * ****┗┓┓┏━┳┓┏┛┏┛
// * ******┃┫┫  ┃┫┫
// * ******┗┻┛  ┗┻┛
// */
//class MainTabFragment : BaseFragment() , SwipeRefreshLayout.OnRefreshListener{
//
//    override fun onRefresh() {
//        Handler().postDelayed({
//            if (swipe_container.isRefreshing) {
//                swipe_container.isRefreshing = false
//            }
//        }, 3000)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.fra_maintab, container, false)
//        return view
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        swipe_container.setOnRefreshListener(this)
//        //设置颜色
//        swipe_container.setColorSchemeResources(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light, android.R.color.holo_orange_light,
//                android.R.color.holo_red_light)
//
//        recyclerView.layoutManager = LinearLayoutManager(activity)
//
//        val data = ArrayList<Bean>()
//        for (i in 0..29) {
//            data.add(Bean("我是第" + i + "个item"))
//        }
//
//
//        val data2 = ArrayList<Bean>()
//        for (i in 0..4) {
//            data2.add(Bean("我是一个新item"))
//        }
//
//        val itemBind = object : ItemBind<Bean>() {
//            override fun onBind(itemView: ItemView, data: Bean, position: Int) {
//                itemView.setText(R.id.textView, data.info)
//                        .setOnClickListener { Toast.makeText(activity, "click" + position, Toast.LENGTH_SHORT).show() }
//                //                        .setOnClickListener(R.id.textView, new View.OnClickListener() {
//                //                            @Override
//                //                            public void onClick(View view) {
//                //                                Toast.makeText(getActivity(), "textView click" + position, Toast.LENGTH_SHORT).show();
//                //                            }
//                //                        });
//            }
//        }
//
//        SlideAdapter.load(data)
//                .item(R.layout.item)//, 0, 0, R.layout.menu, 0.35f)
//                .padding(1)
//                .header(R.layout.head, 0.3f)
//                .footer(R.layout.foot, 0.1f)
//                .bind(itemBind)
//                .bind(HeaderBind { header, order -> header.setOnClickListener(R.id.headText) { Toast.makeText(activity, "head click", Toast.LENGTH_SHORT).show() } })
//                .bind(FooterBind { footer, order -> footer.setOnClickListener(R.id.footerText) { Toast.makeText(activity, "foot click", Toast.LENGTH_SHORT).show() } })
//                .listen { footer, slideAdapter ->
//                    footer.setText(R.id.footerText, "正在加载，请稍后...")
//                    Thread(Runnable {
//                        try {
//                            Thread.sleep(1000)
//                        } catch (e: InterruptedException) {
//                            e.printStackTrace()
//                        }
//
//                        activity!!.runOnUiThread {
//                            slideAdapter.loadMore(data2)
//                            footer.setText(R.id.footerText, "正在加载")
//                        }
//                    }).start()
//                }
//                .into(recyclerView)
//    }
//}