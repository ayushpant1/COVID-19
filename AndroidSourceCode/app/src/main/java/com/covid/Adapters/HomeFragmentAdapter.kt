package com.covid.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.covid.Activities.DetailsActivity
import com.covid.Model.HomeListModel
import com.covid.R
import com.covid.Util.Constants
import com.google.gson.Gson
import androidx.recyclerview.widget.RecyclerView

class HomeFragmentAdapter(private val context: Context, private val homeListModelList: List<HomeListModel>) : RecyclerView.Adapter<HomeFragmentAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragmentAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.home_item_row, parent, false)
        //  view.setOnClickListener(MainActivity.myOnClickListener);
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeFragmentAdapter.MyViewHolder, position: Int) {
        holder.tvCanName.text = homeListModelList[position].brandName
        holder.tvPrice.text = Constants.RUPEE_SYMBOL + " " + homeListModelList[position].price
        holder.tvAdd.setOnClickListener {
            var quantity = Integer.parseInt(holder.tvQuantity.text.toString())
            quantity += 1
            holder.tvQuantity.text = quantity.toString() + ""
        }

        holder.tvRemove.setOnClickListener {
            var quantity = Integer.parseInt(holder.tvQuantity.text.toString())
            if (quantity > 1) {
                quantity -= 1
                holder.tvQuantity.text = quantity.toString() + ""
            }
        }
    }

    override fun getItemCount(): Int {
        return homeListModelList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var tvAdd: TextView
        var tvRemove: TextView
        var tvQuantity: TextView
        var tvCanName: TextView
        var tvPrice: TextView

        init {
            tvAdd = itemView.findViewById<View>(R.id.tv_add) as TextView
            tvRemove = itemView.findViewById<View>(R.id.tv_remove) as TextView
            tvQuantity = itemView.findViewById<View>(R.id.tv_quantity) as TextView
            tvCanName = itemView.findViewById<View>(R.id.can_name) as TextView
            tvPrice = itemView.findViewById<View>(R.id.can_price) as TextView
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val gson = Gson()
            val homeListString = gson.toJson(homeListModelList[adapterPosition])
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(Constants.HOME_STRING_OBJECT, homeListString)
            intent.putExtra(Constants.QUANTITY, tvQuantity.text)
            context.startActivity(intent)
        }
    }
}
