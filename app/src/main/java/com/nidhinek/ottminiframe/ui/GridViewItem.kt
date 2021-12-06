package com.nidhinek.ottminiframe.ui

import android.content.Context
import com.nidhinek.ottminiframe.R
import com.nidhinek.ottminiframe.data.BaseModel
import com.nidhinek.ottminiframe.ui.base.BaseWidget

data class GridViewData(var list: List<BaseModel>)
class GridViewItem(context: Context) : BaseWidget<GridViewData>(context = context) {
    override fun getLayoutId(): Int {
        return R.layout.grid_view
    }

    override fun bindData(data: GridViewData, viewPosition: Int) {
        TODO("Not yet implemented")
    }

}
