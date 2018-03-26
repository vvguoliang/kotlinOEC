package commerce.overseas.com.oec.ui

import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import commerce.overseas.com.oec.R
import commerce.overseas.com.oec.base.BaseFragment
import kotlinx.android.synthetic.main.fra_mainmy.*

/**
 * @Time : 2018/3/20 no 下午5:04
 * @USER : vvguoliang
 * @File : MyTabFragment.java
 * @Software: Android Studio
 *code is far away from bugs with the god animal protecting
 *   I love animals. They taste delicious.
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
class MyTabFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private val mShowingFragments_time = 3000

    private val handler = Handler()

    override fun onRefresh() {
        handler.postDelayed({
            if (swipe_container.isRefreshing) {
                swipe_container.isRefreshing = false
            }
        }, mShowingFragments_time.toLong())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fra_mainmy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe_container.setOnRefreshListener(this)
        //设置颜色
        swipe_container.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light)

    }
}