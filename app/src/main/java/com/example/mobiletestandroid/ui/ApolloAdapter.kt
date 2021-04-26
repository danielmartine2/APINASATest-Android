package com.example.mobiletestandroid.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletestandroid.R
import com.example.mobiletestandroid.domain.ApolloEntity
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import org.w3c.dom.Entity

class ApolloAdapter(private val data: MutableList<ApolloEntity>
    ,private val apolloView: ApolloPresenter.View): RecyclerView.Adapter<ApolloAdapter.Holder>() {

    private var dataApollo: MutableList<ApolloEntity> = mutableListOf()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder =
        Holder(LayoutInflater.from(p0.context).inflate(R.layout.apollo_item, p0, false))


    override fun onBindViewHolder(p0: Holder, p1: Int) {
        p0.bindView(data[p1], apolloView)
    }

    override fun getItemCount(): Int = data.size

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        private val ivApollo: ImageView = itemView.findViewById(R.id.iv_apollo)
        private val llApollo: LinearLayout = itemView.findViewById(R.id.ll_favourite)
        private val ivFavourite: ImageView = itemView.findViewById(R.id.iv_favourite)
        private val ivNotFavourite: ImageView = itemView.findViewById(R.id.iv_favourite_without)


        fun bindView(itemApollo: ApolloEntity, view: ApolloPresenter.View){
            with(itemApollo){
                tvTitle.text = title

                if (image.isNotBlank()) {
                    val url = image
                        ivApollo.setImageDrawable(null)
                    Picasso.get()
                        .load(url)
                        .fit()
                        .centerInside()
                        .into(ivApollo)
                }

                if (isFavourite){
                    ivFavourite.visibility = View.VISIBLE
                    ivNotFavourite.visibility = View.GONE
                }else{
                    ivFavourite.visibility = View.GONE
                    ivNotFavourite.visibility = View.VISIBLE
                }

                llApollo.setOnClickListener {
                    isFavourite = !isFavourite
                    view.updateFavourite(this)
                }

                ivApollo.setOnClickListener {
                    view.navigateToDetail(this)
                }
            }
        }
    }

    fun getItems() = this.data.toMutableList()

    fun getAllItems() = this.dataApollo.toMutableList()

    //functions
    fun updateItems(items : MutableList<ApolloEntity>){
        this.data.clear()
        this.data.addAll(items)
        this.notifyDataSetChanged()
        this.dataApollo.clear()
        this.dataApollo = data.toMutableList()
    }

    fun updateItemChange(index: ApolloEntity){
        this.notifyItemChanged(data.indexOf(index))
    }

    fun updateFilterItems(filterItems: MutableList<ApolloEntity>){
        this.data.clear()
        this.data.addAll(filterItems)
        this.notifyDataSetChanged()
    }

}