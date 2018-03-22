package commerce.overseas.com.oec.ui.network

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.badoo.mobile.util.WeakHandler
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * @Time : 2018/3/22 no 上午11:04
 * @USER : vvguoliang
 * @File : Callback.java
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
@Suppress("UNCHECKED_CAST")
abstract class Callback<T>(parser: Parser<T>) : Callback {
    private var mParser: Parser<T>
    private var mHandler = WeakHandler(Looper.getMainLooper(), UICallback<T>(this))

    companion object {
        val CALLBACK_SUCCESSFUL = 0x01
        val CALLBACK_FAILED = 0x02
    }

    init {
        if (parser == null) {
            throw IllegalArgumentException("Parser can not be null")
        }
        mParser = parser
    }

    override fun onFailure(call: Call?, e: IOException?) {
        val message = Message.obtain()
        message.what = CALLBACK_FAILED
        message.obj = e
        mHandler.sendMessage(message)
    }

    override fun onResponse(call: Call?, response: Response?) {
        if (response != null) {
            if (response.isSuccessful) {
                val parseResult = mParser.parse(response)
                val message = Message.obtain()
                message.what = CALLBACK_SUCCESSFUL
                message.obj = parseResult
                mHandler.sendMessage(message)
            } else {
                val message = Message.obtain()
                message.what = CALLBACK_FAILED
                mHandler.sendMessage(message)
            }
        }
    }

    internal class UICallback<T>(callback: commerce.overseas.com.oec.ui.network.Callback<T>) : Handler.Callback {
        var mCallback = callback
        override fun handleMessage(msg: Message?): Boolean {
            when (msg?.what) {
                CALLBACK_SUCCESSFUL -> {
                    val t = msg.obj as T
                    if (mCallback != null) {
                        mCallback.onResponse(t)
                    }
                }
                CALLBACK_FAILED -> {
                    val e: IOException = if (msg.obj != null) {
                        msg.obj as IOException
                    } else {
                        IOException("response is wrong")
                    }

                    if (mCallback != null) {
                        mCallback.onFailure(e)
                    }
                }

            }
            return true
        }

    }

    abstract fun onResponse(t: T)

    abstract fun onFailure(e: IOException)

}