package commerce.overseas.com.oec

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import commerce.overseas.com.oec.base.BaseBackExitActivity
import commerce.overseas.com.oec.base.BaseFragment
import commerce.overseas.com.oec.ui.MiddleTabFragment
import commerce.overseas.com.oec.ui.MyTabFragment
import commerce.overseas.com.oec.ui.MainTabClassification
import kotlinx.android.synthetic.main.activity_main.*
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import commerce.overseas.com.oec.statusBar.ImmersionBar
import commerce.overseas.com.oec.ui.MainTabFragment

@SuppressLint("RestrictedApi")
class MainActivity : BaseBackExitActivity() {

    var bluesFragments = listOf<BaseFragment>(MainTabFragment(), MainTabClassification(), MiddleTabFragment(), MyTabFragment())

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView(rootView: View) {
    }

    override fun setListener() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        disableShiftMode(navigation);
        viewpager.adapter = viewPagerAdapter(supportFragmentManager, bluesFragments)
        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                navigation.menu.getItem(position).setChecked(true)

            }
        })
    }

    override fun doBusiness() {
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewpager.setCurrentItem(0, true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_classification -> {
                viewpager.setCurrentItem(1, true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_shopping_cart -> {
                viewpager.setCurrentItem(2, true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_my -> {
                viewpager.setCurrentItem(3, true)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    //继承 FragmentPagerAdapter 创建适配器，利用主构造函数传值
    class viewPagerAdapter(fm: FragmentManager?, var list: List<BaseFragment>) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return list.get(position)
        }

        override fun getCount(): Int {
            return list.size
        }
    }

    fun disableShiftMode(view: BottomNavigationView) {
        val menuView = view.getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false
            for (i in 0 until menuView.childCount) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView

                item.setShiftingMode(false)
                // set once again checked value, so view will be updated

                item.setChecked(item.itemData.isChecked)
            }
        } catch (e: NoSuchFieldException) {
        } catch (e: IllegalAccessException) {
        }

    }

    /**
     * 沉浸式
     */
    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar?.statusBarView(top_view)?.init()
        ImmersionBar.with(this@MainActivity)
                .statusBarDarkFont(true, 0.2f)
                .init()

    }

//    requestPermissions(Manifest.permission.CALL_PHONE, object : PermissionCallBack {
//        override fun permissionGranted() {
//            super.permissionGranted()
//            Log.v("Call permissions", "Granted")
//        }
//
//        override fun permissionDenied() {
//            super.permissionDenied()
//            Log.v("Call permissions", "Denied")
//        }
//    })

}
