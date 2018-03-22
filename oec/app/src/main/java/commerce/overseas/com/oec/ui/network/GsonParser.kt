package commerce.overseas.com.oec.ui.network

import com.google.gson.Gson
import okhttp3.Response
import java.io.IOException
import java.lang.IllegalArgumentException

/**
 * @Time : 2018/3/22 no 上午11:08
 * @USER : vvguoliang
 * @File : GsonParser.java
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
class GsonParser<T>(clazz: Class<T>) : Parser<T> {
    var mClass: Class<T>

    init {
        if (clazz == null) {
            throw IllegalArgumentException("Class can't be null")
        } else {
            mClass = clazz
        }
    }

    override fun parse(response: Response): T? {
        try {
            var gson = Gson()
            val str = response.body()?.string()
            val t = gson.fromJson(str, mClass)
            return t
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}