package com.nidhinek.ottminiframe.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nidhinek.ottminiframe.data.BaseModel
import com.nidhinek.ottminiframe.data.ImdbSearchResponse
import com.nidhinek.ottminiframe.databinding.CarousalViewBinding

class HomeAdapter(private val onClicked: (ArrayList<BaseModel>?, Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val playList: ArrayList<BaseModel> = arrayListOf()

    companion object {
        const val VIEW_TYPE_CAROUSAL = 1
        const val VIEW_TYPE_GRID = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = when (viewType) {
            VIEW_TYPE_CAROUSAL -> CarousalViewItem(context = parent.context)
            else -> GridViewItem(context = parent.context)
        }
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return playList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        playList.getOrNull(position)?.let { (holder as? ViewHolder)?.bindData(it) }
    }

    override fun getItemViewType(position: Int): Int {
         if (playList.getOrNull(position)?.railViewTitle==HomeFragment.CAROUSAL)
         {
          return   VIEW_TYPE_CAROUSAL
        }
        return VIEW_TYPE_GRID
    }

    fun addToPlaylist(playList: List<BaseModel>) {
        this.playList.clear()
        this.playList.addAll(playList)
        notifyDataSetChanged()
        //notifyItemRangeInserted(0, playList.size)
    }

    fun clearList() {
        this.playList.clear()
        notifyDataSetChanged()
    }


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(model: BaseModel) {
            when (view) {
                is CarousalViewItem -> {
                    view.bindData(
                        CarousalData((model as? ImdbSearchResponse)?.data, adapterPosition) {
                            onClicked(playList as? ArrayList<BaseModel>, adapterPosition)
                        }
                    )
                }
            }
        }
    }
}
