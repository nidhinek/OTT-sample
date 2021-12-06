package com.nidhinek.ottminiframe.ui

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.nidhinek.ottminiframe.R
import com.nidhinek.ottminiframe.data.BaseModel
import com.nidhinek.ottminiframe.ui.base.BaseWidget

data class CarousalData(
    var list: List<BaseModel>?,
    var itemPosition: Int,
    val onClicked: () -> Unit
)

class CarousalViewItem(context: Context) : BaseWidget<CarousalData>(context = context) {
    private var carousalAdapter: CarousalAdapter? = null
    private lateinit var recyclerView: RecyclerView

    override fun getLayoutId(): Int {
        return R.layout.carousal_view
    }

    override fun bindData(data: CarousalData, viewPosition: Int) {
        initRecyclerAdapter(onClicked = data.onClicked)
        addToPlaylist(data.list)
    }

    private fun initRecyclerAdapter(onClicked: () -> Unit) {
        recyclerView = widgetView.findViewById(R.id.recyclerView)
        val layoutManager = CenterZoomLinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        carousalAdapter = CarousalAdapter(onClicked = { mediaList, position ->
            {

            }
        })
        recyclerView?.adapter = carousalAdapter
        recyclerView?.addItemDecoration(
            HorizondalSpaceItemDecorator(
                resources.getDimension(R.dimen.sixteenDp).toInt()
            )
        )
    }

    private fun addToPlaylist(playList: List<BaseModel>?) {
        playList?.let { carousalAdapter?.addToPlaylist(it) }
    }

}
