package com.covid.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.covid.Model.NavigationItems
import com.covid.R


class DrawerListAdapter(private val context: Context, private val navigationItemsList: List<NavigationItems>) : RecyclerView.Adapter<DrawerListAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nav_item_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: DrawerListAdapter.MyViewHolder, position: Int) {
        holder.tvTitle.text = navigationItemsList[position].navigationName
        holder.tvSubtitle.text = navigationItemsList[position].navigationSubtitle
        //holder.imgIcon.setImageResource(navigationItemsList.get(position).imgIcon);
    }

    override fun getItemCount(): Int {
        return navigationItemsList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val tvTitle: TextView
        val tvSubtitle: TextView
        private val imgIcon: ImageView

        init {
            tvTitle = itemView.findViewById<View>(R.id.tv_title) as TextView
            tvSubtitle = itemView.findViewById<View>(R.id.tv_subtitle) as TextView
            imgIcon = itemView.findViewById<View>(R.id.img_icon) as ImageView
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            itemClickListener!!.onItemClick(adapterPosition, view)

        }
    }

    fun setOnItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener

    }

    interface ItemClickListener {
        fun onItemClick(position: Int, v: View)
    }

    private var itemClickListener: ItemClickListener? = null
}
