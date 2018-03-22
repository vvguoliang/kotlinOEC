package commerce.overseas.com.oec.ui.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import commerce.overseas.com.oec.R

/**
 * @Time : 2018/3/22 no 下午1:20
 * @USER : vvguoliang
 * @File : HistoryAdapter.java
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
class HistoryAdapter(context: Context, booksList: List<String>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    var context: Context? = context
    var list: List<String>? = null
    var inflater: LayoutInflater? = null

    init {
        this.inflater = LayoutInflater.from(context)
        this.list = booksList
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val bean = list?.get(position)
        holder?.tv_book_name?.text = bean
        holder?.itemView?.setOnClickListener {
            //跳转到搜索结果页面
            var intent = Intent()//context, SearchResultActivity::class.java
            intent.putExtra("keyWord", bean)
            context?.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(inflater?.inflate(R.layout.item_search_history, parent, false))
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class HistoryViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tv_book_name: TextView? = null

        init {

            tv_book_name = itemView?.findViewById(R.id.tv_book_name)
        }
    }
}