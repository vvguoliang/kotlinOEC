package commerce.overseas.com.oec.base

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import commerce.overseas.com.oec.api.IViewSpecification
import commerce.overseas.com.oec.kotlinpermissions.PermissionCallBack
import commerce.overseas.com.oec.statusBar.ImmersionBar

/**
 * @Time : 2018/3/20 no 下午2:22
 * @USER : vvguoliang
 * @File : BaseActivity.java
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
public abstract class BaseActivity : AppCompatActivity(), IViewSpecification {

    /**
     * 是否允许屏幕转动  默认不允许
     */
    var isAllowScreenRotate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!isAllowScreenRotate) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        val bundle = intent.extras
        initParams(bundle)

        val viewContent = layoutInflater.inflate(bindLayout(), null)
        setContentView(viewContent)
        //初始化沉浸式
        if (isImmersionBarEnabled())
            initImmersionBar()

        initView(viewContent)
        setListener()
        doBusiness()
    }

    protected var mImmersionBar: ImmersionBar? = null
    private var imm: InputMethodManager? = null

    /**
     * 设置statusBar主题
     */
    override fun initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this)
        mImmersionBar!!.init()
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected fun isImmersionBarEnabled(): Boolean {
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        this.imm = null
        if (mImmersionBar != null)
            mImmersionBar!!.destroy()  //在BaseActivity里销毁
    }

    override fun finish() {
        super.finish()
        hideSoftKeyBoard()
    }

    fun hideSoftKeyBoard() {
        val localView = currentFocus
        if (this.imm == null) {
            this.imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        }
        if (localView != null && this.imm != null) {
            this.imm!!.hideSoftInputFromWindow(localView.windowToken, 2)
        }
    }

    fun startActivity(targetActivity: Class<*>) {
        startActivity(Intent(this, targetActivity))
    }

    fun startActivity(targetActivity: Class<*>, bundle: Bundle?) {
        startActivity(Intent(this, targetActivity).putExtras(bundle))
    }

    fun startActivityForResult(cls: Class<*>, bundle: Bundle?,
                               requestCode: Int) {
        val intent = Intent()
        intent.setClass(this, cls)
        intent.putExtras(bundle)
        startActivityForResult(intent, requestCode)
    }

    fun addFragmentToActivity(fragmentManager: FragmentManager,
                              fragment: Fragment, frameId: Int) {
        val transaction = fragmentManager.beginTransaction()
        if (!fragment.isAdded)
            transaction.add(frameId, fragment)
        fragmentManager.fragments.filter { it.id == fragment.id }.map { transaction.hide(it) }
        transaction.show(fragment)
        transaction.commit()
    }

    private val REQUEST_PERMISSION = 1111
    private val NEEDED_PERMISSIONS = 2222
    var pCallback: PermissionCallBack? = null
    var permissionsNeed: MutableList<String> = mutableListOf()

    fun requestPermissions(arrays: Array<String>, permissionCallback: PermissionCallBack) {
        permissionsNeed.clear()
        pCallback = permissionCallback
        for (permission in arrays) {
            if (ActivityCompat.checkSelfPermission(applicationContext, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsNeed.add(permission)
            }
        }
        if (permissionsNeed.size > 0) {
            Log.v("request", "permissions")
            reuestNeededPermission(permissionsNeed)
        } else {
            toast("Permissions Granted")
            pCallback?.permissionGranted()
        }
    }

    fun requestPermissions(permission: String, permissionCallback: PermissionCallBack) {
        pCallback = permissionCallback
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(permission),
                    REQUEST_PERMISSION)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(permission),
                    REQUEST_PERMISSION)
        }
    }

    private fun reuestNeededPermission(permissionsNeed: MutableList<String>) {
        // if (ActivityCompat.shouldShowRequestPermissionRationale(this@PermissionsActivity,permissionsNeed.toTypedArray()))
        ActivityCompat.requestPermissions(this@BaseActivity, permissionsNeed.toTypedArray(), NEEDED_PERMISSIONS)
    }

    fun AppCompatActivity.toast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        if (grantResults.isNotEmpty()) {
            Log.v("resultss", "" + grantResults[0] + grantResults.toString())
            if (requestCode == REQUEST_PERMISSION) {
                if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Camera permission has been granted, preview can be displayed
                    pCallback?.permissionGranted()
                } else {
                    pCallback?.permissionDenied()
                }
            } else if (requestCode == NEEDED_PERMISSIONS) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pCallback?.permissionGranted()
                } else {
                    pCallback?.permissionDenied()
                }

            } else {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }

}