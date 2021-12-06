package com.nidhinek.ottminiframe.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nidhinek.ottminiframe.data.BaseModel
import com.nidhinek.ottminiframe.databinding.CarousalImageViewBinding

class CarousalAdapter(private val onClicked: (ArrayList<BaseModel>?, Int) -> Unit) :RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val playList: ArrayList<BaseModel> = arrayListOf()
    companion object {
        const val VIEW_TYPE_HORIZONDAL = 1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view: View = when (viewType) {
            VIEW_TYPE_HORIZONDAL -> CarousalImageViewItem(context = parent.context)
            else -> CarousalImageViewItem(context = parent.context)
        }
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return playList.size
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_HORIZONDAL
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        playList.getOrNull(position)?.let { (holder as? ViewHolder)?.bindData(it) }
    }

    fun addToPlaylist(playList: List<BaseModel>) {
        this.playList.clear()
        this.playList.addAll(playList)
       // notifyItemRangeInserted(0, playList.size)
        notifyDataSetChanged()
    }

    fun clearList() {
        this.playList.clear()
        notifyDataSetChanged()
    }


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(model: BaseModel) {
            when (view) {
                is CarousalImageViewItem -> {
                    view.bindData(
                        CarousalImageData(model, adapterPosition) {
                            onClicked(playList as? ArrayList<BaseModel>, adapterPosition)
                        }

                    )
                }
            }
        }
    }
}
